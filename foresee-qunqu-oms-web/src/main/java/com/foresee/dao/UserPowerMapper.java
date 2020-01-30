package com.foresee.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.foresee.model.UserPower;
/**
 * @version 1.0
 * @author wrh
 */
@Repository
public interface UserPowerMapper {
    
    UserPower getById(Integer id);

    List<UserPower> listPage(UserPower userPower);
    
    int insert(UserPower userPower);
    
    int update(UserPower userPower);
    
    int deleteById(Integer id);
    
    UserPower getByUserId(String userid);
}