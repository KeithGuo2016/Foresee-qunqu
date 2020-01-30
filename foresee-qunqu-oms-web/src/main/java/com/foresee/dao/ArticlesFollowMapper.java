package com.foresee.dao;

import com.foresee.model.ArticlesFollow;

public interface ArticlesFollowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticlesFollow record);

    int insertSelective(ArticlesFollow record);

    ArticlesFollow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticlesFollow record);

    int updateByPrimaryKey(ArticlesFollow record);
}