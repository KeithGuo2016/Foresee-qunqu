package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.pojo.WechatUser;
import com.foresee.utils.MyMapper;

public interface WechatUserMapper extends MyMapper<WechatUser> {
	public void updateByopenId(WechatUser user);
	public void updateCommunity(WechatUser user);
	
}