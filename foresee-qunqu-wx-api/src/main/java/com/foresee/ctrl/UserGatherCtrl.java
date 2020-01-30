package com.foresee.ctrl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.pojo.Articles;
import com.foresee.pojo.UserGather;
import com.foresee.service.ArticlesService;
import com.foresee.service.UserGatherService;
import com.foresee.utils.JSONResult;
import com.foresee.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="文集业务API",tags= {"文集业务API"})
@RequestMapping("/gather")
public class UserGatherCtrl {
	
	@Autowired
	private UserGatherService userGatherService;
	@Autowired
	private ArticlesService articlesService;
	
	@ApiOperation(value="查询自己的文集",notes="查询自己的文集")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userid", value="用户id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
		
	})
	
	@RequestMapping(value="/selectMyGather",method = RequestMethod.GET)
	public JSONResult selectMyGather(int userid,int page,int pageSize) {
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		return JSONResult.ok(userGatherService.selectMyGather(userid, page, pageSize));
	}
	
	/*@ApiOperation(value="新建文集",notes="新建文集接口")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public JSONResult insert(@RequestBody UserGather userGather) {
		if(StringUtil.isBlank(userGather.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		userGather.setCreatedDate(new Date());
		userGather.setIsDeleted(false);
		userGather.setCreatedBy(userGather.getUserid()+"");
		userGatherService.insert(userGather);
		return JSONResult.ok();
	}*/
	
	
	@ApiOperation(value="新建文集",notes="新建文集接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="gatherName", value="文集名称",required = true,
		dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="gatherIcon", value="文集图标",required = true,
		dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="userid", value="用户id",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="ids", value="文集文章id",required = false,
		dataType = "String",paramType = "query")
	})
	@RequestMapping(value="/insert",method = RequestMethod.GET)
	public JSONResult insert(String gatherName,String gatherIcon,String userid,String ids) {
		
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		UserGather userGather = new UserGather();
		userGather.setCreatedBy(userid);
		userGather.setCreatedDate(new Date());
		userGather.setIsDeleted(false);
		userGather.setGatherIcon(gatherIcon);
		userGather.setGatherName(gatherName);
		userGather.setUserid(Integer.parseInt(userid));
		userGatherService.insert(userGather);
		UserGather ug = new UserGather();
		ug.setGatherIcon(gatherIcon);
		UserGather u =userGatherService.selectOne(ug);
		
		if(StringUtil.notBlank(ids)) {
			
			List<String> listAddId = Arrays.asList(ids.split(",")); 
			for(String aid:listAddId) {
				Articles a =new Articles();
				a.setId(Integer.parseInt(aid));
				a.setGatherId(u.getId());
				articlesService.update(a);
			}
		}
		
		return JSONResult.ok(u);
	}
	
	@ApiOperation(value="新建文集",notes="新建文集接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="gatherName", value="文集名称",required = true,
		dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="gatherIcon", value="文集图标",required = true,
		dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="userid", value="用户id",required = true,
		dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="id", value="文集id",required = true,
		dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/update",method = RequestMethod.GET)
	public JSONResult update(String gatherName,String gatherIcon,String userid,int id) {
		
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		UserGather userGather = new UserGather();
		
		
		
		userGather.setGatherIcon(gatherIcon);
		userGather.setGatherName(gatherName);
		userGather.setId(id);
		userGatherService.update(userGather);
		return JSONResult.ok(userGather);
	}
	@ApiOperation(value="删除文集",notes="删除文集接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="id",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="userid", value="用户id",required = true,
		dataType = "int",paramType = "query")
		
	})
	@RequestMapping(value="/delete",method = RequestMethod.GET)
	public JSONResult delete(int id,int userid) {
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		UserGather ug = new UserGather();
		ug.setUpdatedDate(new Date());
		ug.setId(id);
		ug.setIsDeleted(true);
		userGatherService.update(ug);
		//userGatherService.delete(id);
		return JSONResult.ok();
	}
	

}
