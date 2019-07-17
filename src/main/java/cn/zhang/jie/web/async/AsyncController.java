package cn.zhang.jie.web.async;

import java.util.concurrent.Callable;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

@RestController
public class AsyncController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private MockQueue mockQueue;
	@Autowired
	private DeferredResultHolder deferredResultHolder; 
	
	@GetMapping("/order")
	public DeferredResult<String> order() throws InterruptedException {
		logger.info("主线程开始");
		
		
		//使用这种方式时，这里的线程指的是“生产者线程”
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		DeferredResult<String> result = new DeferredResult<>();
		//最终什么时候范湖，取决于 DeferredResult 中的 setResult 什么时候被执行
		deferredResultHolder.getMap().put(orderNumber, result);
		
		
//		简单异步处理方式
//		Callable<String> result = new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				logger.info("副线程开始");
//				Thread.sleep(3000);
//				logger.info("副线程结束");
//				return "success";
//			}
//		};
		
		
		logger.info("主线程返回");
		return result;
	}
}
