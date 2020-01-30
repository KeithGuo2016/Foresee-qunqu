package com.foresee.service;

import com.foresee.model.ContributeDelivery;

public interface ContributeDeliveryService {
    int deleteByPrimaryKey(Integer id);

    int insert(ContributeDelivery record);

    int insertSelective(ContributeDelivery record);

    ContributeDelivery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ContributeDelivery record);

    int updateByPrimaryKey(ContributeDelivery record);
}