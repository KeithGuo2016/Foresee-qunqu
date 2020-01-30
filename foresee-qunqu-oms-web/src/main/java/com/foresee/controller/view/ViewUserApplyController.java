package com.foresee.controller.view;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;

/**
 * 
 * @author wrh
 * 审核管理视图
 */
@Controller
@RequestMapping("/view/userapply")
public class ViewUserApplyController {
	//用户审核管理主页
	@RequestMapping("/applymanage.html")
	@AccountAuthenticationAnnotation
	public Object applyManage(HttpServletRequest request,ModelMap model) {
		return "page/wechat/apply/main";
	}
	//用户审核管理(VIP)
	@RequestMapping("/applymanagevip.html")
	@AccountAuthenticationAnnotation
	public Object applyManageVip(HttpServletRequest request,ModelMap model) {
		return "page/wechat/apply/vip";
	}
	//用户审核管理(入群申请)
	@RequestMapping("/applymanagerqsq.html")
	@AccountAuthenticationAnnotation
	public Object applyManageRqsq(HttpServletRequest request,ModelMap model) {
		return "page/wechat/apply/rqsq";
	}
	//用户审核管理(管理员申请)
	@RequestMapping("/applymanageglysq.html")
	@AccountAuthenticationAnnotation
	public Object applyManageGlysq(HttpServletRequest request,ModelMap model) {
		return "page/wechat/apply/glysq";
	}
	//用户审核管理(创建社群申请)
	@RequestMapping("/applymanagecjsqsq.html")
	@AccountAuthenticationAnnotation
	public Object applyManageCjsqsq(HttpServletRequest request,ModelMap model) {
		return "page/wechat/apply/cjsqsq";
	}
	//用户审核管理(征稿申请)
	@RequestMapping("/applymanagezgsq.html")
	@AccountAuthenticationAnnotation
	public Object applyManageZgsq(HttpServletRequest request,ModelMap model) {
		return "page/wechat/apply/zgsq";
	}
	//用户审核管理(社刊申请)
	@RequestMapping("/applymanagesksq.html")
	@AccountAuthenticationAnnotation
	public Object applyManageSksq(HttpServletRequest request,ModelMap model) {
		return "page/wechat/apply/sksq";
	}
	//用户审核管理(推荐发文申请)
	@RequestMapping("/applymanagetjfwsq.html")
	@AccountAuthenticationAnnotation
	public Object applyManageTjfwsq(HttpServletRequest request,ModelMap model) {
		return "page/wechat/apply/tjfwsq";
	}
	//用户审核通过
	@RequestMapping("/applymanagetg.html")
	@AccountAuthenticationAnnotation
	public Object applyManageTg(HttpServletRequest request,ModelMap model) {
		return "page/wechat/apply/tongguo";
	}
	//用户审核拒绝
	@RequestMapping("/applymanagejj.html")
	@AccountAuthenticationAnnotation
	public Object applyManageJj(HttpServletRequest request,ModelMap model) {
		return "page/wechat/apply/jujue";
	}
	//用户审核管理详情
	@RequestMapping("/applymanageinfo.html")
	@AccountAuthenticationAnnotation
	public Object applyManageInfo(HttpServletRequest request,ModelMap model) {
		return "page/wechat/apply/info";
	}
	
	//用户审核管理详情
		@RequestMapping("/applymanagewzinfo.html")
		@AccountAuthenticationAnnotation
		public Object applymanagewzinfo(HttpServletRequest request,ModelMap model) {
			return "page/wechat/apply/wzinfo";
		}
}
