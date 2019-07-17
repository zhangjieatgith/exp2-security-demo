package cn.zhang.jie.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.stereotype.Component;


//@Component
public class TimeFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("timeFilter.init");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("TimeFilter.start");
		long beginTime = System.currentTimeMillis();
		chain.doFilter(request, response);
		System.out.println("TimeFilter cost time : " + (System.currentTimeMillis()-beginTime));
		System.out.println("TimeFilter.finish");
	}

	@Override
	public void destroy() {
		System.out.println("TimeFilter.destroy");
	}

}
