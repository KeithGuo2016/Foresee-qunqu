package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.UserNewsMapperCostom;
import com.foresee.pojo.UserNews;
import com.foresee.service.UserNewsService;
import com.foresee.vo.UserNewsVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class UserNewsServiceImpl extends BasicsSvcImpl<UserNews> implements UserNewsService{

	@Autowired
	private UserNewsMapperCostom userNewsMapperCostom;
	@Override
	public PageInfo<UserNewsVo> selectMyNews(int userid, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<UserNewsVo> list =userNewsMapperCostom.selectMyNews(userid);
		PageInfo<UserNewsVo> pageInfo = new PageInfo<UserNewsVo>(list);
		return pageInfo;
	}

}
