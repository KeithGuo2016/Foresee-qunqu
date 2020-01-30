package com.foresee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.service.UserPowerService;
import com.foresee.dao.UserPowerMapper;
import com.foresee.model.UserPower;

/**
 * @version 1.0
 * @author wrh
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserPowerServiceImpl implements UserPowerService {
	
    @Autowired private UserPowerMapper userPowerMapper;
    
	public int add(UserPower userPower) {
		if(null == userPower){
			return 0;
		}
        int rows = userPowerMapper.insert(userPower);
        return rows;
	}

    
    public int update(UserPower userPower) {
		if(null == userPower){
			return 0;
		}
        int rows = userPowerMapper.update(userPower);
        return rows;
    }
    
    
    public int delete(Integer id) {
		if(null == id){
			return 0;
		}
        int rows = userPowerMapper.deleteById(id);
        return rows;
    }
    
    
    public UserPower getById(Integer id) {
		if(null == id){
			return null;
		}
		UserPower userPower = userPowerMapper.getById(id);
        return userPower;
    }
    
	public List<UserPower> listPage(UserPower userPower){
		List<UserPower> lists = userPowerMapper.listPage(userPower);
		return lists;
	}
}
