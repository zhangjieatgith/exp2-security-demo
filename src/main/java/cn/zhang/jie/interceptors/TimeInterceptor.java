package cn.zhang.jie.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


@Component
public class TimeInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("TimeInterceptor.preHandler");
		request.setAttribute("startTime", System.currentTimeMillis());
		System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println(((HandlerMethod)handler).getMethod().getName());
		//决定是否继续下去，如果是false，那么 afterCompletion 也不会执行
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandler");
		System.out.println("time interceptor cost time L " + (System.currentTimeMillis() - (long)request.getAttribute("startTime")));
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
		System.out.println("time interceptor cost time L " + (System.currentTimeMillis() - (long)request.getAttribute("startTime")));
		System.out.println("ex is : " + ex);
	}
}
