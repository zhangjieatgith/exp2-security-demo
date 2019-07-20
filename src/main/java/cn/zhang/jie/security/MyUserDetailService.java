package cn.zhang.jie.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService, SocialUserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("表单登录用户名  : " +username);
		//根据用户名查找用户信息，User是Spring提供的一个UserDetails实现，参数3表示用户的权限，参数3方法的含义是将用户权限的字符串形式转为权限对象
		//Spring会将http请求传过来的用户名密码和数据库（下面的参数）做匹配
		//这里可以自定义UserDetails的实现类
		return buildUser(username);
	}

	private SocialUserDetails buildUser(String username) {
		String password = passwordEncoder.encode("123456");
		logger.info("加密后的密码是 ： " + password);
		return new SocialUser(username,password,true,true,true,true,
			AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}
	
	@Override
	//SpringSocial中提供，根据 userconnection 表中的UserId获取用户信息
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		logger.info("社交登录用户ID  : " + userId);
		return buildUser(userId);
	}
}
