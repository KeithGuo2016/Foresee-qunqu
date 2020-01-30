package com.foresee.service;

import java.util.List;

import com.foresee.model.User;

public interface AccountsService {
	int insertSelective(User record);
	
	List<User> selectListAccount(User record);
	
	int updateByPrimaryKeySelective(User record);
	
	User selectById(Integer id);
	
	User selectByPrimaryKey(Integer id);
}
