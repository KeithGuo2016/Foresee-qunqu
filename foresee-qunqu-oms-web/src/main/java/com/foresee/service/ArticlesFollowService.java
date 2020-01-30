package com.foresee.service;

import com.foresee.model.ArticlesFollow;

public interface ArticlesFollowService {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticlesFollow record);

    int insertSelective(ArticlesFollow record);

    ArticlesFollow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticlesFollow record);

    int updateByPrimaryKey(ArticlesFollow record);
}