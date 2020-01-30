package com.foresee.service;

import java.util.List;

import com.foresee.model.User;
import com.foresee.model.UserApply;
import com.foresee.model.WechatUser;

public interface WechatUserService {
    int deleteByPrimaryKey(Integer id);

    int insert(WechatUser record);

    int insertSelective(WechatUser record);

    WechatUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WechatUser record,User user,Integer id);

    int updateByPrimaryKey(WechatUser record);
    
    List<WechatUser> selectList(WechatUser record);
    
    boolean setVipWechatUser(User user,Integer id);
    
    boolean setAdminWechatUser(User user,Integer id);
    
    boolean setUserCommunity(User user,Integer id);
    
    boolean setUserAdmin(User user,Integer id);
    boolean quxiaoAdminWechatUser(User user, Integer id);
    
    boolean setOrdinaryWechatUser(User user,UserApply userApply);
    
    boolean kickOutWechatUser(User user,Integer id);
    
    List<WechatUser>  selectListCommunitysFollow (WechatUser record);
    
    void setWechatUserRedis(WechatUser record);
    
}