package com.foresee.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;
/**
 * 
 * @author wrh
 * 类别管理视图
 */
@Controller
@RequestMapping("/view/communityfamily")
public class ViewCommunityFamilyController {
	//社群类别管理主页
	@RequestMapping("/leibiemanage.html")
	@AccountAuthenticationAnnotation
	public Object leibieManage(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/liebie/main";
	}
	//社群类别管理添加
	@RequestMapping("/leibieadd.html")
	@AccountAuthenticationAnnotation
	public Object leibieAdd(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/liebie/add";
	}
	//社群类别管理编辑
	@RequestMapping("/leibieedit.html")
	@AccountAuthenticationAnnotation
	public Object leibieEdit(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/liebie/edit";
	}
	//社群类别管理详情
	@RequestMapping("/leibiemanageinfo.html")
	@AccountAuthenticationAnnotation
	public Object leibieManageInfo(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/liebie/info";
	}
	
}
