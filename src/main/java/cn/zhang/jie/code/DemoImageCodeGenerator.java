package cn.zhang.jie.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import cn.zhang.jie.core.validate.code.ImageCode;
import cn.zhang.jie.core.validate.code.ValidateCodeGenerator;

//假设这是用户自定义的图形验证码实现，它会覆盖系统默认的验证码实现
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	@Override
	public ImageCode generate(ServletWebRequest request) {
		System.out.println("更高级的图形验证码生成代码");
		return null;
	}

}
