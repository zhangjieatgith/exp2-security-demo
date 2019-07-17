package cn.zhang.jie.web.controller;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

//测试用例的两个标准注解
@RunWith(SpringRunner.class)	
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	//伪造 mvc 环境，不会启动tomcat，从加快测试速度
	private MockMvc mockMvc;
	
	//在每个测试用例执行前执行
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	//@Test
	public void whenQuerySuccess() throws Exception {
		//模拟发送请求、请求方式为get、请求地址、content-type
		String resp = mockMvc.perform(MockMvcRequestBuilders.get("/user")
			.param("username", "123")
			.param("age", "18")
			.contentType(MediaType.APPLICATION_JSON_UTF8))
			//对结果的期望, isOK表示200
			.andExpect(MockMvcResultMatchers.status().isOk())
			//这里表达式的含义是期望返回一个集合，长度是3
			.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
			//获取返回结果
			.andReturn().getResponse().getContentAsString();
		System.out.println("reso : " + resp);
	}
	
	
	//@Test
	public void whenGetInfoSuccess () throws Exception {
		String resp = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
			.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
			.andReturn().getResponse().getContentAsString();
		System.out.println("rest : " + resp);
	}
	
	
	//@Test
	public void whenGetInfoFail () throws Exception {
		String resp = mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
			.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().is4xxClientError())
			.andReturn().getResponse().getContentAsString();
		System.out.println("rest : " + resp);
	}
	
	
	//@Test
	public void whenCreateSuccess() throws Exception {
		Date date = new Date();		
		String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String resp = mockMvc.perform(MockMvcRequestBuilders.post("/user")
			.contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
			.andReturn().getResponse().getContentAsString();
		System.out.println("resp : " + resp);
	}
	
	//@Test
	public void whenUpdateSuccess() throws Exception {
		//Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().getEpochSecond());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("2020-01-01");
		String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String resp = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
			.contentType(MediaType.APPLICATION_JSON_UTF8).content(content))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
			.andReturn().getResponse().getContentAsString();
		System.out.println("resp : " + resp);
	}
	
	//@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
			.contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	
	@Test
	public void whenUploadSuccess() throws Exception {
		String resp = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file")
			.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "hellohello".getBytes("UTF-8"))))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andReturn().getResponse().getContentAsString();
		System.out.println("resp : " + resp);
	}
	
}


