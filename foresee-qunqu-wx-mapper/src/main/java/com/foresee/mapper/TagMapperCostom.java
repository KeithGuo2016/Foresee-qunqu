package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.utils.MyMapper;
import com.foresee.vo.TagVo;

public interface TagMapperCostom extends MyMapper<TagVo> {
	
	public List<TagVo> selectArticlesTagLike(@Param("userid")int userid);
	public List<TagVo> selectCommunitysTagLike(@Param("userid")int userid);
	public List<TagVo> selectContributeTagLike(@Param("userid")int userid);
	public List<TagVo> selectMagazineTagLike(@Param("userid")int userid);
	
	
}