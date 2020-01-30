package com.foresee.service;

import java.util.List;

import com.foresee.model.Contributes;
import com.foresee.model.ContributesWithBLOBs;
import com.foresee.model.User;

public interface ContributesService {
    int deleteByPrimaryKey(Integer id);

    int insert(ContributesWithBLOBs record);

    int insertSelective(ContributesWithBLOBs record,User account);

    ContributesWithBLOBs selectByPrimaryKey(Integer id);
    
    boolean applyContributes(Integer id,User account);

    int updateByPrimaryKeySelective(ContributesWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ContributesWithBLOBs record);

    int updateByPrimaryKey(Contributes record);
    
    List<ContributesWithBLOBs> selectList(ContributesWithBLOBs record);
    
    boolean applyContributes(User user,int id,String flowSts);
}