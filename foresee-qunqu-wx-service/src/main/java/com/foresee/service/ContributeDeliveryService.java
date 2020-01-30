package com.foresee.service;


import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.ContributeDelivery;
import com.foresee.vo.ContributeDeliveryVo;
import com.github.pagehelper.PageInfo;

public interface ContributeDeliveryService extends BasicsSvc<ContributeDelivery>{
	PageInfo<ContributeDeliveryVo> selectByContributesId(int contributesId,int page,int pageSize);
	ContributeDeliveryVo selectVoById(int id);
}
