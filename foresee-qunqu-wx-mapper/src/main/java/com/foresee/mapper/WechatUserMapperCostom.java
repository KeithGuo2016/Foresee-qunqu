package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.pojo.WechatUser;
import com.foresee.utils.MyMapper;
import com.foresee.vo.WechatUserVo;

public interface WechatUserMapperCostom extends MyMapper<WechatUserVo> {
	public WechatUserVo selectOneByOpenid( @Param("openid") String openid);
	public List<WechatUserVo> initRedisSession();
	public List<WechatUserVo> selectAdminList(@Param("communityid") int communityid);
	public List<WechatUserVo> selectByCommunityId(@Param("communityid") int communityid);
}