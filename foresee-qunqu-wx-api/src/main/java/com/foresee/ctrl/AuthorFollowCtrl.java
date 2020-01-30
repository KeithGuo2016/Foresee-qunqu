package com.foresee.ctrl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.pojo.AuthorFollow;
import com.foresee.service.AuthorFollowService;
import com.foresee.utils.JSONResult;
import com.foresee.utils.QueryParameter;
import com.foresee.utils.StringUtil;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="用户关注作者业务API",tags= {"用户关注作者业务API接口类"})
@RequestMapping("/authorFollow")
public class AuthorFollowCtrl {
	@Autowired
	private AuthorFollowService authorFollowService;


	@ApiOperation(value="用户关注作者",notes="用户关注作者接口")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public JSONResult insert(@RequestBody AuthorFollow authorFollow) {
		if(StringUtil.isBlank(authorFollow.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		AuthorFollow af = authorFollowService.selectOne(authorFollow);
		if(af == null) {
			authorFollow.setCreatedDate(new Date());
			authorFollow.setCreatedBy(authorFollow.getUserid()+"");
			authorFollowService.insert(authorFollow);
		}else {
			af.setIsDeleted(false);
			af.setUpdatedDate(new Date());
			af.setUpdatedBy(authorFollow.getUserid()+"");
			authorFollowService.update(af);
		}
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="用户取消关注作者",notes="用户取消关注作者接口")
	@RequestMapping(value="/cancelFollow",method = RequestMethod.POST)
	public JSONResult cancelFollow(@RequestBody AuthorFollow authorFollow) {
		if(StringUtil.isBlank(authorFollow.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		AuthorFollow af = authorFollowService.selectOne(authorFollow);
		af.setIsDeleted(true);
		af.setUpdatedDate(new  Date());
		af.setUpdatedBy(authorFollow.getUserid()+"");
		authorFollowService.update(af);
		return JSONResult.ok(af);
	}
	
	@ApiOperation(value="用户自己关注的作者列表",notes="用户自己关注的作者列表接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="用户id",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前页码",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
		
	})
	@RequestMapping(value="/meFollow",method = RequestMethod.POST)
	public JSONResult meFollow(String userId,int page,int pageSize) {
		if(StringUtil.isBlank(userId)) {
			return JSONResult.error("请先授权登录");
		}
		return JSONResult.ok(authorFollowService.selectAuthorByFollowUserId(Integer.parseInt(userId), page, pageSize));
	}
	
	@ApiOperation(value="查询关注自己的用户列表",notes="查询关注自己的用户接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userid", value="用户id",required = false,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前页码",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
		
	})
	@RequestMapping(value="/selectFollowMe",method = RequestMethod.GET)
	public JSONResult selectFollowMe(String userid,int page,int pageSize) {
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		return JSONResult.ok(authorFollowService.selectFollowMe(Integer.parseInt(userid), page, pageSize));
	}
}
