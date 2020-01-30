package com.foresee.controller.view;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;

/**
 * 
 * @author wrh
 * 文章列表/文章管理视图
 */
@Controller
@RequestMapping("/view/articles")
public class ViewArticlesController {
	//社群文章列表主页
	@RequestMapping("/wenzhangliebiao.html")
	@AccountAuthenticationAnnotation
	public Object wenzhangLiebiao(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/wenzhang/main";
	}
	//社群文章列表添加
	@RequestMapping("/wenzhanglbadd.html")
	@AccountAuthenticationAnnotation
	public Object wenzhangLbadd(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/wenzhang/add";
	}
	//社群文章列表编辑
	@RequestMapping("/wenzhanglbedit.html")
	@AccountAuthenticationAnnotation
	public Object wenzhangLbedit(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/wenzhang/edit";
	}
	//社群文章列表详情
	@RequestMapping("/wenzhanginfo.html")
	@AccountAuthenticationAnnotation
	public Object wenzhangInfo(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/wenzhang/info";
	}
	//社群文章设置轮播
	@RequestMapping("/wenzhangcarousels.html")
	@AccountAuthenticationAnnotation
	public Object wenzhangCarousels(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/wenzhang/carousels";
	}
	//群内文章管理主页
	@RequestMapping("/wenzhangmanage.html")
	@AccountAuthenticationAnnotation
	public Object wenzhangManage(HttpServletRequest request,ModelMap model) {
		return "page/qnmanage/wenzhang/main";
	}
	//群内文章管理添加
	@RequestMapping("/wenzhangmanageadd.html")
	@AccountAuthenticationAnnotation
	public Object wenzhangManageadd(HttpServletRequest request,ModelMap model) {
		return "page/qnmanage/wenzhang/add";
	}
	//群内文章管理编辑
	@RequestMapping("/wenzhangmanageedit.html")
	@AccountAuthenticationAnnotation
	public Object wenzhangManageedit(HttpServletRequest request,ModelMap model) {
		return "page/qnmanage/wenzhang/edit";
	}
	//群内文章管理详情
	@RequestMapping("/wenzhangmaninfo.html")
	@AccountAuthenticationAnnotation
	public Object wenzhangManInfo(HttpServletRequest request,ModelMap model) {
		return "page/qnmanage/wenzhang/info";
	}
	//群内文章设置轮播
	@RequestMapping("/wenzhangmanagecarousels.html")
	@AccountAuthenticationAnnotation
	public Object wenzhangManagecarousels(HttpServletRequest request,ModelMap model) {
		return "page/qnmanage/wenzhang/carousels";
	}
}
