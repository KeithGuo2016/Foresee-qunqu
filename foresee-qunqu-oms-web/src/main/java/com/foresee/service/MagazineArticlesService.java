package com.foresee.service;

import com.foresee.model.MagazineArticles;

public interface MagazineArticlesService {
    int deleteByPrimaryKey(Integer id);

    int insert(MagazineArticles record);

    int insertSelective(MagazineArticles record);

    MagazineArticles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MagazineArticles record);

    int updateByPrimaryKey(MagazineArticles record);
}