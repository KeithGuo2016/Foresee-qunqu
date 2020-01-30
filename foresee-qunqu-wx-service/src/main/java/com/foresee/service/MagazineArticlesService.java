package com.foresee.service;

import java.util.List;

import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.MagazineArticles;

public interface MagazineArticlesService extends BasicsSvc<MagazineArticles>{
	public List<MagazineArticles> selectListByMagazinesId(int magazinesid);

}
