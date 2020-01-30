package com.foresee.service;

import java.util.List;

import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.WechatUser;
import com.foresee.vo.WechatUserVo;

public interface WechatUserService  extends BasicsSvc<WechatUser>{

	public WechatUserVo selectOneByOpenid( String openid);
	void updateByopenId(WechatUser user);
	void updateCommunity(WechatUser user);
	List<WechatUserVo> initRedisSession();
	List<WechatUserVo> selectAdminList(int communityid);
	public List<WechatUserVo> selectByCommunityId( int communityid);
}
