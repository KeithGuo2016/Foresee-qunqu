package com.foresee.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.model.MagazineArticles;

public interface MagazineArticlesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MagazineArticles record);

    int insertSelective(MagazineArticles record);

    MagazineArticles selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MagazineArticles record);

    int updateByPrimaryKey(MagazineArticles record);
    
    void insertSelectiveList(@Param("list")List<MagazineArticles> list);
    
    int deleteByMagazinesId(Integer magazinesId);
}