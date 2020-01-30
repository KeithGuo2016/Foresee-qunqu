package com.foresee.service;

import java.util.List;

import com.foresee.model.Articles;
import com.foresee.model.User;

public interface ArticlesService {
    int deleteByPrimaryKey(Integer id);

    int insert(Articles record);

    int insertSelective(Articles record,User user);

    Articles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Articles record,User user);

    int updateByPrimaryKeyWithBLOBs(Articles record);

    int updateByPrimaryKey(Articles record);
    
    List<Articles> selectList(Articles record);
    
    boolean applyArticlesRecommend(User user, int id, String isRecommend);
    
    boolean applyArticles(User user, int id);
}