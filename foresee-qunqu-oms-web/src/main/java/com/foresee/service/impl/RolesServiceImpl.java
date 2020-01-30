package com.foresee.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.RolesMapper;
import com.foresee.model.Roles;
import com.foresee.service.RolesService;

@Service
@Transactional(rollbackFor = Exception.class)
public class RolesServiceImpl implements RolesService {
	@Autowired
	RolesMapper dao;

	@Override
	public int insertSelective(Roles record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	@Override
	public int updateByPrimaryKeySelective(Roles record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		List<Roles> list = new ArrayList<Roles>();
		Roles roles = new Roles();
		roles.setRolepid(id);
		list = dao.selectRolesList(roles);

		for (Roles group : list) {
			deleteByPrimaryKey(group.getId());
		}

		return dao.deleteByPrimaryKey(id);
	}

	@Override
	public Roles selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}

	@Override
	public List<Map<String, Object>> selectRolesList(Roles record) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Roles> list = dao.selectRolesList(record);
			for (Roles roles : list) {
				map = new HashMap<String, Object>();
				map.put("id", roles.getId());
				map.put("pId", roles.getRolepid());
				map.put("name", roles.getRolename());
				map.put("open", true);
				treeList.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return treeList;
	}

}
