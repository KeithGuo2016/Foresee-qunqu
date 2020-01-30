package com.foresee.dao;

import java.util.List;

import com.foresee.model.Roles;

public interface RolesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Roles record);

    int insertSelective(Roles record);

    Roles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roles record);

    int updateByPrimaryKey(Roles record);
    
    List<Roles> selectRolesList(Roles record);
}