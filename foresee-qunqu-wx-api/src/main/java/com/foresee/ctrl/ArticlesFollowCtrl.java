package com.foresee.ctrl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.pojo.Articles;
import com.foresee.pojo.ArticlesFollow;
import com.foresee.service.ArticlesFollowService;
import com.foresee.service.ArticlesService;
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
@Api(value="用户关注文章业务API",tags= {"用户关注文章业务API接口类"})
@RequestMapping("/articlesFollow")
public class ArticlesFollowCtrl {

	@Autowired
	private ArticlesFollowService articlesFollowService;
	@Autowired
	private ArticlesService articlesService;
	
	@ApiOperation(value="用户关注文章",notes="用户关注文章接口")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public JSONResult insert(@RequestBody ArticlesFollow articlesFollow) {
		if(StringUtil.isBlank(articlesFollow.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		ArticlesFollow af = articlesFollowService.selectOne(articlesFollow);
		if(af == null) {
			articlesFollow.setCreatedDate(new Date());
			articlesFollow.setCreatedBy(articlesFollow.getUserid()+"");
			articlesFollowService.insert(articlesFollow);
			Articles a = new Articles();
			a.setId(articlesFollow.getArticleId());
			a.setFollowCount(articlesFollow.getFollowNum()+1);
			articlesService.update(a);
		}else {
			
			af.setIsDeleted(false);
			af.setUpdatedDate(new Date());
			articlesFollowService.update(af);
			Articles a = new Articles();
			a.setId(articlesFollow.getArticleId());
			a.setFollowCount(articlesFollow.getFollowNum()+1);
			articlesService.update(a);
		}
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="用户取消关注文章",notes="用户取消关注文章接口")
	@RequestMapping(value="/cancelFollow",method = RequestMethod.POST)
	public JSONResult cancelFollow(@RequestBody ArticlesFollow articlesFollow) {
		if(StringUtil.isBlank(articlesFollow.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		ArticlesFollow af = articlesFollowService.selectOne(articlesFollow);
		af.setIsDeleted(true);
		af.setUpdatedDate(new  Date());
		af.setUpdatedBy(articlesFollow.getUserid()+"");
		articlesFollowService.update(af);
		Articles a = new Articles();
		a.setId(articlesFollow.getArticleId());
		a.setFollowCount(articlesFollow.getFollowNum()-1);
		articlesService.update(a);
		return JSONResult.ok();
	}
	
	
	@ApiOperation(value="用户自己关注的文章列表",notes="用户自己关注的文章列表接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="用户id",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前页码",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
		
	})
	@RequestMapping(value="/meFollow",method = RequestMethod.GET)
	public JSONResult meFollow(String userId,int page,int pageSize) {
		if(StringUtil.isBlank(userId)) {
			return JSONResult.error("请先授权登录");
		}
		PageInfo<ArticleVo> info = articlesService.selectListByFollowUserId(Integer.parseInt(userId), page, pageSize);
		
		return JSONResult.ok(info);
	}
	
}
