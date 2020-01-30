package com.foresee.dao;

import java.util.List;

import com.foresee.model.User;

public interface AccountsMapper {
    int deleteByPrimaryKey(Integer id);
    
    int insert(User record);
    
    int insertSelective(User record);
    
    User selectById(Integer id);
    
    User selectByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(User record);
    
    int updateByPrimaryKey(User record);
    
    List<User> selectListAccount(User record);
    
    User selectByTh1(String th1);
}