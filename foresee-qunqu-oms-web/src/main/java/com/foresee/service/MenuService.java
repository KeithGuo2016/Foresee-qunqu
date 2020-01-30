package com.foresee.service;

import java.util.List;
import java.util.Map;

import com.foresee.model.Menus;

public interface MenuService {
	 Menus selectByPrimaryKey(Integer id);
	
	 int insertSelective(Menus record);
	 
	 int deleteByPrimaryKey(Integer id);
	 
	 int updateByPrimaryKeySelective(Menus record);
	 
	 List<Map<String, Object>> selectMenusTree(Menus record);
	 
	 /**
	  * 查询所有的菜单树并排序
	  * @return
	  */
	 List<Menus> selectAllMenus();
	 
	 List<Menus> selectAllMenusByRoleId(Integer roleId);
}
