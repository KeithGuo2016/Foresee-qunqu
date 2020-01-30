package com.foresee.controller.view;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;

/**
 * 
 * @author wrh
 * 标签管理视图
 */
@Controller
@RequestMapping("/view/tag")
public class ViewTagController {
	//社群标签管理主页
	@RequestMapping("/tagmanage.html")
	@AccountAuthenticationAnnotation
	public Object tagManage(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/tag/main";
	}
	//社群标签管理添加
	@RequestMapping("/tagmanageadd.html")
	@AccountAuthenticationAnnotation
	public Object tagManageAdd(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/tag/add";
	}
	//社群标签管理编辑
	@RequestMapping("/tagmanageedit.html")
	@AccountAuthenticationAnnotation
	public Object tagManageEdit(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/tag/edit";
	}
	//社群标签管理详情
	@RequestMapping("/tagmanageinfo.html")
	@AccountAuthenticationAnnotation
	public Object tagManageInfo(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/tag/info";
	}
}
