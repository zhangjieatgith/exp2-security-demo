package cn.zhang.jie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * $ 基于 Springboot的应用（不是通过模板方式创建）
 * 	
 * @author admin
 *
 */
//表示这是一个Springboot项目
@SpringBootApplication  
@EnableSwagger2
public class DemoApplication {
	public static void main(String[] args) {
		//Springboot的标准启动方式
		SpringApplication.run(DemoApplication.class, args);
	}
}
