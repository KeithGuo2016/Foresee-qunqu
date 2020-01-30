package com.foresee.service;



import org.apache.ibatis.annotations.Param;

import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.Contributes;
import com.foresee.vo.ContributesVo;
import com.github.pagehelper.PageInfo;

public interface ContributesService extends BasicsSvc<Contributes>{
	
	public PageInfo<Contributes> selectAllList(String communityId,int page,int pageSize);
	PageInfo<ContributesVo> selectAllListHome(String communityId,int page,int pageSize);
	ContributesVo selectVoById(int id);
	
	PageInfo<ContributesVo> searchContributes(String tagId,String searchDesc,String communityId,int page,int pageSize);
	PageInfo<ContributesVo> selectListCommunityId(int communityId,int page,int pageSize);

}
