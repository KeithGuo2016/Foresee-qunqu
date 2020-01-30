package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.pojo.Articles;
import com.foresee.utils.MyMapper;

public interface ArticlesMapper extends MyMapper<Articles> {
	
	public List<Articles> selectRecommend(@Param("selectDesc")String selectDesc,@Param("orderBy")String orderBy);
    void	updateById(Articles articles);
}