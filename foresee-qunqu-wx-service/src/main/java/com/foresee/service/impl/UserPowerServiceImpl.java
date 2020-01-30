package com.foresee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.UserPowerMapper;
import com.foresee.mapper.WechatUserMapper;
import com.foresee.pojo.UserPower;
import com.foresee.service.UserPowerService;

@Service
public class UserPowerServiceImpl extends BasicsSvcImpl<UserPower> implements UserPowerService{
	@Autowired
	private UserPowerMapper userPowerMapper;
	
	public void updateUserPower(UserPower user) {
		userPowerMapper.updateUserPower(user);
	}
}
