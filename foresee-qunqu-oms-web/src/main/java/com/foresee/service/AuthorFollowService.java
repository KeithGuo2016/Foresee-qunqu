package com.foresee.service;

import com.foresee.model.AuthorFollow;

public interface AuthorFollowService {
    int deleteByPrimaryKey(Integer id);

    int insert(AuthorFollow record);

    int insertSelective(AuthorFollow record);

    AuthorFollow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AuthorFollow record);

    int updateByPrimaryKey(AuthorFollow record);
}