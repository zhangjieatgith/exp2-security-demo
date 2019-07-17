package cn.zhang.jie.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

import cn.zhang.jie.validator.MyConstraint;

public class User {
	
	//[1/3] 使用JsonView,使用接口来声明多个视图 
	public interface UserSimpleView {};
	//在使用UserDetailView的时候，也会使用 UserSImpleView
	public interface UserDetailView extends UserSimpleView {};
	
	private String id;
	@MyConstraint(message = "一个测试用验证注解")
	private String username;
	//这里的message 在打印 errors 的时候用到
	@NotBlank(message = "密码不能为空")
	private String password;
	@Past(message = "必须小于当前时间")
	private Date birthday;
	
	public User() {}
	public User(String username,String password) {
		this.username = username;
		this.password = password;
	}
	
	//[2/3] 使用JsonVIew, 在值对象的get()方法上指定视图
	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", birthday=" + birthday + "]";
	}
}
