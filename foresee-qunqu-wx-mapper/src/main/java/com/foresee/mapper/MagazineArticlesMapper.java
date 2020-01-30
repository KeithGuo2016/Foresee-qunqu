package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.pojo.MagazineArticles;
import com.foresee.utils.MyMapper;

public interface MagazineArticlesMapper extends MyMapper<MagazineArticles> {
	public List<MagazineArticles> selectListByMagazinesId(@Param("magazinesid")int magazinesid);
}