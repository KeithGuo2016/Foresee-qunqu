package com.foresee.service;

import java.util.List;

import com.foresee.model.Communitys;
import com.foresee.model.User;

public interface CommunitysService {
    int deleteByPrimaryKey(Integer id);

    int insert(Communitys record);

    int insertSelective(Communitys record);

    Communitys selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Communitys record);

    int updateByPrimaryKey(Communitys record);
    
    List<Communitys> selectList(Communitys record);
    
    int adoptCommunitys(User user,int id);
    
    int refuseCommunitys(User user,int id,String newsContent);
    int updateIsDeleted(Communitys record);
}