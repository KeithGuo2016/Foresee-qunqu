package com.foresee.service;


import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.UserNews;
import com.foresee.vo.UserNewsVo;
import com.github.pagehelper.PageInfo;

public interface UserNewsService extends BasicsSvc<UserNews>{
	public PageInfo<UserNewsVo> selectMyNews(int userid,int page,int pageSize);

}
