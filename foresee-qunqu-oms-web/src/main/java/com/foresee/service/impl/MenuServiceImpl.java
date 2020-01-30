package com.foresee.service.impl;




import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.stream.Collectors;

import com.foresee.dao.MenusMapper;
import com.foresee.dao.RoleandmenuMapper;
import com.foresee.model.Menus;
import com.foresee.model.Roleandmenu;
import com.foresee.service.MenuService;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuServiceImpl implements MenuService {
	@Autowired
	MenusMapper dao;
	@Autowired
	RoleandmenuMapper rmdao;

	@Override
	public Menus selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public int insertSelective(Menus record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {

		List<Menus> list = new ArrayList<Menus>();
		Menus menus = new Menus();
		menus.setMenupid(id);
		list = dao.selectMenuList(menus);

		for (Menus group : list) {
			deleteByPrimaryKey(group.getId());
		}
		rmdao.deleteByMenuid(id);// 先删除关系表
		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Menus record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Map<String, Object>> selectMenusTree(Menus record) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Menus> list = dao.selectMenuList(record);
			for (Menus menu : list) {
				map = new HashMap<String, Object>();
				map.put("id", menu.getId());
				map.put("pId", menu.getMenupid());
				map.put("name", menu.getMenuname());
				map.put("open", true);
				treeList.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return treeList;
	}
	
	@Override
	public List<Menus> selectAllMenus() {
		List<Menus> list = new ArrayList<>();
		List<Menus> menusList = dao.selectByParentId(0);
		if(menusList != null && menusList.size() > 0) {
			for(Menus menus : menusList) {
				list.add(menus);
				Integer id = menus.getId();
				List<Menus> byParentId = getByParentId(id);
				list.addAll(byParentId);
			}
		}
		return list;
	}
	
	public List<Menus> getByParentId(Integer id){
		List<Menus> list = new ArrayList<>();
		List<Menus> menusList = dao.selectByParentId(id);
		if(menusList != null && menusList.size() > 0) {
			for(Menus menus : menusList) {
				Integer pid = menus.getId();
				List<Menus> selectByParentId = dao.selectByParentId(pid);
				if(selectByParentId != null && selectByParentId.size() > 0) {
					List<Menus> byParentId = getByParentId(pid);
					list.add(menus);
					list.addAll(byParentId);
				}else {
					list.add(menus);
				}
			}
		}
		return list;
	}
	
	@Override
	public List<Menus> selectAllMenusByRoleId(Integer roleId) {
		List<Menus> menuList = new ArrayList<>();
		Roleandmenu record = new Roleandmenu();
		record.setRoleid(roleId);
		List<Roleandmenu> roleandmenus = rmdao.selectRMList(record);
		if(roleandmenus != null && roleandmenus.size() > 0) {
			List<Menus> meList = this.selectAllMenus();
			List<Integer> list = roleandmenus.stream().map(u->u.getMenuid()).collect(Collectors.toList());
			for(Menus menus : meList) {
				if(list.contains(menus.getId())) {
					menuList.add(menus);
				}
			}
		}
		return menuList;
	}
}
