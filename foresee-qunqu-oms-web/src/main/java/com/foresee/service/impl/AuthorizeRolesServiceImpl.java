package com.foresee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.MenusMapper;
import com.foresee.dao.RoleandmenuMapper;
import com.foresee.model.Menus;
import com.foresee.model.Roleandmenu;
import com.foresee.service.AuthorizeRolesService;

@Service
@Transactional(rollbackFor = Exception.class)
public class AuthorizeRolesServiceImpl implements AuthorizeRolesService {

	@Autowired
	RoleandmenuMapper dao;
	@Autowired
	MenusMapper menudao;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Roleandmenu record) {
		// 先进行删除，重新授权
		int i = 0;
		try {
			int roleid = record.getRoleid();
			dao.deleteByRoleid(roleid);
			List<Roleandmenu> list = new ArrayList<Roleandmenu>();
			String[] menuids = record.getMenuids().split(",");
			Roleandmenu roleandmenu = new Roleandmenu();
			for (String menuid : menuids) {
				roleandmenu = new Roleandmenu();
				roleandmenu.setMenuid(Integer.parseInt(menuid));
				roleandmenu.setRoleid(roleid);
				list.add(roleandmenu);
			}
			i = dao.insertList(list);
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

		return i;
	}

	@Override
	public List<Map<String, Object>> selectAuthorizeTree(List<Menus> menuList , Integer roleid) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			Roleandmenu roleandmenu = new Roleandmenu();
			roleandmenu.setRoleid(roleid);
			List<Roleandmenu> RoleandmenuList = dao.selectRMList(roleandmenu);
			Map<String, Object> map = new HashMap<String, Object>();
			for (Menus menu : menuList) {
				map = new HashMap<String, Object>();
				map.put("id", menu.getId());
				map.put("pId", menu.getMenupid());
				map.put("name", menu.getMenuname());
				map.put("file", menu.getMenuurl());
				for (Roleandmenu rm : RoleandmenuList) {
					if ((int)menu.getId() == (int)rm.getMenuid()) {
						map.put("checked", true);
						break;
					}
				}
				list.add(map);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

}
