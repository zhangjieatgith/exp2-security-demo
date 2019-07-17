package cn.zhang.jie.dto;

//用来封装请求参数
public class UserQueryCondition {

	private String username;
	private int age;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "UserQueryCondition [username=" + username + ", age=" + age + "]";
	}
}
