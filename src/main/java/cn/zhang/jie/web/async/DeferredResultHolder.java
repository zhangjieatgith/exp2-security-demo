package cn.zhang.jie.web.async;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

//用来在两个线程间通信
@Component
public class DeferredResultHolder {
	//key表示订单号，value表示订单的处理结果
	private Map<String, DeferredResult<String>> map = new HashMap<>();

	public Map<String, DeferredResult<String>> getMap() {
		return map;
	}
	public void setMap(Map<String, DeferredResult<String>> map) {
		this.map = map;
	}
}
