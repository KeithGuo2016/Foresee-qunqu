package com.foresee.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;

/**
 * 
 * @author wrh
 * 主体框架视图
 */
@Controller
public class ViewMainController {
	
	@RequestMapping("/index.html")
	@AccountAuthenticationAnnotation
	public Object index(HttpServletRequest request,ModelMap model) {
		
		return "page/index";
	} 
	
	@RequestMapping("/main.html")
	@AccountAuthenticationAnnotation
	public Object main(HttpServletRequest request,ModelMap model) {
		
		return "page/main";
	}
	
	//登录页
	@RequestMapping("/login.html")
	public Object login(HttpServletRequest request,ModelMap model) {
		
		return "page/login";
	} 
}
