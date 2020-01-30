package com.foresee.service;

import java.util.List;

import com.foresee.model.UserPower;

/**
 * @version 1.0
 * @author wrh
 */
public interface UserPowerService {
	
	public int add(UserPower userPower);

	public int update(UserPower userPower);
    
	public int delete(Integer id);

	public UserPower getById(Integer id);

	public List<UserPower> listPage(UserPower userPower);

}
