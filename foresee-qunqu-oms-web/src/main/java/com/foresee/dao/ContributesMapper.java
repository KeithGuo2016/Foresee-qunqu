package com.foresee.dao;

import java.util.List;

import com.foresee.model.Contributes;
import com.foresee.model.ContributesWithBLOBs;

public interface ContributesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContributesWithBLOBs record);

    int insertSelective(ContributesWithBLOBs record);

    ContributesWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContributesWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ContributesWithBLOBs record);

    int updateByPrimaryKey(Contributes record);
    
    List<ContributesWithBLOBs> selectList(ContributesWithBLOBs record);
}