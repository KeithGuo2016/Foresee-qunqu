package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.MagazineArticlesMapper;
import com.foresee.pojo.MagazineArticles;
import com.foresee.service.MagazineArticlesService;
@Service
public class MagazineArticlesServiceImpl extends BasicsSvcImpl<MagazineArticles> implements MagazineArticlesService{
	@Autowired 
	private  MagazineArticlesMapper mapper;
	@Override
	public List<MagazineArticles> selectListByMagazinesId(int magazinesid) {
		
		return mapper.selectListByMagazinesId(magazinesid);
	}

}
