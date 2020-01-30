package com.foresee.controller.view;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;

/**
 * 
 * @author wrh
 * 用户管理/成员管理视图
 */
@Controller
@RequestMapping("/view/wechatuser")
public class ViewWechatUserController {
	//成员管理主页
	@RequestMapping("/chengyuanmanage.html")
	@AccountAuthenticationAnnotation
	public Object chengyuanManage(HttpServletRequest request,ModelMap model) {
		return "page/qnmanage/chengyuan/main";
	}
	//成员管理详情
	@RequestMapping("/chengyuanmanageinfo.html")
	@AccountAuthenticationAnnotation
	public Object chengyuanManageInfo(HttpServletRequest request,ModelMap model) {
		return "page/qnmanage/chengyuan/info";
	}
	//用户管理主页
	@RequestMapping("/yonghumanage.html")
	@AccountAuthenticationAnnotation
	public Object yonghuManage(HttpServletRequest request,ModelMap model) {
		return "page/wechat/wechat/main";
	}
	//用户管理详情
	@RequestMapping("/yonghumanageinfo.html")
	@AccountAuthenticationAnnotation
	public Object yonghuManageInfo(HttpServletRequest request,ModelMap model) {
		return "page/wechat/wechat/info";
	}
}
