package com.foresee.controller.view;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;

/**
 * 
 * @author wrh
 * 社群列表视图
 */
@Controller
@RequestMapping("/view/communitys")
public class ViewCommunitysController {
	//社群社群列表主页
	@RequestMapping("/shequnliebiao.html")
	@AccountAuthenticationAnnotation
	public Object shequnLiebiao(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shequn/main";
	}
	//社群社群添加选择社长
	@RequestMapping("/shequnwechat.html")
	@AccountAuthenticationAnnotation
	public Object shequnWechat(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shequn/wechatlist";
	}
	//社群社群列表添加
	@RequestMapping("/shequnadd.html")
	@AccountAuthenticationAnnotation
	public Object shequnAdd(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shequn/add";
	}
	//社群社群列表编辑
	@RequestMapping("/shequnedit.html")
	@AccountAuthenticationAnnotation
	public Object shequnEdit(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shequn/edit";
	}
	//社群社群列表详情
	@RequestMapping("/shequninfo.html")
	@AccountAuthenticationAnnotation
	public Object shequnInfo(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shequn/info";
	}
	//社群社群列表设置轮播
	@RequestMapping("/shequncarousels.html")
	@AccountAuthenticationAnnotation
	public Object shequnCarousels(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shequn/carousels";
	}
	//社群成员列表
	@RequestMapping("/shequncylist.html")
	@AccountAuthenticationAnnotation
	public Object shequnCyList(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shequn/cylist";
	}
	//社群文章列表
	@RequestMapping("/shequnwzlist.html")
	@AccountAuthenticationAnnotation
	public Object shequnWzList(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shequn/wzlist";
	}
	//社群粉丝列表
	@RequestMapping("/shequnfslist.html")
	@AccountAuthenticationAnnotation
	public Object shequnFsList(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shequn/fslist";
	}
	//社群社刊列表
	@RequestMapping("/shequnsklist.html")
	@AccountAuthenticationAnnotation
	public Object shequnSkList(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shequn/sklist";
	}
	//社群征稿列表
	@RequestMapping("/shequnzglist.html")
	@AccountAuthenticationAnnotation
	public Object shequnZgList(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/shequn/zglist";
	}
}
