package com.foresee;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * tomcat 目录映射：可以把资源文件目录映射到tomcat服务中 
 * 供浏览器直接访问资源文件
 * @author DELL
 *
 */
@Configuration
public class AppMvcConfig extends WebMvcConfigurerAdapter{

	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/**")
		.addResourceLocations("classpath:/META-INF/resources/");//swagger2的时候需要映射
		registry.addResourceHandler("/img/**").addResourceLocations("file:/root/img/");
		super.addResourceHandlers(registry);
	}

}
