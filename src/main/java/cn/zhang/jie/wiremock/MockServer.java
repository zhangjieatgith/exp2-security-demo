package cn.zhang.jie.wiremock;

import java.io.IOException;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;

import com.github.tomakehurst.wiremock.client.WireMock;

//连接并使用 wiremock
public class MockServer {

	public static void main(String[] args) throws IOException {
		//由于在本地，因此只配置了端口号
		WireMock.configureFor(8062);
		//清空所有历史请求
		WireMock.removeAllMappings();
		
		ClassPathResource resource = new ClassPathResource("mockResponse.json");
		String responseBody = FileUtils.readFileToString(resource.getFile(),"UTF-8");
		//测试桩，这里表示请求方式时 get，并且url地址要严格匹配
		WireMock.stubFor(WireMock.get(WireMock.urlPathEqualTo("/order/1"))
			//指定响应和响应码
			.willReturn(WireMock.aResponse().withBody(responseBody).withStatus(200)));
	}
}

