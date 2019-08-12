package cn.zhang.jie.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import com.fasterxml.jackson.annotation.JsonView;

import cn.zhang.jie.core.properties.SercurityProperties;
import cn.zhang.jie.dto.User;
import cn.zhang.jie.dto.UserQueryCondition;
import cn.zhang.jie.exception.UserNotExistException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

//表示这个java类是一个restful服务
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ProviderSignInUtils providerSignInUtils;
	@Autowired
	private SercurityProperties sercurityProperties;
	
	@GetMapping("/me")
//	public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
	//使用这种方式接受参数，可以解析JWT中的内容
	public Object getCurrentUser(Authentication user, HttpServletRequest request) throws Exception {
		
		String header = request.getHeader("Authorization");
		String token = StringUtils.substringAfter(header, "bearer ");
		//解析 JWT Token 到一个 Claims 对象，其中包含了Jwt中的一些额外信息
		Claims claims = Jwts.parser().setSigningKey(sercurityProperties.getOauth2().getJwtSigningKey().getBytes("UTF-8")).parseClaimsJws(token).getBody();
		String company = (String) claims.get("company");
		System.out.println("claims content : " + company);
		
		
		//获取当前登录用户，方式一
//		return SecurityContextHolder.getContext().getAuthentication();
		//获取当前登录用户，方式二（方法入参 Authentication authentication）
		//return authentication;
		//获取当前登录用户信息，方式三（仅返回用户信息，而不是全部信息）
		return user;
	}
	
	@GetMapping
	//[3/3] 使用JsonView<,在Controller方法上指定视图
	@JsonView(User.UserSimpleView.class)
	//name用来指定请求中的参数名
	public List<User> query(@RequestParam(name = "username",required = false,defaultValue = "123") String username){
		System.out.println("username : " + username);
		List<User> list = new ArrayList<>();		
		list.add(new User());
		list.add(new User());
		list.add(new User());
		return list;
	}

	@GetMapping("/userV1")
	//将请求参数封装到 param 中
	public List<User> queryV1(UserQueryCondition param){
		System.out.println(ReflectionToStringBuilder.toString(param, ToStringStyle.MULTI_LINE_STYLE));
		return null;
	}
	
	@JsonView(User.UserDetailView.class)
	//可以使用正则表达式
	@GetMapping("/{id:\\d+}")
	//@Pathvariable注解，如果不指定 value，那么 请求参数名和方法参数名要一致
	public User getInfo(@PathVariable("id") Integer id) {
		User user = new User();
		user.setUsername("tom");
		return user;
	}
	
	@PostMapping
	//@RequestBody，可以将请求参数的json，转为一个实体对象
	//@Valid 用来校验请求参数的合法性
	//BindingResult，配合 @Valid 使用，对获取的错误信息做进一步处理
	public User create(@Valid @RequestBody User user,BindingResult errors) {
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		System.out.println("create.user : " + user);
		user.setId("1");
		return user;
	}
	
	
	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user,BindingResult errors) {
		//剖析 errors 的本质
		if(errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> {
				FieldError fieldError = (FieldError) error;
				System.out.println(fieldError.getField() + " - " + fieldError.getDefaultMessage());
			});
		}
		System.out.println("update.user : " + user);
		return user;
	}
	
	@DeleteMapping("/{id:\\d}")
	public void delete(@PathVariable String id) {
		System.out.println("delete.id : " + id);
	}
	
	@GetMapping("/get2/{id:\\d+}")
	public void get2(@PathVariable String id) {
		throw new UserNotExistException(id);
	}
	
	@PostMapping("/register")
	public void regist(User user,HttpServletRequest request) {
		//注册用户
		//不管是注册用户还是绑定用户，都会拿到一个用户唯一标识
		String userId = user.getUsername();
		//Spring提供的工具类，用来完成注册，将用户id和Springsocial的信息绑定在一起，并插入数据库
		providerSignInUtils.doPostSignUp(userId, new ServletWebRequest(request));
		return;
	}
}
