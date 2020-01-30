package com.foresee.dao;

import java.util.List;

import com.foresee.model.Roleandmenu;

public interface RoleandmenuMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByRoleid(Integer roleid);
    
    int deleteByMenuid(Integer menuid);

    int insert(Roleandmenu record);

    int insertSelective(Roleandmenu record);

    Roleandmenu selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Roleandmenu record);

    int updateByPrimaryKey(Roleandmenu record);
    
    int insertList(List<Roleandmenu> list);
    
    List<Roleandmenu> selectRMList(Roleandmenu record);
}