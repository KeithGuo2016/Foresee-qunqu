package com.foresee;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import tk.mybatis.spring.annotation.MapperScan;


@SpringBootApplication
@MapperScan(basePackages ="com.foresee.mapper")
@ComponentScan(basePackages= {"com.foresee","org.n3r.idworker"})
//@EnableAsync   //开启异步任务
@EnableScheduling
public class BootApplication {
	 	
	
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

}
