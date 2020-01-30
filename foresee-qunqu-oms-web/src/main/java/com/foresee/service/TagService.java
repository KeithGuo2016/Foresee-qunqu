package com.foresee.service;

import java.util.List;

import com.foresee.model.Tag;

public interface TagService {
	int deleteByPrimaryKey(Integer id);

    int insert(Tag record);

    int insertSelective(Tag record);

    Tag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tag record);

    int updateByPrimaryKey(Tag record);
    
    List<Tag> selectList(Tag record);
}
