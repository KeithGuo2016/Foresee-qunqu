package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.utils.MyMapper;
import com.foresee.vo.AuthorFollowVo;

public interface AuthorFollowMapperCostom extends MyMapper<AuthorFollowVo> {
	public List<AuthorFollowVo> selectAuthorByFollowUserId(@Param("userid") int userid);
	public List<AuthorFollowVo> selectFollowMe(@Param("userid") int userid);
	
}