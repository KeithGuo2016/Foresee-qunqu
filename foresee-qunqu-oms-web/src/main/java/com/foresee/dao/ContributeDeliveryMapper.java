package com.foresee.dao;

import com.foresee.model.ContributeDelivery;

public interface ContributeDeliveryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ContributeDelivery record);

    int insertSelective(ContributeDelivery record);

    ContributeDelivery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContributeDelivery record);

    int updateByPrimaryKey(ContributeDelivery record);
}