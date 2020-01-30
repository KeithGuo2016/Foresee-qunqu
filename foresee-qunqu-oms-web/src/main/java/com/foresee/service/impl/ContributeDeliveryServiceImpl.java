package com.foresee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.ContributeDeliveryMapper;
import com.foresee.model.ContributeDelivery;
import com.foresee.service.ContributeDeliveryService;
@Service
@Transactional(rollbackFor = Exception.class)
public class ContributeDeliveryServiceImpl implements ContributeDeliveryService{

	@Autowired ContributeDeliveryMapper contributeDeliveryDao;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return contributeDeliveryDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ContributeDelivery record) {
		// TODO Auto-generated method stub
		return contributeDeliveryDao.insert(record);
	}

	@Override
	public int insertSelective(ContributeDelivery record) {
		// TODO Auto-generated method stub
		return contributeDeliveryDao.insertSelective(record);
	}

	@Override
	public ContributeDelivery selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return contributeDeliveryDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ContributeDelivery record) {
		// TODO Auto-generated method stub
		return contributeDeliveryDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ContributeDelivery record) {
		// TODO Auto-generated method stub
		return contributeDeliveryDao.updateByPrimaryKey(record);
	}
}