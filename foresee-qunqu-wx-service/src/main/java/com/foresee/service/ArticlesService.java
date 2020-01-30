package com.foresee.service;



import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.Articles;
import com.foresee.vo.ArticleVo;
import com.github.pagehelper.PageInfo;

public interface ArticlesService extends BasicsSvc<Articles> {
	PageInfo<Articles>  selectRecommend(String selectDesc,int page,int pageSize,String orderBy);
	ArticleVo selectVoById(int id,String userid);
	List<ArticleVo> selectHome();
	
	List<ArticleVo>  selectHomeByDate(Date dates);
	public PageInfo<ArticleVo> selectListByFollowUserId(int userid,int page,int pageSize);
	public PageInfo<ArticleVo> selectRecommendList(int page,int pageSize);
	
	public PageInfo<ArticleVo> selectListByUserId(int userid,int page,int pageSize);
	
	public PageInfo<ArticleVo> searchRecommendArticles(String tagId, String searchDesc,int page,int pageSize);
	public PageInfo<ArticleVo> searchArticles(String tagId, String searchDesc,int page,int pageSize);
	public PageInfo<ArticleVo>  selectListByCommunityId(int communityid,int page,int pageSize);
	public PageInfo<ArticleVo>  selectTempList(int userid,int page,int pageSize);
	public PageInfo<ArticleVo> selectByGetherList(int userid,int gatherId,int page,int pageSize);
	void	updateById(Articles articles);
	public PageInfo<ArticleVo> selectNotGether(int userid, int page, int pageSize);
	public PageInfo<ArticleVo> selectGetherDetail(int gatherId, int page, int pageSize);
	
}
