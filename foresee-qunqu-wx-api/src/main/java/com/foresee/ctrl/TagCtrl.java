package com.foresee.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.baseCtrl.BasicsAPICtrl;
import com.foresee.pojo.Tag;
import com.foresee.service.TagService;
import com.foresee.utils.JSONResult;
import com.foresee.utils.StringUtil;
import com.foresee.vo.TagListVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@RestController
@Api(value="标签业务",tags= {"标签查询API"})
@RequestMapping("/tag")
public class TagCtrl extends BasicsAPICtrl{
	
	@Autowired
	private TagService tagService;
	
	
	
	
	@ApiOperation(value="搜索页面标签查询：包含全部标签和喜欢的标签",notes="搜索页面标签查询：包含全部标签和喜欢的标签")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userid", value="当前登录用户id",required = true,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="type", value="查询标签类型（0社群，1文章  3社刊 5征稿）",required = true,
		dataType = "String",paramType = "query")
	})
	@RequestMapping(value="/searchTagAndLikeTag",method = RequestMethod.GET)
	public JSONResult searchTagAndLikeTag(String userid,String type) {
		TagListVo vo = new TagListVo();
		vo.setTagType(type);
		if(StringUtil.isBlank(userid)) {
			vo.setLikeTags(null);
		}else {
			if(type.equals("0")) {
				vo.setLikeTags(tagService.selectCommunitysTagLike(Integer.parseInt(userid)));
			}
			if(type.equals("1")) {
				vo.setLikeTags(tagService.selectArticlesTagLike(Integer.parseInt(userid)));
			}
			if(type.equals("3")) {
				vo.setLikeTags(tagService.selectMagazineTagLike(Integer.parseInt(userid)));
			}
			if(type.equals("5")) {
				vo.setLikeTags(tagService.selectContributeTagLike(Integer.parseInt(userid)));
			}
		}
		
		
		Tag t=new Tag();
		t.setTagType(type);
		t.setIsDeleted(false);
		vo.setTags(tagService.selectList(t));
		return JSONResult.ok(vo);
	}
	
	@ApiOperation(value="猜你喜欢：查询出可能喜欢的标签",notes="猜你喜欢：查询出可能喜欢的标签接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userid", value="当前登录用户id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="type", value="查询标签类型（0社群，1文章  3社刊 5征稿）",required = true,
		dataType = "String",paramType = "query")
	})
	@RequestMapping(value="/selectTagLike",method = RequestMethod.GET)
	public JSONResult selectTagLike(int userid,String type) {
		if(type.equals("0")) {
			return JSONResult.ok(tagService.selectCommunitysTagLike(userid));
		}
		if(type.equals("1")) {
			return JSONResult.ok(tagService.selectArticlesTagLike(userid));
		}
		if(type.equals("3")) {
			return JSONResult.ok(tagService.selectMagazineTagLike(userid));
		}
		if(type.equals("5")) {
			return JSONResult.ok(tagService.selectContributeTagLike(userid));
		}
		return JSONResult.ok();
	}
	@ApiOperation(value="根据标签类型查询标签",notes="根据标签类型查询标签接口")
	@ApiImplicitParam(name="type", value="查询标签类型（0社群，1文章 2用户 3社刊 4身份 5征稿）",required = true,
	dataType = "String",paramType = "query")
	@RequestMapping(value="/selectTag",method = RequestMethod.GET)
	public JSONResult selectTag(String type) {
		Tag t=new Tag();
		t.setTagType(type);
		t.setIsDeleted(false);
		return JSONResult.ok(tagService.selectList(t));
	}
	
	@ApiOperation(value="文章标签查询",notes="文章标签查询接口")
	@RequestMapping(value="/selectArticlesTag",method = RequestMethod.GET)
	public JSONResult selectArticlesTag() {
		
		
		Tag t=new Tag();
		t.setTagType("1");
		t.setIsDeleted(false);
		return JSONResult.ok(tagService.selectList(t));
	}
	
	@ApiOperation(value="社群标签查询",notes="社群标签查询接口")
	@RequestMapping(value="/selectCommunitysTag",method = RequestMethod.GET)
	public JSONResult selectCommunitysTag() {
		Tag t=new Tag();
		t.setTagType("0");
		t.setIsDeleted(false);
		return JSONResult.ok(tagService.selectList(t));
	}
	
	
	
	@ApiOperation(value="作者标签查询",notes="作者标签查询接口")
	@RequestMapping(value="/selectWxUserTag",method = RequestMethod.GET)
	public JSONResult selectWxUserTag() {
		Tag t=new Tag();
		t.setTagType("2");
		t.setIsDeleted(false);
		return JSONResult.ok(tagService.selectList(t));
	}
	
	@ApiOperation(value="社刊标签查询",notes="社刊标签查询接口")
	@RequestMapping(value="/selectMagazineTag",method = RequestMethod.GET)
	public JSONResult selectMagazineTag() {
		Tag t=new Tag();
		t.setTagType("3");
		t.setIsDeleted(false);
		return JSONResult.ok(tagService.selectList(t));
	}
	
	@ApiOperation(value="用户身份标签查询",notes="用户身份标签查询接口")
	@RequestMapping(value="/selectUserIdentityTag",method = RequestMethod.GET)
	public JSONResult selectUserIdentityTag() {
		Tag t=new Tag();
		t.setTagType("4");
		t.setIsDeleted(false);
		return JSONResult.ok(tagService.selectList(t));
	}
	
	@ApiOperation(value="征稿标签查询",notes="征稿标签查询接口")
	@RequestMapping(value="/selectContributeTag",method = RequestMethod.GET)
	public JSONResult selectContributeTag() {
		Tag t=new Tag();
		t.setTagType("5");
		t.setIsDeleted(false);
		return JSONResult.ok(tagService.selectList(t));
	}
	
	

}
