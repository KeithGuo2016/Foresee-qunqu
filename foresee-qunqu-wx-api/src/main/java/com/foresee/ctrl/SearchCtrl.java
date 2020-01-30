package com.foresee.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.baseCtrl.BasicsAPICtrl;
import com.foresee.service.ArticlesService;
import com.foresee.service.CommunitysService;
import com.foresee.service.ContributesService;
import com.foresee.service.MagazinesService;
import com.foresee.utils.JSONResult;
import com.foresee.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="搜索工具",tags= {"搜索业务API"})
@RequestMapping("/search")
public class SearchCtrl extends BasicsAPICtrl{

	@Autowired
	private ArticlesService articlesService;
	@Autowired
	private CommunitysService communitysService;
	
	@Autowired
	private ContributesService contributesService;
	@Autowired
	private MagazinesService magazinesService;
	
	@ApiOperation(value="搜索接口，根据类型搜索内容",notes="搜索接口，根据类型搜索内容")
	@ApiImplicitParams({
		@ApiImplicitParam(name="searchDesc", value="搜索抗输入内容",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="tagId", value="标签id",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="communityId", value="当前登录用户所在社群id:社刊和征稿查询的时候需要用到",required = false,
		dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="type", value="查询类型（0社群，1文章  3社刊 5征稿），与标签类型一致",required = true,
		dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
				dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/search",method = RequestMethod.GET)
	public JSONResult search(String searchDesc,String tagId,String communityId,String type,int page,int pageSize) {
		
		if(type.equals("0")) {
			return JSONResult.ok(communitysService.searchCommunitys(tagId, searchDesc, page, pageSize));
		}
		if(type.equals("1")) {
			return JSONResult.ok(articlesService.searchRecommendArticles(tagId, searchDesc, page, pageSize));
		}
		if(type.equals("3")) {
			return JSONResult.ok(magazinesService.searchMagazine(tagId, searchDesc, communityId, page, pageSize));
		}
		if(type.equals("5")) {
			return JSONResult.ok(contributesService.searchContributes(tagId, searchDesc, communityId, page, pageSize));
		}
		return JSONResult.ok();
	}
	
	/**
	 * 按照标签、标题、关键词
	 * @param searchDesc
	 * @param tagId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@ApiOperation(value="搜索属于首页推荐的文章",notes="搜索属于首页推荐的文章接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="searchDesc", value="搜索抗输入内容",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="tagId", value="标签id",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
				dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/searchRecommendArticles",method = RequestMethod.GET)
	public JSONResult searchRecommendArticles(String searchDesc,String tagId,int page,int pageSize) {
		
		return JSONResult.ok(articlesService.searchRecommendArticles(tagId, searchDesc, page, pageSize));
	}
	
	@ApiOperation(value="搜索全部的已经发布的文章",notes="搜索全部的已经发布的文章接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="searchDesc", value="搜索抗输入内容",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="tagId", value="标签id",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
				dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/searchArticles",method = RequestMethod.GET)
	public JSONResult searchArticles(String searchDesc,String tagId,int page,int pageSize) {
		
		return JSONResult.ok(articlesService.searchArticles(tagId, searchDesc, page, pageSize));
	}

	@ApiOperation(value="搜索社群",notes="搜索社群接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="searchDesc", value="搜索抗输入内容",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="tagId", value="标签id",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
				dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/searchCommunitys",method = RequestMethod.GET)
	public JSONResult searchCommunitys(String searchDesc,String tagId,int page,int pageSize) {
		
		return JSONResult.ok(communitysService.searchCommunitys(tagId, searchDesc, page, pageSize));
	}
	@ApiOperation(value="搜索征稿",notes="搜索征稿接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="searchDesc", value="搜索抗输入内容",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="tagId", value="标签id",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="communityId", value="当前登录用户所在社群id",required = false,
		dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
				dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/searchContributes",method = RequestMethod.GET)
	public JSONResult searchContributes(String searchDesc,String tagId,String communityId,int page,int pageSize) {
		
		return JSONResult.ok(contributesService.searchContributes(tagId, searchDesc, communityId, page, pageSize));
	}


	
}
