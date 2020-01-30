package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.utils.MyMapper;
import com.foresee.vo.CommunityVo;

public interface CommunitysMapperCostom extends MyMapper<CommunityVo> {
	public List<CommunityVo> selectHome();
	
	public CommunityVo selectCommunityVoById(@Param("id")int id,@Param("userid")String userid);
	
	public List<CommunityVo> selectFollowList(@Param("userid")int userid);
	/**
	 * 搜索社群
	 * @param tagId
	 * @param searchDesc
	 * @return
	 */
	public List<CommunityVo> searchCommunitys(@Param("tagId") String tagId,@Param("searchDesc") String searchDesc);
	
	
}