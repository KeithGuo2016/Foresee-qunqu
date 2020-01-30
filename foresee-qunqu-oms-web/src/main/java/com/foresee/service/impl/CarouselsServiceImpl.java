package com.foresee.service.impl;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.CarouselsMapper;
import com.foresee.exception.ResultException;
import com.foresee.model.Carousels;
import com.foresee.service.CarouselsService;
import com.foresee.utils.ResultCode;
@Service
@Transactional(rollbackFor = Exception.class)
public class CarouselsServiceImpl implements CarouselsService{
	@Autowired CarouselsMapper carouselsDao;
	Lock lock = new ReentrantLock();
	
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return carouselsDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Carousels record) {
		// TODO Auto-generated method stub
		return carouselsDao.insert(record);
	}

	@Override
	public int insertSelective(Carousels record) {
		// TODO Auto-generated method stub
		try {
			lock.lock();
			List<Carousels> selectList = carouselsDao.selectList(new Carousels());
			if(selectList.size()>=10){
				throw new ResultException(ResultCode.CAROUSELS_SIZE_ERROR);
			}
			return carouselsDao.insertSelective(record);
		} catch (Exception e) {
			throw e;
		}finally{
			lock.unlock();
		}
		
	}

	@Override
	public Carousels selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return carouselsDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Carousels record) {
		// TODO Auto-generated method stub
		return carouselsDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Carousels record) {
		// TODO Auto-generated method stub
		return carouselsDao.updateByPrimaryKey(record);
	}

	@Override
	public List<Carousels> selectList(Carousels record) {
		// TODO Auto-generated method stub
		return carouselsDao.selectList(record);
	}
}