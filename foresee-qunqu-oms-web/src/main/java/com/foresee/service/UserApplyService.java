package com.foresee.service;

import java.util.List;

import com.foresee.model.User;
import com.foresee.model.UserApply;

public interface UserApplyService {
    int deleteByPrimaryKey(Integer id);

    int insert(UserApply record);

    int insertSelective(UserApply record);

    UserApply selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserApply record);

    int updateByPrimaryKey(UserApply record);
    
    List<UserApply> selectList(UserApply record);
    List<UserApply> selectListJoinCommunity(UserApply record);
    
    boolean adoptUserApply(User user,Integer id,String adoptType,String newsContent);
    
}