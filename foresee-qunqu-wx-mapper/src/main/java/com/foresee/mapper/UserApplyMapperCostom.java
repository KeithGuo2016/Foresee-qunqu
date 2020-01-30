package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.utils.MyMapper;
import com.foresee.vo.UserApplyVo;

public interface UserApplyMapperCostom extends MyMapper<UserApplyVo> {
	/**
	 * 根据申请业务对象id查询申请列表
	 * @param communityid
	 * @return
	 */
	public List<UserApplyVo> selectApplyTargetList(@Param("applyTargetId")int applyTargetId,@Param("applyType")String applyType);
	
	public List<UserApplyVo> selectMeApply(@Param("userid")int userid);
	public List<UserApplyVo> selectAddCommunityApply(@Param("userid")int userid);
	public List<UserApplyVo> selectMeApplyAll(@Param("userid")int userid);
}