package com.foresee;


import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
/**
 * 构建API Controller中的方法可以作为API抛出 方便前端人员测试
 * 注意事项：方法最好标明 method @RequestMapping(value="/insert", method = RequestMethod.POST)，不然会显示所有的method类型的API
 * 传参 需要有 @RequestBody 注解
 * 
 * @author xujunqing
 *
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
	/**
	 * @Description:swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
	 */
	
	
	@Bean
	public Docket createRestApi() {
		
		// 为swagger添加header参数可供输入  
        ParameterBuilder userTokenHeader = new ParameterBuilder();
        ParameterBuilder userIdHeader = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();  
        userTokenHeader.name("headerUserToken").description("userToken")
        	.modelRef(new ModelRef("string")).parameterType("header")
        	.required(false).build();  
        userIdHeader.name("headerUserId").description("userId")
	    	.modelRef(new ModelRef("string")).parameterType("header")
	    	.required(false).build(); 
        pars.add(userTokenHeader.build());
        pars.add(userIdHeader.build());
		
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.foresee.ctrl"))
				.paths(PathSelectors.any()).build()
				.globalOperationParameters(pars);
	}
	 
	/**
	 * @Description: 构建 api文档的信息
	 */
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 设置页面标题
				.title("群趣创作小程序api接口文档")
				// 设置联系人
				.contact(new Contact("foresee-xjq", "", "foresee@163.com"))
				// 描述
				.description("欢迎访问短视频接口文档，这里是描述信息")
				// 定义版本号
				.version("1.0").build();
	}

}
