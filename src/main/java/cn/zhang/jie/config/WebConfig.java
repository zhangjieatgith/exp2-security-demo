package cn.zhang.jie.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.zhang.jie.filter.TimeFilter;
import cn.zhang.jie.interceptors.TimeInterceptor;

//@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

//	@Autowired
	private TimeInterceptor timeInterceptor; 
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}
	
	

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		//设置Callable的异步方式
//		configurer.registerCallableInterceptors(interceptors)
		//设置DeferredResult的异步方式
//		configurer.registerDeferredResultInterceptors(interceptors)
		//设置超时时间
//		configurer.setDefaultTimeout(timeout)
		//配置线程池
//		configurer.setTaskExecutor(taskExecutor)
	}



	//	@Bean
	public FilterRegistrationBean timeFilter() {
		FilterRegistrationBean bean = new FilterRegistrationBean();
		TimeFilter timeFilter = new TimeFilter();
		bean.setFilter(timeFilter);
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		bean.setUrlPatterns(urls);
		return bean;
	}
}
