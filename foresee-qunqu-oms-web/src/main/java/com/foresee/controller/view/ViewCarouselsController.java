package com.foresee.controller.view;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;


/**
 * 
 * @author wrh
 * 轮播管理视图
 */
@Controller
@RequestMapping("/view/carousels")
public class ViewCarouselsController {
	//轮播管理主页
	@RequestMapping("/main.html")
	@AccountAuthenticationAnnotation
	public Object mainManage(HttpServletRequest request,ModelMap model) {
		return "page/carousels/main";
	}
	//轮播管理添加
	@RequestMapping("/carouselsadd.html")
	@AccountAuthenticationAnnotation
	public Object carouselsAdd(HttpServletRequest request,ModelMap model) {
		return "page/carousels/add";
	}
	//轮播管理编辑
	@RequestMapping("/carouselsedit.html")
	@AccountAuthenticationAnnotation
	public Object carouselsEdit(HttpServletRequest request,ModelMap model) {
		return "page/carousels/edit";
	}
}

