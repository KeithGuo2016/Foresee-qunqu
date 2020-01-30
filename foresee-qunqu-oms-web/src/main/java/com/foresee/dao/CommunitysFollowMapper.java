package com.foresee.dao;

import com.foresee.model.CommunitysFollow;

public interface CommunitysFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunitysFollow record);

    int insertSelective(CommunitysFollow record);

    CommunitysFollow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunitysFollow record);

    int updateByPrimaryKey(CommunitysFollow record);
}