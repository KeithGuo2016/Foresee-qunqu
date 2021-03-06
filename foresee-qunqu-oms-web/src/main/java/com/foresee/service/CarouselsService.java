package com.foresee.service;

import java.util.List;

import com.foresee.model.Carousels;

public interface CarouselsService {
    int deleteByPrimaryKey(Integer id);

    int insert(Carousels record);

    int insertSelective(Carousels record);

    Carousels selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Carousels record);

    int updateByPrimaryKey(Carousels record);
    
    List<Carousels> selectList(Carousels record);
}