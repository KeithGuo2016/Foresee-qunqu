package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.LogMapper;
import com.foresee.model.Log;
import com.foresee.service.LogService;
@Service
@Transactional(rollbackFor = Exception.class)
public class LogServiceImpl implements LogService {

	@Autowired LogMapper log;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return log.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Log record) {
		// TODO Auto-generated method stub
		return log.insert(record);
	}

	@Override
	public int insertSelective(Log record) {
		// TODO Auto-generated method stub
		return log.insertSelective(record);
	}

	@Override
	public Log selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return log.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Log record) {
		// TODO Auto-generated method stub
		return log.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Log record) {
		// TODO Auto-generated method stub
		return log.updateByPrimaryKey(record);
	}

	@Override
	public List<Log> selectList(Log record) {
		// TODO Auto-generated method stub
		return log.selectList(record);
	}

}
