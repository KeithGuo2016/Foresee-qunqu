package com.foresee.controller.view;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;
/**
 * 
 * @author wrh
 * 社刊列表/社刊管理视图
 */
@Controller
@RequestMapping("/view/magazines")
public class ViewMagazinesController {
	//社群社刊列表主页
	@RequestMapping("/shekanliebiao.html")
	@AccountAuthenticationAnnotation
	public Object shekanLiebiao(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shekan/main";
	}
	//社群社刊列表详情
	@RequestMapping("/shekanliebiaoinfo.html")
	@AccountAuthenticationAnnotation
	public Object shekanLiebiaoInfo(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shekan/info";
	}
	//社群社刊选择文章列表
	@RequestMapping("/shekanwzlist.html")
	@AccountAuthenticationAnnotation
	public Object shekanWzlist(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shekan/wenzhanglist";
	}
	//社群社刊列表添加
	@RequestMapping("/shekanlbadd.html")
	@AccountAuthenticationAnnotation
	public Object shekanLbadd(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shekan/add";
	}
	//社群社刊列表编辑
	@RequestMapping("/shekanlbedit.html")
	@AccountAuthenticationAnnotation
	public Object shekanLbedit(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shekan/edit";
	}
	//社群社刊设置轮播
	@RequestMapping("/shekanlbcarousels.html")
	@AccountAuthenticationAnnotation
	public Object shekanLbcarousels(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shekan/carousels";
	}
	//群内社刊管理主页
	@RequestMapping("/shekanmanage.html")
	@AccountAuthenticationAnnotation
	public Object shekanManage(HttpServletRequest request,ModelMap model) {
		return "page/qnmanage/shekan/main";
	}
	//群内社刊管理添加
	@RequestMapping("/shekanmanageadd.html")
	@AccountAuthenticationAnnotation
	public Object shekanManageadd(HttpServletRequest request,ModelMap model) {
		return "page/qnmanage/shekan/add";
	}
	//群内社刊管理编辑
	@RequestMapping("/shekanmanageedit.html")
	@AccountAuthenticationAnnotation
	public Object shekanManageedit(HttpServletRequest request,ModelMap model) {
		return "page/qnmanage/shekan/edit";
	}
	//群内社刊管理详情
	@RequestMapping("/shekanmanageinfo.html")
	@AccountAuthenticationAnnotation
	public Object shekanManageInfo(HttpServletRequest request,ModelMap model) {
		return "page/qnmanage/shekan/info";
	}
	//群内社刊管理设置轮播
	@RequestMapping("/shekanmanagecarousels.html")
	@AccountAuthenticationAnnotation
	public Object shekanManagecarousels(HttpServletRequest request,ModelMap model) {
		return "page/qnmanage/shekan/carousels";
	}
}
