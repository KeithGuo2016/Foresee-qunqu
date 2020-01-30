package com.foresee.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.MagazineArticlesMapper;
import com.foresee.model.MagazineArticles;
import com.foresee.service.MagazineArticlesService;
@Service
@Transactional(rollbackFor = Exception.class)
public class MagazineArticlesServiceImpl implements MagazineArticlesService{

	@Autowired MagazineArticlesMapper magazineArticlesDao;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return magazineArticlesDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MagazineArticles record) {
		// TODO Auto-generated method stub
		return magazineArticlesDao.insert(record);
	}

	@Override
	public int insertSelective(MagazineArticles record) {
		// TODO Auto-generated method stub
		return magazineArticlesDao.insertSelective(record);
	}

	@Override
	public MagazineArticles selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return magazineArticlesDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MagazineArticles record) {
		// TODO Auto-generated method stub
		return magazineArticlesDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MagazineArticles record) {
		// TODO Auto-generated method stub
		return magazineArticlesDao.updateByPrimaryKey(record);
	}
}