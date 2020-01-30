package com.foresee.ctrl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.pojo.CommunitysFollow;
import com.foresee.service.CommunitysFollowService;
import com.foresee.service.CommunitysService;
import com.foresee.utils.JSONResult;
import com.foresee.utils.QueryParameter;
import com.foresee.utils.StringUtil;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="用户关注社群业务API",tags= {"用户关注社群业务API接口类"})
@RequestMapping("/communitysFollow")
public class CommunitysFollowCtrl {
	
	@Autowired
	private CommunitysFollowService communitysFollowService;
	@Autowired
	private CommunitysService communitysService;
	
	@ApiOperation(value="用户关注社群",notes="用户关注社群接口")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public JSONResult insert(@RequestBody CommunitysFollow communitysFollow) {
		if(StringUtil.isBlank(communitysFollow.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		CommunitysFollow cf = communitysFollowService.selectOne(communitysFollow);
		if(cf == null) {
			communitysFollow.setCreatedDate(new Date());
			communitysFollow.setCreatedBy(communitysFollow.getUserid()+"");
			communitysFollowService.insert(communitysFollow);
		}else {
			cf.setIsDeleted(false);
			cf.setUpdatedDate(new Date());
			cf.setUpdatedBy(communitysFollow.getUserid()+"");
			communitysFollowService.update(cf);
		}
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="用户取消关注社群",notes="用户取消关注社群接口")
	
	@RequestMapping(value="/cancelFollow",method = RequestMethod.POST)
	public JSONResult cancelFollow(@RequestBody CommunitysFollow communitysFollow) {
		if(StringUtil.isBlank(communitysFollow.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		CommunitysFollow cf = communitysFollowService.selectOne(communitysFollow);
		if(cf == null) {
			return JSONResult.ok();
		}
		cf.setIsDeleted(true);
		cf.setUpdatedDate(new  Date());
		cf.setUpdatedBy(communitysFollow.getUserid()+"");
		communitysFollowService.update(cf);
		return JSONResult.ok();
	}
	
	@ApiOperation(value="用户自己关注的社群列表",notes="用户自己关注的社群列表接口")
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
		return JSONResult.ok(communitysService.selectFollowList(Integer.parseInt(userId), page, pageSize));
	}

}
