package com.foresee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.baseService.impl.BasicsSvcImpl;
import com.foresee.mapper.ContributeDeliveryMapperCostom;
import com.foresee.pojo.ContributeDelivery;
import com.foresee.service.ContributeDeliveryService;
import com.foresee.vo.ContributeDeliveryVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ContributeDeliveryServiceImpl extends BasicsSvcImpl<ContributeDelivery> implements ContributeDeliveryService{

	@Autowired
	private ContributeDeliveryMapperCostom costom;
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public PageInfo<ContributeDeliveryVo> selectByContributesId(int contributesId,int page,int pageSize) {
		PageHelper.startPage(page, pageSize);
		List<ContributeDeliveryVo> list =costom.selectByContributesId(contributesId);
		PageInfo<ContributeDeliveryVo> pageInfo = new PageInfo<ContributeDeliveryVo>(list);
		return pageInfo;
	}
	@Override
	@Transactional(propagation = Propagation.SUPPORTS)
	public ContributeDeliveryVo selectVoById(int id) {
		return costom.selectVoById(id);
	}

}
