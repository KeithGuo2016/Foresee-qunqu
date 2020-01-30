package com.foresee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.model.CommonCity;

public interface CommonCityMapper {
    int insert(CommonCity record);

    int insertSelective(CommonCity record);
    
    List<CommonCity> selectByPid(@Param("pid")Integer pid,@Param("id")Integer id);
}