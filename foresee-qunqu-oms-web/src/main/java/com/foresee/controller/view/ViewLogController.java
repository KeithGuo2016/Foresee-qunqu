package com.foresee.controller.view;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;

/**
 * 
 * @author wrh
 * 系统日志视图
 */
@Controller
@RequestMapping("/view/log")
public class ViewLogController {
	//日志管理主页
	@RequestMapping("/logmanage.html")
	@AccountAuthenticationAnnotation
	public Object logManage(HttpServletRequest request,ModelMap model) {
		return "page/log/log/main";
	}
	//日志管理详情
	@RequestMapping("/logmanageinfo.html")
	@AccountAuthenticationAnnotation
	public Object logManageInfo(HttpServletRequest request,ModelMap model) {
		return "page/log/log/info";
	}
}
