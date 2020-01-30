package com.foresee.service;

import java.util.List;
import java.util.Map;

import com.foresee.model.Menus;
import com.foresee.model.Roleandmenu;

public interface AuthorizeRolesService {
	int deleteByPrimaryKey(Integer id);

    int insertSelective(Roleandmenu record);
    
    List<Map<String, Object>> selectAuthorizeTree(List<Menus> menus , Integer roleid);
}
