


package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.AccountsMapper;
import com.foresee.model.User;
import com.foresee.service.AccountsService;

@Service
@Transactional(rollbackFor = Exception.class)
public class AccountsServiceImpl implements AccountsService {
	@Autowired
	AccountsMapper dao;

	@Override
	public int insertSelective(User record) {
		// TODO Auto-generated method stub
		return dao.insertSelective(record);
	}

	@Override
	public List<User> selectListAccount(User record) {
		// TODO Auto-generated method stub
		return dao.selectListAccount(record);
	}

	@Override
	public int updateByPrimaryKeySelective(User record) {
		// TODO Auto-generated method stub
		return dao.updateByPrimaryKeySelective(record);
	}
	
	@Override
	public User selectById(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectById(id);
	}

	@Override
	public User selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return dao.selectByPrimaryKey(id);
	}
}
