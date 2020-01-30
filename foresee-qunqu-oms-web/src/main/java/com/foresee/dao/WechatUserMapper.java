package com.foresee.dao;

import java.util.List;

import com.foresee.model.WechatUser;

public interface WechatUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatUser record);

    int insertSelective(WechatUser record);

    WechatUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatUser record);

    int updateByPrimaryKey(WechatUser record);
    
    List<WechatUser> selectList(WechatUser record);
    
    List<WechatUser>  selectListCommunitysFollow (WechatUser record);
    
}