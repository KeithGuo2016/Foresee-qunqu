package com.foresee.mapper;

import java.util.List;

import com.foresee.pojo.UserPower;
import com.foresee.pojo.WechatUser;
import com.foresee.utils.MyMapper;

public interface UserPowerMapper extends MyMapper<UserPower> {
	public void updateUserPower(UserPower user);
	
}