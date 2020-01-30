package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.TagMapper;
import com.foresee.model.Tag;
import com.foresee.service.TagService;
@Service
@Transactional(rollbackFor = Exception.class)
public class TagServiceImpl implements TagService {

	@Autowired TagMapper tag;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return tag.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Tag record) {
		// TODO Auto-generated method stub
		return tag.insert(record);
	}

	@Override
	public int insertSelective(Tag record) {
		// TODO Auto-generated method stub
		return tag.insertSelective(record);
	}

	@Override
	public Tag selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return tag.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Tag record) {
		// TODO Auto-generated method stub
		return tag.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Tag record) {
		// TODO Auto-generated method stub
		return tag.updateByPrimaryKey(record);
	}

	@Override
	public List<Tag> selectList(Tag record) {
		// TODO Auto-generated method stub
		return tag.selectList(record);
	}

}
