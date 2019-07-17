package cn.zhang.jie.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;


/**
 * $ 用来验证注解
 * 	1.MyConstraint，表示作用在哪个验证注解上
 * 	2.Object 表示注解作用在哪种类型上
 * @author admin
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object>{

	//这里可以注入Spring的bean,而且当前类不用指明 @Component，因为 实现ConstraintValidator就表示该类是一个Bean了
	//@Autowired
	
	@Override
	public void initialize(MyConstraint constraintAnnotation) {
		System.out.println("MyConstraintValidator.initialize");
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		System.out.println("MyConstraintValidator.isValid");
		return false;
	}

}
