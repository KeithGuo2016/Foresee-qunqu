package com.foresee.service;


import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.UserPower;


public interface UserPowerService extends BasicsSvc<UserPower>{
	public void updateUserPower(UserPower user);
}
