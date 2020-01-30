package com.foresee.controller.view;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;
import com.foresee.constant.UserRoleConfig;
import com.foresee.model.User;

/**
 * 
 * @author wrh
 * 征稿管理视图
 */
@Controller
@RequestMapping("/view/contributes")
public class ViewContributesController {
	//社群征稿管理主页
	@RequestMapping("/zhenggaomanage.html")
	@AccountAuthenticationAnnotation
	public Object zhenggaoManage(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/zhenggao/main";
	}
	//社群征稿管理添加
	@RequestMapping("/zhenggaoadd.html")
	@AccountAuthenticationAnnotation
	public Object zhenggaoAdd(HttpServletRequest request,ModelMap model) {
		User account =  (User) request.getSession().getAttribute("accounts");
		if(account.getRoleid() == UserRoleConfig.SUPER_ADMINISTRATOR_USER){
			model.addAttribute("isCommunityView", 1);
		}else{
			model.addAttribute("isCommunityView", 0);
		}
		return "page/sqmanage/zhenggao/add";
	}
	//社群征稿管理编辑
	@RequestMapping("/zhenggaoedit.html")
	@AccountAuthenticationAnnotation
	public Object zhenggaoEdit(HttpServletRequest request,ModelMap model) {
		User account =  (User) request.getSession().getAttribute("accounts");
		if(account.getRoleid() == UserRoleConfig.SUPER_ADMINISTRATOR_USER){
			model.addAttribute("isCommunityView", 1);
		}else{
			model.addAttribute("isCommunityView", 0);
		}
		return "page/sqmanage/zhenggao/edit";
	}
	//社群征稿管理详情
	@RequestMapping("/zhenggaomaninfo.html")
	@AccountAuthenticationAnnotation
	public Object zhenggaoManInfo(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/zhenggao/info";
	}
	
	//社群文章设置轮播
	@RequestMapping("/zhenggaocarousels.html")
	@AccountAuthenticationAnnotation
	public Object wenzhangCarousels(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/zhenggao/carousels";
	}
}
