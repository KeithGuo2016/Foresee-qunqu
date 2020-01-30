package com.foresee.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.utils.ResultUtils;
import com.foresee.model.Menus;
import com.foresee.model.Roleandmenu;
import com.foresee.service.AuthorizeRolesService;
import com.foresee.service.MenuService;

/**
 * @author wangruiheng
 * 授权角色
 */
@RestController
@RequestMapping("/authorizeroles")
@Validated
public class AuthorizeRolesController {
	
	@Resource  private AuthorizeRolesService authorizeRolesService;
	
	@Autowired
	private MenuService MenuService;
	/**
	 * @return
	 * 角色授权
	 */
	@RequestMapping(value = "/authorize" , method = RequestMethod.POST)
	public Object authorizeRoles(HttpServletRequest request, @Valid Roleandmenu roleandmenu) {
		int num=authorizeRolesService.insertSelective(roleandmenu);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	
	}
	/**
	 * @return
	 * 角色授权查询选中权限菜单
	 * 
	 */
	@RequestMapping(value = "/selectauthorizetree" , method = RequestMethod.POST)
	public Object selectAuthorizeTree(HttpServletRequest request,
		@NotNull(message="{roles.id.notnull}")
		@Min(value=1,message="{roles.id.min}")
		 Integer id) {
		List<Menus> menus = MenuService.selectAllMenus();
		List<Map<String, Object>> list = authorizeRolesService.selectAuthorizeTree(menus , id);
		if(list.size()>0) {
			return ResultUtils.success(list);
		}
		return ResultUtils.fail("");
	}
}
