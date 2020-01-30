package com.foresee.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foresee.advice.AccountAuthenticationAnnotation;
import com.foresee.service.AccountsService;
/**
 * 
 * @author wrh
 * 账户视图
 */
@Controller
@RequestMapping("/view/account")
public class ViewAccountController {
	@Resource  private AccountsService accountsService;
	/*
	 * 页面跳转index
	 */
	@RequestMapping("/account.html")
	@AccountAuthenticationAnnotation
	public Object getAccount(HttpServletRequest request,ModelMap model) {
		
		return "page/account/account";
	}
	
	/*
	 * 页面跳转add
	 */
	@RequestMapping("/accountadd.html")
	@AccountAuthenticationAnnotation
	public Object getAccountAdd(HttpServletRequest request,ModelMap model) {
		
		return "page/account/accountadd";
	}
	
	/*
	 * 页面跳转edit
	 */
	/**
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/accountedit.html")
	@AccountAuthenticationAnnotation
	public Object getAccountEdit(HttpServletRequest request,ModelMap model,
			@RequestParam(name = "id", required = false,defaultValue="0") Integer id) {
		model.addAttribute("account", accountsService.selectByPrimaryKey(id));
		return "page/account/accountedit";
	}
	
	/*
	 * 页面跳转修改密码
	 */
	/**
	 * @param request
	 * @param model
	 * @param id
	 * @return
	 */
	@RequestMapping("/accountcheckpwd.html")
	@AccountAuthenticationAnnotation
	public Object getAccountCheckpwd(HttpServletRequest request,ModelMap model) {
		return "page/account/checkpwd";
	}
	
}
