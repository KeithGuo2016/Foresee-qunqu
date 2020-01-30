package com.foresee.service;


import java.util.List;
import java.util.Map;

import com.foresee.model.Roles;

public interface RolesService {
	
	int insertSelective(Roles record);
	
	int updateByPrimaryKeySelective(Roles record);
	
	int deleteByPrimaryKey(Integer id);
	
	Roles selectByPrimaryKey(Integer id);
	
	List<Map<String, Object>> selectRolesList(Roles record);

}
