package cn.zhang.jie.exception;

public class UserNotExistException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	//自定义异常可以保存某些信息
	private String id;
	public UserNotExistException(String id) {
		super("use doesn't exist");
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
