package com.foresee.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.baseCtrl.BasicsAPICtrl;
import com.foresee.pojo.UserNews;
import com.foresee.service.UserNewsService;
import com.foresee.utils.JSONResult;
import com.foresee.utils.QueryParameter;
import com.foresee.vo.ArticleVo;
import com.foresee.vo.UserNewsVo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@RestController
@Api(value="消息业务",tags= {"消息业务接口"})
@RequestMapping("/news")
public class UserNewsCtrl extends BasicsAPICtrl{
	
	@Autowired
	private UserNewsService userNewsService;
	
	@ApiOperation(value="根据登录用户id查询消息",notes="根据登录用户id查询消息")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userid", value="登录用户id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
		
	})
	
	@RequestMapping(value="/selectMyNews",method = RequestMethod.GET)
	public JSONResult selectMyNews(int userid,int page,int pageSize) {
		PageInfo<UserNewsVo> info = userNewsService.selectMyNews(userid, page, pageSize);
		return JSONResult.ok(info);
	}

}
