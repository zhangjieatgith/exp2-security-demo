package cn.zhang.jie.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * $ 编写自定义的验证注解
 * @author admin
 *
 */
@Target({ElementType.METHOD,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//表示这是一个验证注解，validatedBy 表示用哪个类来处理这个注解
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyConstraint {
	//必须包含该3个属性
	String message();
	Class<?>[] groups() default { };
	Class<? extends Payload>[] payload() default { };
}
