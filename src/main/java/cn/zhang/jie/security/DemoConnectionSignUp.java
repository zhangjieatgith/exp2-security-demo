package cn.zhang.jie.security;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Component;

@Component
public class DemoConnectionSignUp implements ConnectionSignUp {
	@Override
	public String execute(Connection<?> connection) {
		//根据社交用户信息，默认创建用户，并返回用户唯一标识，这里假设用户唯一标识就是 DisplayName
		return connection.getDisplayName() + "abcd";
	}
}
