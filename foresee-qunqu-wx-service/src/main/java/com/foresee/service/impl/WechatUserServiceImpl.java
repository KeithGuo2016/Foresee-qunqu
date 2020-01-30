package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.WechatUserMapper;
import com.foresee.mapper.WechatUserMapperCostom;
import com.foresee.pojo.WechatUser;
import com.foresee.service.WechatUserService;
import com.foresee.vo.WechatUserVo;
@Service
public class WechatUserServiceImpl extends BasicsSvcImpl<WechatUser> implements WechatUserService{

	@Autowired
	private WechatUserMapperCostom costom;
	@Autowired
	private WechatUserMapper wechatUserMapper;
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public WechatUserVo selectOneByOpenid(String openid) {
		return costom.selectOneByOpenid(openid);
	}
	@Override
	public void updateByopenId(WechatUser user) {
		wechatUserMapper.updateByopenId(user);
		
	}
	@Override
	public List<WechatUserVo> initRedisSession() {
		
		return costom.initRedisSession();
	}
	@Override
	public List<WechatUserVo> selectAdminList(int communityid) {

		return costom.selectAdminList(communityid);
	}

	@Override
	public List<WechatUserVo> selectByCommunityId(int communityid) {
		
		return costom.selectByCommunityId(communityid);
	}

	@Override
	public void updateCommunity(WechatUser user) {
		//wechatUserMapper.updateCommunity(user);
		
	}

}
