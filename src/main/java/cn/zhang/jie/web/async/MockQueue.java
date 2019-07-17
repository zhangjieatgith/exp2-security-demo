package cn.zhang.jie.web.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//模拟消息队列
@Component
public class MockQueue {

	//模拟下单消息
	private String placeOrder;
	//模拟订单处理完成
	private String completeOrder;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public String getPlaceOrder() {
		return placeOrder;
	}
	public void setPlaceOrder(String placeOrder) throws InterruptedException {
		new Thread(() -> {
			logger.info("接到下单请求 " + placeOrder);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.completeOrder = placeOrder;
			logger.info("订单处理完成 " + placeOrder);
		}).start();
	}
	public String getCompleteOrder() {
		return completeOrder;
	}
	public void setCompleteOrder(String completeOrder) {
		this.completeOrder = completeOrder;
	}
}
