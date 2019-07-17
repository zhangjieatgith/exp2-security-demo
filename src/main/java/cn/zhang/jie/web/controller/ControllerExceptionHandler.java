package cn.zhang.jie.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import cn.zhang.jie.exception.UserNotExistException;

/**
 * $ 该类本身并不处理http请求，它的作用仅仅是处理其他controller抛出的异常
 * @author admin
 *
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	//表示用来处理指定的异常
	@ExceptionHandler(UserNotExistException.class)
	//将返回的map转为json
	@ResponseBody
	//指定返回的状态码
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	//参数可以拿到抛出的异常
	public Map<String,Object> handlerUserNotExists(UserNotExistException ex){
		Map<String,Object> map = new HashMap<>();
		map.put("id", ex.getId());
		map.put("otherMsg", "none");
		return map;
	}
}
