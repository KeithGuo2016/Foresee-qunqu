package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.utils.MyMapper;
import com.foresee.vo.CommunityVo;
import com.foresee.vo.ContributesVo;

public interface ContributesMapperCostom extends MyMapper<ContributesVo> {
	public List<ContributesVo> selectAllListHome(@Param("communityId")String communityId);
	
	public ContributesVo selectVoById(@Param("id")int id);
	public List<ContributesVo> searchContributes(@Param("tagId") String tagId,@Param("searchDesc") String searchDesc,@Param("communityId")String communityId);
	
	public List<ContributesVo> selectListCommunityId(@Param("communityId")int communityId);
}