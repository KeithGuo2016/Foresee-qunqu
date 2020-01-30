package com.foresee.dao;

import com.foresee.model.UserGather;

public interface UserGatherMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserGather record);

    int insertSelective(UserGather record);

    UserGather selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserGather record);

    int updateByPrimaryKey(UserGather record);
}