package com.foresee.service;


import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.AuthorFollow;
import com.foresee.vo.AuthorFollowVo;
import com.github.pagehelper.PageInfo;

public interface AuthorFollowService extends BasicsSvc<AuthorFollow>{
	PageInfo<AuthorFollowVo> selectAuthorByFollowUserId(int userid,int page,int pageSize);
	PageInfo<AuthorFollowVo> selectFollowMe(int userid,int page,int pageSize);
	
	
	
	

}
