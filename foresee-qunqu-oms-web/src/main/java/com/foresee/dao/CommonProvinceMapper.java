package com.foresee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.model.CommonProvince;

public interface CommonProvinceMapper {
    int insert(CommonProvince record);

    int insertSelective(CommonProvince record);
    
    List<CommonProvince> allProvince(@Param("id") Integer id);
}