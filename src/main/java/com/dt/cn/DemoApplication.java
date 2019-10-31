package com.dt.cn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
//@MapperScan("com.dt.cn")
//@SpringBootApplication
@SpringBootApplication(scanBasePackages= {"com.dt.cn.*"})
@ComponentScan("com.dt.cn.*")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
