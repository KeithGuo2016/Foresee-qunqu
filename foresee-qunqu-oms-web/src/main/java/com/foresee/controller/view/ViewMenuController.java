package com.foresee.controller.view;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.foresee.advice.AccountAuthenticationAnnotation;
import com.foresee.advice.WebLog;
import com.foresee.service.MenuService;
/**
 * 
 * @author wrh
 * 菜单视图
 */
@Controller
@RequestMapping("/view/menu")
public class ViewMenuController {
	@Resource  private MenuService menuService;
	
	@RequestMapping("/menu.html")
	@AccountAuthenticationAnnotation
	public Object getMenu(HttpServletRequest request,ModelMap model) {
		
		return "page/menu/menu";
	} 
	
	@RequestMapping("/menuadd.html")
	@AccountAuthenticationAnnotation
	public Object getMenuAdd(HttpServletRequest request,ModelMap model) {
		return "page/menu/menuadd";
	} 
	
	@RequestMapping("/menuedit.html")
	@WebLog(description ="修改菜单")
	@AccountAuthenticationAnnotation
	public Object getMenuEdit(HttpServletRequest request,ModelMap model,
			@RequestParam(name = "id", required = false,defaultValue="0") Integer id) {
		model.addAttribute("menu", menuService.selectByPrimaryKey(id));
		return "page/menu/menuedit";
	} 
	
}
