package com.foresee.controller;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.utils.ResultUtils;
import com.foresee.model.Menus;
import com.foresee.model.User;
import com.foresee.service.AccountsService;
import com.foresee.service.AuthorizeRolesService;
import com.foresee.service.MenuService;
/**
 * @author wangruiheng
 * 菜单管理
 */
@RestController
@RequestMapping("/menu")
@Validated
public class MenuController{
	
	@Resource  private MenuService menuService;
	
	@Resource  private AuthorizeRolesService authorizeRolesService;
	
	@Resource
	private AccountsService accountsService;
	
	/**
	 * @return
	 * 添加菜单
	 */
	@RequestMapping("/save")
	public Object saveMenu(HttpServletRequest request, @Valid Menus menus) {
		menus.setCreatetime(new Date());
		int num=menuService.insertSelective(menus);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 删除菜单-级联删除
	 */
	@RequestMapping("/del")
	public Object delMenu(HttpServletRequest request,
			@NotNull(message="{menus.id.notnull}")
    		@Min(value=1,message="{menus.id.min}")
			 Integer id) {
		    int num = menuService.deleteByPrimaryKey(id);
		    if(num>0) {
				return ResultUtils.success("");
			}
			return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 编辑菜单
	 */
	@RequestMapping("/update")
	public Object updateMenu(HttpServletRequest request, Menus menus) {
		 int num = menuService.updateByPrimaryKeySelective(menus);
		 if(num>0) {
				return ResultUtils.success("");
			}
			return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 菜单树形结构
	 */
	@RequestMapping("/selectmenuslist")
	public Object selectMenusList(HttpServletRequest request,
			 Menus menus) {
		List<Map<String, Object>> list = menuService.selectMenusTree(menus);
		if(list.size()>0) {
			return ResultUtils.success(list);
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 菜单信息
	 */
	@RequestMapping("/selectmenus")
	public Object selectMenus(HttpServletRequest request,
			 Integer id) {
		try {
			Menus menus = menuService.selectByPrimaryKey(id);
			if(menus!=null) {
				return ResultUtils.success(menus);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * 递归查询所有的菜单树
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/all" , method = RequestMethod.GET)
	public Object selectAllMenus(HttpServletRequest request) {
		return ResultUtils.success(menuService.selectAllMenus());
	}
	
	
	
	/**
	 * 根据登录人查询所有的菜单树
	 * @param request
	 * @param accountId
	 * @return
	 */
	@RequestMapping(value = "/all/tree" , method = RequestMethod.POST)
	public Object selectAllMenusByRoleId(HttpServletRequest request) {
		User accounts =  (User) request.getSession().getAttribute("accounts");
		return ResultUtils.success(menuService.selectAllMenusByRoleId(accounts.getRoleid()));
	}

}
