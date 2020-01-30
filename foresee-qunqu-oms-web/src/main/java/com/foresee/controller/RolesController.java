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
import org.springframework.web.bind.annotation.RestController;

import com.foresee.utils.ResultUtils;
import com.foresee.model.Roles;
import com.foresee.service.RolesService;
/**
 * @author wangruiheng
 * 角色管理
 */
@RestController
@RequestMapping("/role")
@Validated
public class RolesController{
	
	@Resource  private RolesService rolesService;
	/**
	 * @return
	 * 添加角色
	 */
	@RequestMapping("/save")
	public Object saveRole(HttpServletRequest request, @Valid Roles roles) {
		roles.setCreatetime(new Date());
		int num=rolesService.insertSelective(roles);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 删除角色-级联删除
	 */
	@RequestMapping("/del")
	public Object delRole(HttpServletRequest request,
			@NotNull(message="{roles.id.notnull}")
    		@Min(value=1,message="{roles.id.min}")
			 Integer id) {
		
		    int num = rolesService.deleteByPrimaryKey(id);
		    if(num>0) {
				return ResultUtils.success("");
			}
			return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 编辑角色
	 */
	@RequestMapping("/update")
	public Object updateRole(HttpServletRequest request,
			 Roles roles) {
		 int num = rolesService.updateByPrimaryKeySelective(roles);
		 if(num>0) {
				return ResultUtils.success("");
			}
			return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 查询角色树
	 */
	@RequestMapping("/selectroleslist")
	public Object selectRolesList(HttpServletRequest request, Roles roles) {
		List<Map<String, Object>> list = rolesService.selectRolesList(roles);
		 if(list.size()>0) {
				return ResultUtils.success(list);
			}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 查询角色信息
	 */
	@RequestMapping("/selectroles")
	public Object selectRoles(HttpServletRequest request, int id) {
		Roles roles = rolesService.selectByPrimaryKey(id);
		if(roles!=null){
			return ResultUtils.success(roles);
		}
		return ResultUtils.fail("");
	}
	

}
