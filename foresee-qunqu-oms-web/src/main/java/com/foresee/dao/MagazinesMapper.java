package com.foresee.dao;

import java.util.List;

import com.foresee.model.Magazines;

public interface MagazinesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Magazines record);

    int insertSelective(Magazines record);

    Magazines selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Magazines record);

    int updateByPrimaryKey(Magazines record);
    
    List<Magazines> selectList(Magazines record);
}