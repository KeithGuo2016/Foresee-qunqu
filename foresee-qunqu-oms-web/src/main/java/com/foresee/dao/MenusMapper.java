package com.foresee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.model.Menus;

public interface MenusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Menus record);

    int insertSelective(Menus record);

    Menus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Menus record);

    int updateByPrimaryKey(Menus record);
    
    List<Menus> selectMenuList(Menus record);
    
    List<Menus> selectByParentId(@Param("id") Integer id);
}