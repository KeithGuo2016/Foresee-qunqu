package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.AuthorFollowMapperCostom;
import com.foresee.pojo.AuthorFollow;
import com.foresee.service.AuthorFollowService;
import com.foresee.vo.AuthorFollowVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class AuthorFollowServiceImpl  extends BasicsSvcImpl<AuthorFollow> implements AuthorFollowService{

	@Autowired 
	private AuthorFollowMapperCostom costom;
	@Override
	public PageInfo<AuthorFollowVo> selectAuthorByFollowUserId(int userid,int page,int pageSize) {
		PageHelper.startPage(page, pageSize);
		
		List<AuthorFollowVo> list =costom.selectAuthorByFollowUserId(userid);
		PageInfo<AuthorFollowVo> pageInfo = new PageInfo<AuthorFollowVo>(list);
		return pageInfo;
	}
	@Override
	public PageInfo<AuthorFollowVo> selectFollowMe(int userid, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		
		List<AuthorFollowVo> list =costom.selectFollowMe(userid);
		PageInfo<AuthorFollowVo> pageInfo = new PageInfo<AuthorFollowVo>(list);
		return pageInfo;
	}

}
