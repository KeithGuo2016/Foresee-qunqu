package com.foresee.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.utils.MyMapper;
import com.foresee.vo.ArticleVo;

public interface ArticlesMapperCostom  extends MyMapper<ArticleVo>{

	public ArticleVo selectVoById(@Param("id")int id,@Param("userid")String userid);
	
	public List<ArticleVo> selectHome();
	public  List<ArticleVo> selectHomeByDate(@Param("date") Date dates);
	/**
	 * 根据关注用户id查询关注的文章列表
	 * @param userid
	 * @return
	 */
	public List<ArticleVo> selectListByFollowUserId(@Param("userid") int userid);
	/**
	 * 查询推荐首页的所有文章
	 * @return
	 */
	public List<ArticleVo> selectRecommendList();
	/**
	 * 查询自己发布的文章
	 * @param userid
	 * @return
	 */
	public List<ArticleVo> selectListByUserId(@Param("userid") int userid);
	/**
	 * 搜索首页推荐文章
	 * @param tagId
	 * @param searchDesc
	 * @return
	 */
	public List<ArticleVo> searchRecommendArticles(@Param("tagId") String tagId,@Param("searchDesc") String searchDesc);
	/**
	 * 搜索所有文章
	 * @param tagId
	 * @param searchDesc
	 * @return
	 */
	public List<ArticleVo> searchArticles(@Param("tagId") String tagId,@Param("searchDesc") String searchDesc);
	
	public List<ArticleVo>  selectListByCommunityId(@Param("communityid") int communityid);
	public List<ArticleVo>  selectTempList(@Param("userid") int userid);
	public List<ArticleVo>  selectByGetherList(@Param("userid") int userid,@Param("gatherId") int gatherId);
	public List<ArticleVo>  selectNotGether(@Param("userid") int userid);
	public List<ArticleVo>  selectGetherDetail(@Param("gatherId") int gatherId);
	
}
