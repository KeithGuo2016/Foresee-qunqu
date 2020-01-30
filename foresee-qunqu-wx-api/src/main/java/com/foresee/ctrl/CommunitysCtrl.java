package com.foresee.ctrl;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.foresee.baseCtrl.BasicsAPICtrl;
import com.foresee.pojo.Articles;
import com.foresee.pojo.Communitys;
import com.foresee.pojo.CommunitysFollow;
import com.foresee.pojo.UserNews;
import com.foresee.pojo.WechatUser;
import com.foresee.service.ArticlesService;
import com.foresee.service.CommunitysFollowService;
import com.foresee.service.CommunitysService;
import com.foresee.service.UserNewsService;
import com.foresee.service.WechatUserService;
import com.foresee.utils.CreateQrcore;
import com.foresee.utils.JSONResult;
import com.foresee.utils.QueryCondition;
import com.foresee.utils.QueryParameter;
import com.foresee.utils.StringUtil;
import com.foresee.vo.CommunityVo;
import com.foresee.vo.WechatUserVo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
@RestController
@Api(value="社群业务",tags= {"社群业务接口"})
@RequestMapping("/communitys")
public class CommunitysCtrl extends BasicsAPICtrl{
	@Autowired
	private CommunitysService communitysService;
	
	@Autowired
	private CommunitysFollowService communitysFollowService;
	@Autowired
	private WechatUserService  wechatUserService;
	
	@Autowired
	private ArticlesService articlesService;
	@Autowired
	private UserNewsService userNewsService;
	
	@ApiOperation(value="根据id查询社群详情",notes="根据id查询社群详情接口")
	
	@ApiImplicitParams({
		@ApiImplicitParam(name="id", value="社群id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="userId", value="登录用户id",required = false,
		dataType = "String",paramType = "query")
		
	})
	@RequestMapping(value="/selectById",method = RequestMethod.GET)
	public JSONResult selectById(int id,String userId) {
		
		CommunityVo ct = communitysService.selectCommunityVoById(id,userId);
		if(StringUtil.notBlank(userId)) {
			CommunitysFollow t = new CommunitysFollow();
			t.setCommunitysId(id);
			t.setUserid(Integer.parseInt(userId));
			t.setIsDeleted(false);
			ct.setCommunitysFollow(communitysFollowService.selectCount(t));
		}else {
			ct.setCommunitysFollow(0);
		}
		
		QueryParameter qp = new QueryParameter();
		qp.addParamter("communityid", ct.getId());
		qp.addParamter("isDeleted", false);
		qp.setSortField("created_date");
		
		ct.setUserList(wechatUserService.pageList(qp, 1, 10));
		return JSONResult.ok(ct);
	}
	
	@ApiOperation(value="根据社群类别id查询列表,不分页",notes="根据社群类别id查询列表接口,不分页")
	@ApiImplicitParam(name="typeId", value="社群类别id",required = true,
	dataType = "int",paramType = "query")
	@RequestMapping(value="/selectByType",method = RequestMethod.GET)
	public JSONResult selectListByType(int typeId) {
		QueryParameter qp = new QueryParameter();
		qp.addParamter("isDeleted", false);
		qp.addParamter("communityType", typeId);
		return JSONResult.ok(communitysService.selectList(qp));
	}
	
	@ApiOperation(value="根据社群类别id查询列表,分页",notes="根据社群类别id查询列表接口,分页")
	@ApiImplicitParams({
		@ApiImplicitParam(name="typeId", value="社群类别id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
		
	})
	@RequestMapping(value="/selectPageByType",method = RequestMethod.GET)
	public JSONResult selectPageByType(int typeId,int page,int pageSize) {
		QueryParameter qp = new QueryParameter();
		qp.addParamter("communityType", typeId);
		qp.addParamter("isDeleted", false);
		PageInfo<Communitys> info = communitysService.pageInfoList(qp, page, pageSize);
		return JSONResult.ok(info);
	}
	@ApiOperation(value="新建社群",notes="新建社群接口")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public JSONResult insert(@RequestBody Communitys communitys) {
		if(StringUtil.isBlank(communitys.getAdminId())) {
			return JSONResult.error("请先授权登录");
		}
		List<Communitys> list =communitysService.selectApllyCommunity(communitys.getAdminId());
		if(list.size()>0) {
			Communitys c = list.get(0);
			if(c.getFlowSts().equals("0")||c.getFlowSts().equals("3")) {
				return JSONResult.errorMsg("您还有建群申请在审核中，请耐心等待");
			}
			if(c.getFlowSts().equals("1")) {
				return JSONResult.errorMsg("您已经创建了社群，请不要重复创建");
			}
		}
		communitys.setCommunitySts("1");
		communitys.setIsDeleted(false);
		communitys.setCreatedDate(new Date());
		communitys.setFlowSts("0");
		communitys.setCreatedBy(communitys.getAdminId()+"");
		communitysService.insert(communitys);
		
		QueryParameter qp = new QueryParameter();
		qp.addParamter("communityCodeIcon", "", QueryCondition.is_null);
		List<Communitys> lists=communitysService.selectList(qp);
		for(Communitys c:lists) {
			  	String accessToken = CreateQrcore.getToken();
		        String twoCodeUrl = CreateQrcore.getminiqrQr(accessToken,"id="+c.getId(),c.getAdminId()+"");
		        c.setCommunityCodeIcon(twoCodeUrl);
		        communitysService.update(c);
		}
		String openid=wechatUserService.selectByid(communitys.getAdminId()+"").getOpenId();
		WechatUserVo vo= wechatUserService.selectOneByOpenid(openid);
		this.redis.set(USER_REDIS_SESSION+":"+openid, JSONObject.toJSON(vo).toString(),1000*60*30);
		
		return JSONResult.ok();
	}
	
	

}
