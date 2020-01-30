package com.foresee.dao;

import java.util.List;

import com.foresee.model.Communitys;

public interface CommunitysMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Communitys record);

    int insertSelective(Communitys record);

    Communitys selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Communitys record);

    int updateByPrimaryKey(Communitys record);
    
    List<Communitys> selectList(Communitys record);
}