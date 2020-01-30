package com.foresee.ctrl;


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.baseCtrl.BasicsAPICtrl;
import com.foresee.init.InitUtils;
import com.foresee.pojo.ArticleZan;
import com.foresee.pojo.Articles;
import com.foresee.pojo.WechatUser;
import com.foresee.service.ArticleZanService;
import com.foresee.service.ArticlesService;
import com.foresee.service.UserNewsService;
import com.foresee.service.WechatUserService;
import com.foresee.utils.DateUtils;
import com.foresee.utils.JSONResult;
import com.foresee.utils.QueryParameter;
import com.foresee.utils.StringUtil;
import com.foresee.vo.ArticleVo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="文章业务API",tags= {"文章业务API"})
@RequestMapping("/articles")
public class ArticlesCtrl  extends BasicsAPICtrl{
	
	@Autowired
	private ArticlesService articlesService;
	@Autowired
	private WechatUserService  wechatUserService;
	@Autowired
	private UserNewsService userNewsService;
	@Autowired
	private ArticleZanService  articleZanService;
	
	@ApiOperation(value="推荐文章查询",notes="用户首页点击“更多”的时候，调用接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="selectDesc", value="查询时输入的查询内容",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="orderBy", value="排序方式：recommend_date最新、read_count最热",required = false,
		dataType = "String",paramType = "query")
		
	})
	@RequestMapping(value="/recommend",method = RequestMethod.GET)
	public JSONResult recommend(String selectDesc,int page,int pageSize,String orderBy) {
		//查找审核通过的 且是首页推荐的文章
		if(StringUtil.isBlank(orderBy)) {
			orderBy="recommend_date";
		}
		PageInfo<Articles> pageInfo = articlesService.selectRecommend(selectDesc, page, pageSize,orderBy);
		return JSONResult.ok(pageInfo);
	}
	
	
	@ApiOperation(value="小程序首页",notes="首页接口")
	@ApiImplicitParam(name="recommendDate", value="格式：yyyy-MM-dd",required = true,
	dataType = "String",paramType = "query")
	@RequestMapping(value="/index",method = RequestMethod.GET)
	public JSONResult index(String recommendDate) {
		
		if(recommendDate.equals(DateUtils.formatDate(new Date()))) {
			if(InitUtils.articleList.size()<=0) {
				return JSONResult.ok(articlesService.selectHome());
			}
			return JSONResult.ok(InitUtils.articleList);
		}
		if(InitUtils.articleMap.get(recommendDate) != null) {
			if(InitUtils.articleMap.get(recommendDate).size()>0) {
				return JSONResult.ok(InitUtils.articleMap.get(recommendDate));
			}
		}
		Date dates =DateUtils.getDate(recommendDate);
		return JSONResult.ok(articlesService.selectHomeByDate(dates));
	}
	
	@ApiOperation(value="新建文章",notes="新建文章接口")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public JSONResult insert(@RequestBody Articles articles) {
		
		if(StringUtil.isBlank(articles.getOpenid())) {
			return JSONResult.error("请先授权登录");
		}
		if(StringUtil.notBlank(articles.getId())) {
			articlesService.update(articles);
			return JSONResult.ok();
		}
		WechatUser user = new WechatUser();
		user.setOpenId(articles.getOpenid());
		WechatUser wu = wechatUserService.selectOne(user);
		if(StringUtil.isBlank(wu.getCommunityid())) {
			return JSONResult.errorMsg("您还没有成功加入社群");
		}
		if(articles.getIsRecommend().equals("0")) {
			articles.setFlowSts("4");
			articles.setFlowDate(new Date());
		}
		articles.setUserid(wu.getId());
		articles.setCommunityId(wu.getCommunityid());
		//articles.setRecommendDate(DateUtils.getDate(DateUtils.formatDate(articles.getRecommendDate())));
		articles.setCreatedDate(new Date());
		articles.setReadCount(0);
		articles.setFollowCount(0);
		articles.setArticleZan(0);
		articles.setCreatedBy(wu.getId()+"");
		
		articlesService.insert(articles);
		//推荐首页的时候：
		/*if(articles.getIsRecommend().equals("1")) {
			UserNews userNews= new UserNews();
			userNews.setCreatedBy(articles.getUserid()+"");
			userNews.setCreatedDate(new Date());
			userNews.setIsDeleted(false);
			userNews.setNewsContent(articles.getArticleTitle());
			userNews.setNewsTitle("推荐文章申请");
			userNews.setNewsSts("0");
			userNews.setNewsType("7");
			userNews.setUserid(articles.getUserid());
			userNewsService.insert(userNews);
		}*/
		return JSONResult.ok();
	}
	
	
	
	@ApiOperation(value="修改文章",notes="修改文章接口")
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public JSONResult update(@RequestBody Articles articles) {
		articlesService.update(articles);
		return JSONResult.ok();
	}
	
	@ApiOperation(value="删除文章",notes="删除文章接口")
	@RequestMapping(value="/delete",method = RequestMethod.POST)
	public JSONResult delete(@RequestBody Articles articles) {
		articlesService.delete(articles);;
		return JSONResult.ok();
	}
	
	@ApiOperation(value="根据id查询文章详情",notes="根据id查询文章详情接口")
	@ApiImplicitParam(name="id", value="文章id",required = true,
	dataType = "String",paramType = "query")
	@RequestMapping(value="/selectById",method = RequestMethod.GET)
	public JSONResult selectById(String id) {
		return JSONResult.ok(articlesService.selectByid(id));
	}
	@ApiOperation(value="根据id查询文章视图：包括作者信息和社群信息",notes="根据id查询文章视图：包括作者信息和社群信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="文章id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="userid", value="当前用户id",required = false,
		dataType = "String",paramType = "query")
	})
	@RequestMapping(value="/selectVoById",method = RequestMethod.GET)
	public JSONResult selectVoById(int id,String userid) {
		
		ArticleVo vo =articlesService.selectVoById(id,userid);
		Articles articles = new Articles();
		articles.setId(vo.getId());
		articles.setReadCount(vo.getReadCount()+1);
		if(StringUtil.notBlank(userid)) {
			ArticleZan zan = new ArticleZan();
			zan.setArticleid(id);
			zan.setUserid(Integer.parseInt(userid));
			int isZan = articleZanService.selectCount(zan);
			vo.setIsZan(isZan);
		}else {
			vo.setIsZan(0);
		}
		
		articlesService.update(articles);
		return JSONResult.ok(vo);
	}
	
	@ApiOperation(value="根据社群id查询文章列表",notes="根据社群id查询文章列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name="communityid", value="社群id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
		
	})
	
	@RequestMapping(value="/selectByCommunityId",method = RequestMethod.GET)
	public JSONResult selectByCommunityId(int communityid,int page,int pageSize) {
		
		PageInfo<ArticleVo> info = articlesService.selectListByCommunityId(communityid, page, pageSize);
		return JSONResult.ok(info);
	}
	
	@ApiOperation(value="根据文集id查询文章列表",notes="根据文集id查询文章列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name="gatherId", value="文集id",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="userid", value="用户id",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
		
	})
	@RequestMapping(value="/selectByGatherId",method = RequestMethod.GET)
	public JSONResult selectByGatherId(String gatherId,int userid,int page,int pageSize) {
		
		if(StringUtil.isBlank(gatherId)) {
			return JSONResult.ok(articlesService.selectNotGether(userid, page, pageSize));
		}
		PageInfo<ArticleVo> info = articlesService.selectByGetherList(userid,Integer.parseInt(gatherId), page, pageSize);
		return JSONResult.ok(info);
	}
	
	@ApiOperation(value="根据文集id查询文章列表(只查询社刊选中的文章)",notes="根据文集id查询文章列表(只查询社刊选中的文章)")
	@ApiImplicitParams({
		@ApiImplicitParam(name="gatherId", value="文集id",required = true,
				dataType = "String",paramType = "query"),
		
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
		
	})
	@RequestMapping(value="/selectGatherDetail",method = RequestMethod.GET)
	public JSONResult selectGatherDetail(String gatherId,int page,int pageSize) {

		PageInfo<ArticleVo> info = articlesService.selectGetherDetail(Integer.parseInt(gatherId), page, pageSize);
		return JSONResult.ok(info);
	}
	
	@ApiOperation(value="根据文集id查询文章列表",notes="根据文集id查询文章列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name="gatherId", value="文集id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="userid", value="用户id",required = true,
		dataType = "int",paramType = "query")
		
	})
	@RequestMapping(value="/selectGatherArticles",method = RequestMethod.GET)
	public JSONResult selectGatherArticles(int gatherId,int userid) {
		QueryParameter qp = new QueryParameter();
		qp.addParamter("gatherId", gatherId);
		qp.addParamter("isDeleted", false);
		qp.addParamter("isDraft", "0");
		qp.addParamter("userid", userid);
		return JSONResult.ok(articlesService.selectList(qp));
	}
	
	@ApiOperation(value="查询推荐文章列表",notes="查询推荐文章列表")
	@ApiImplicitParams({
		
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
		
	})
	@RequestMapping(value="/selectRecommendList",method = RequestMethod.GET)
	public JSONResult selectRecommendList(int page,int pageSize) {
		PageInfo<ArticleVo> info = articlesService.selectRecommendList(page, pageSize);
		return JSONResult.ok(info);
	}
	

	@ApiOperation(value="查询草稿箱列表",notes="查询草稿箱列表")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="用户id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
		
	})
	@RequestMapping(value="/selectTempList",method = RequestMethod.GET)
	public JSONResult selectTempList(int userId,int page,int pageSize) {
		
		PageInfo<ArticleVo> info = articlesService.selectTempList(userId, page, pageSize);
		return JSONResult.ok(info);
	}
	
	
	@ApiOperation(value="点赞",notes="点赞")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="文章id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="userid", value="用户id",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="type", value="是否点赞",required = true,
		dataType = "String",paramType = "query")
		
	})
	
	@RequestMapping(value="/articleZan",method = RequestMethod.GET)
	public JSONResult articleZan(int id,int userid,String type) {
		Articles a = articlesService.selectByid(id+"");
		if(type.equals("1")) {
			a.setArticleZan(a.getArticleZan()+1);
		}else {
			a.setArticleZan(a.getArticleZan()-1);
		}
		articlesService.update(a);
		
		if(type.equals("1")) {
			ArticleZan zan=new ArticleZan();
			zan.setCreatedDate(new Date());
			zan.setArticleid(id);
			zan.setUserid(userid);
			articleZanService.insert(zan);
		}else {
			ArticleZan zan=new ArticleZan();
			zan.setArticleid(id);
			zan.setUserid(userid);
			articleZanService.delete(zan);
		}
		
		return JSONResult.ok(a.getArticleZan());
	}
	
	@ApiOperation(value="点赞",notes="点赞")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="用户id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="type", value="是否点赞",required = true,
		dataType = "String",paramType = "query")
		
	})
	
	@RequestMapping(value="/addArticleToGatherId",method = RequestMethod.GET)
	public JSONResult addArticleToGatherId(String delId,String addId,int gatherId,int userid) {
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		
		if(StringUtil.notBlank(delId)) {
			List<String> listDelId = Arrays.asList(delId.split(",")); 
			for(String id:listDelId) {
				Articles a =new Articles();
				a.setId(Integer.parseInt(id));
				
				articlesService.updateById(a);
			}
		}
		if(StringUtil.notBlank(addId)) {
			List<String> listAddId = Arrays.asList(addId.split(",")); 
			for(String id:listAddId) {
				Articles a =new Articles();
				a.setId(Integer.parseInt(id));
				a.setGatherId(gatherId);
				articlesService.update(a);
			}
		}
		
		
		return JSONResult.ok();
	}
	@RequestMapping(value="/delArticleToGatherId",method = RequestMethod.GET)
	public JSONResult delArticleToGatherId(String delId,int userid) {
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		
		if(StringUtil.notBlank(delId)) {
			Articles a =new Articles();
			a.setId(Integer.parseInt(delId));
			articlesService.updateById(a);
		}
		

		return JSONResult.ok();
	}
	

}
