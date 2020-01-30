package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.UserGatherMapperCostom;
import com.foresee.pojo.UserGather;
import com.foresee.service.UserGatherService;
import com.foresee.vo.UserGatherVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserGatherServiceImpl extends BasicsSvcImpl<UserGather> implements UserGatherService{
	@Autowired
	private UserGatherMapperCostom costom;
	
	@Override
	public PageInfo<UserGatherVo> selectMyGather(int userid,int page,int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<UserGatherVo> list = costom.selectMyGather(userid);
		PageInfo<UserGatherVo> pageInfo = new PageInfo<UserGatherVo>(list);
		return pageInfo;
	}

}
