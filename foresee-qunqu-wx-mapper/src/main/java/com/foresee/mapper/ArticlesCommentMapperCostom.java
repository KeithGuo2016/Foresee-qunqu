package com.foresee.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.utils.MyMapper;
import com.foresee.vo.ArticlesCommentVo;


public interface ArticlesCommentMapperCostom extends MyMapper<ArticlesCommentVo> {
	
	public List<ArticlesCommentVo> selectCommentList(@Param("articlesId")int articlesId);
	
	public List<ArticlesCommentVo> selectToCommentList(@Param("pid")int pid);
	
	public ArticlesCommentVo selectCommentByid(@Param("id")int id);
	public List<ArticlesCommentVo>  selectCommentByDate(@Param("userid")int userid,@Param("articlesId")int articlesId,@Param("content")String content);
	
	public List<ArticlesCommentVo> selectMyComment(@Param("userid")int userid);
}