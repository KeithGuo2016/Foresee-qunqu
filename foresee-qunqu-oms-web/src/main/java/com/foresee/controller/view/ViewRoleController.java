package com.foresee.controller.view;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;
import com.foresee.service.RolesService;

/**
 * 
 * @author wrh
 * 角色视图
 */
@Controller
@RequestMapping("/view/role")
public class ViewRoleController {
	@Resource  private RolesService rolesService;
	@RequestMapping("/role.html")
	@AccountAuthenticationAnnotation
	public Object role(HttpServletRequest request,ModelMap model) {
		
		return "page/role/role";
	} 
	
	@RequestMapping("/roleadd.html")
	@AccountAuthenticationAnnotation
	public Object roleadd(HttpServletRequest request,ModelMap model) {
		
		return "page/role/roleadd";
	}
	
	@RequestMapping("/roleedit.html")
	@AccountAuthenticationAnnotation
	public Object roleedit(HttpServletRequest request,ModelMap model,int id) {
		
		model.addAttribute("role", rolesService.selectByPrimaryKey(id));
		return "page/role/roleedit";
	} 
	
	@RequestMapping("/managerole.html")
	@AccountAuthenticationAnnotation
	public Object managerole(HttpServletRequest request,ModelMap model) {
		
		return "page/role/managerole";
	}
	
}
