package com.foresee.dao;

import java.util.List;

import com.foresee.model.CommunityFamily;

public interface CommunityFamilyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommunityFamily record);

    int insertSelective(CommunityFamily record);

    CommunityFamily selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommunityFamily record);

    int updateByPrimaryKey(CommunityFamily record);
    
    List<CommunityFamily> selectList(CommunityFamily record);
}