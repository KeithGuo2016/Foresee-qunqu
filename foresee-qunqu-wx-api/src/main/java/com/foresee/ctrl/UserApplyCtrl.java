package com.foresee.ctrl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.foresee.pojo.*;
import com.foresee.service.*;
import com.foresee.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.baseCtrl.BasicsAPICtrl;
import com.foresee.vo.UserApplyVo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="用户申请业务API",tags= {"用户申请业务API"})
@RequestMapping("/apply")
public class UserApplyCtrl extends BasicsAPICtrl{
	
	@Autowired
	private UserApplyService userApplyService;
	@Autowired
	private InviteHistoryService inviteHistoryService;
	@Autowired
	private WechatUserService wechatUserService;

	@Autowired
	private CommunitysService communitysService;
	
	@ApiOperation(value="新增一个用户申请",notes="新增用户申请接口")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
	public JSONResult insert(@RequestBody UserApply userApply) {
		if(StringUtil.isBlank(userApply.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		
		userApply.setCreatedDate(new Date());
		userApply.setCreatedBy(userApply.getUserid()+"");
		userApplyService.insert(userApply);
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="新增VIP申请",notes="新增VIP申请接口")
	@RequestMapping(value="/insertVip",method = RequestMethod.POST)
	public JSONResult insertVip(@RequestBody UserApply userApply) {
		if(StringUtil.isBlank(userApply.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		if(!this.redis.get("vip"+userApply.getUserid()).equals(userApply.getCode())) {
			return JSONResult.errorMsg("验证码不正确");
		}
		//更新用户手机号
		WechatUser wechatUser = wechatUserService.selectByid(userApply.getUserid()+"");
		wechatUser.setPhone(userApply.getPhone());
		wechatUser.setUpdatedDate(new Date());
		wechatUserService.update(wechatUser);

		String oldDate = this.redis.get("vip"+userApply.getUserid()+"s");
		String newDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		
        long diff = (DateUtils.getTime(newDate).getTime() - DateUtils.getTime(oldDate).getTime())/1000;//这样得到的差值是毫秒级别
        if(diff>60) {
    	   this.redis.del("vip"+userApply.getUserid()+"s");
    	   this.redis.del("vip"+userApply.getUserid());
    	   return JSONResult.errorMsg("验证超时");
        }
        this.redis.del("vip"+userApply.getUserid()+"s");
	    this.redis.del("vip"+userApply.getUserid());
		UserApply u = new UserApply();
		u.setUserid(userApply.getUserid());
		u.setApplyType("1");
		u.setApplySts("0");
		u.setIsDeleted(false);
		if(userApplyService.selectOne(u)==null) {
			userApply.setApplyType("1");
			userApply.setCreatedDate(new Date());
			userApply.setApplySts("0");
			userApply.setCreatedBy(userApply.getUserid()+"");
			
			userApplyService.insert(userApply);
		}
		else {
			return JSONResult.errorMsg("您提交的VIP申请正在审核中，请等待工作人员联系");
		}
		
		
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="查看是否存在加入社群申请",notes="查看是否存在加入社群申请")
	@ApiImplicitParam(name="userid", value="userid",required = true,
	dataType = "int",paramType = "query")
	@RequestMapping(value="/isAddCommunity",method = RequestMethod.GET)
	public JSONResult isAddCommunity(int userid ) {
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		List<UserApplyVo> list =userApplyService.selectAddCommunityApply(userid);
		Communitys c= new Communitys();
		c.setAdminId(userid);
		c.setFlowSts("0");
		int num = communitysService.selectCount(c);
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("addApply", list.size());
		map.put("create", num);
		return JSONResult.ok(map);
	}
	
	@ApiOperation(value="新增入群申请",notes="新增入群申请接口")
	@RequestMapping(value="/insertAddCommunity",method = RequestMethod.POST)
	public JSONResult insertAddCommunity(@RequestBody UserApply userApply) {
		if(StringUtil.isBlank(userApply.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		//TODO 与vip共用一个获取验证码的接口
		if(!this.redis.get("vip"+userApply.getUserid()).equals(userApply.getCode())) {
			return JSONResult.errorMsg("验证码不正确");
		}
		String oldDate = this.redis.get("vip"+userApply.getUserid()+"s");
		String newDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		long diff = (DateUtils.getTime(newDate).getTime() - DateUtils.getTime(oldDate).getTime())/1000;//这样得到的差值是毫秒级别
		if(diff>60) {
			this.redis.del("vip"+userApply.getUserid());
			this.redis.del("vip"+userApply.getUserid()+"s");
			return JSONResult.errorMsg("验证超时");
		}
		this.redis.del("vip"+userApply.getUserid());
		this.redis.del("vip"+userApply.getUserid()+"s");

		//更新用户手机号，如果这个手机号码被注册过，就不做修改
		WechatUser user = new WechatUser();
		user.setPhone(userApply.getPhone());
		int count = wechatUserService.selectCount(user);
		WechatUser wechatUser = wechatUserService.selectByid(userApply.getUserid()+"");
		if(count==0) {
			wechatUser.setPhone(userApply.getPhone());
			wechatUser.setUpdatedDate(new Date());
			wechatUserService.update(wechatUser);
		}
		userApply.setApplyType("2");
		userApply.setApplyDesc("用户入群申请: "+ userApply.getApplyDesc());
		userApply.setApplySts("0");
		userApply.setCreatedDate(new Date());
		userApply.setCreatedBy(userApply.getUserid()+"");

		//没有申请直接插入，注意只有一条数据
		UserApply u = new UserApply();
		u.setUserid(userApply.getUserid());
		u.setApplyType("2");
		u.setIsDeleted(false);
		UserApply ua =userApplyService.selectOne(u);
		if(ua==null) {
			userApplyService.insert(userApply);
			ua =userApplyService.selectOne(u);
			if(userApply.getInviteCode()!=null && userApply.getEid()!=null){
				InviteHistory ih = new InviteHistory();
				Long iu = InviteodeUtil.codeToId(userApply.getInviteCode());
				ih.setCreatedBy(iu+"");
				ih.setTargetType(1);
				ih.setCreatedDate(new Date());
				ih.setEventId(new Integer(userApply.getEid()));
				ih.setRelatedId(ua.getId());
				inviteHistoryService.insert(ih);
			}
		}
		else {
			if(ua.getApplySts().equals("0")||ua.getApplySts().equals("3")) {
				return JSONResult.errorMsg("您的入群申请正在审核中，请耐心等待");
			}
			if(ua.getApplySts().equals("1") && wechatUser.getCommunityid() != null) {
				return JSONResult.errorMsg("您已经加入社群了");
			}
			//更新状态和数据
			ua.setCreatedDate(new Date());
			ua.setApplySts("0");
			ua.setPhone(userApply.getPhone());
			ua.setApplyDesc(userApply.getApplyDesc());
			ua.setQq(userApply.getQq());
			ua.setApplyTargetId(userApply.getApplyTargetId());
			ua.setCreatedBy(userApply.getUserid()+"");
			userApplyService.update(ua);

		}
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="新增管理员申请",notes="新增管理员申请接口")
	@RequestMapping(value="/insertAdmin",method = RequestMethod.POST)
	public JSONResult insertAdmin(@RequestBody UserApply userApply) {
		if(StringUtil.isBlank(userApply.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		UserApply u = new UserApply();
		u.setUserid(userApply.getUserid());
		u.setApplyType("3");
		u.setIsDeleted(false);
		u.setApplyTargetId(userApply.getApplyTargetId());
		UserApply ua =userApplyService.selectOne(u);
		if(ua==null) {
			userApply.setApplyType("3");
			userApply.setCreatedDate(new Date());
			userApply.setCreatedBy(userApply.getUserid()+"");
			userApplyService.insert(userApply);
		}
		else {
			if(ua.getApplySts().equals("0")||ua.getApplySts().equals("3")) {
				return JSONResult.errorMsg("您的群管理员申请正在审核中，请耐心等待");
			}
			if(ua.getApplySts().equals("1")) {
				return JSONResult.errorMsg("您已经是社群管理员了");
			}
			if(ua.getApplySts().equals("2")) {
				ua.setCreatedDate(new Date());
				ua.setApplySts("0");
				ua.setCreatedBy(userApply.getUserid()+"");
				userApplyService.update(ua);;
			}
			
		}
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="创建社群申请",notes="创建社群申请接口")
	@RequestMapping(value="/createCommunity",method = RequestMethod.POST)
	public JSONResult createCommunity(@RequestBody UserApply userApply) {
		if(StringUtil.isBlank(userApply.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		UserApply u = new UserApply();
		u.setUserid(userApply.getUserid());
		u.setApplyType("4");
		u.setApplySts("0");
		u.setIsDeleted(false);
		UserApply ua =userApplyService.selectOne(u);
		if(ua == null) {
			userApply.setApplyType("4");
			userApply.setCreatedDate(new Date());
			userApply.setCreatedBy(userApply.getUserid()+"");
			userApplyService.insert(userApply);
		}else {
			return JSONResult.errorMsg("您的创建社群申请正在审核中，请等待工作人员联系");
		}
		
		
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="征稿申请（我要出版）",notes="征稿申请（我要出版）接口")
	@RequestMapping(value="/contributeApply",method = RequestMethod.POST)
	public JSONResult contributeApply(@RequestBody UserApply userApply) {
		if(StringUtil.isBlank(userApply.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		if(!this.redis.get("u"+userApply.getUserid()).equals(userApply.getCode())) {
			return JSONResult.errorMsg("验证码不正确");
		}
		String oldDate = this.redis.get("u"+userApply.getUserid()+"s");
		String newDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		
      long diff = (DateUtils.getTime(newDate).getTime() - DateUtils.getTime(oldDate).getTime())/1000;//这样得到的差值是毫秒级别  
      if(diff>60) {
    	  this.redis.del("u"+userApply.getUserid()+"s");
    	  this.redis.del("u"+userApply.getUserid());
    	  return JSONResult.errorMsg("验证超时");
      }
      this.redis.del("u"+userApply.getUserid()+"s");
	  this.redis.del("u"+userApply.getUserid());
		UserApply u = new UserApply();
		u.setUserid(userApply.getUserid());
		u.setApplyType("5");
		u.setApplySts("0");
		u.setIsDeleted(false);
		int ua =userApplyService.selectCount(u);
		if(ua <5) {
			userApply.setApplyType("5");
			userApply.setCreatedDate(new Date());
			userApply.setCreatedBy(userApply.getUserid()+"");
			userApplyService.insert(userApply);
		}else {
			return JSONResult.errorMsg("您还有5个出版申请在审核当中，请耐心等待");
		}
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="社刊申请",notes="社刊申请接口")
	@RequestMapping(value="/magazineApply",method = RequestMethod.POST)
	public JSONResult magazineApply(@RequestBody UserApply userApply) {
		if(StringUtil.isBlank(userApply.getUserid())) {
			return JSONResult.error("请先授权登录");
		}
		if(!this.redis.get("u"+userApply.getUserid()).equals(userApply.getCode())) {
			return JSONResult.errorMsg("验证码不正确");
		}
		String oldDate = this.redis.get("u"+userApply.getUserid()+"s");
		String newDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		
      long diff = (DateUtils.getTime(newDate).getTime() - DateUtils.getTime(oldDate).getTime())/1000;//这样得到的差值是毫秒级别  
      if(diff>60) {
    	  this.redis.del("u"+userApply.getUserid()+"s");
    	  this.redis.del("u"+userApply.getUserid());
    	  return JSONResult.errorMsg("验证超时");
      }
      this.redis.del("u"+userApply.getUserid()+"s");
	  this.redis.del("u"+userApply.getUserid());
		UserApply u = new UserApply();
		u.setUserid(userApply.getUserid());
		u.setApplyType("6");
		u.setApplySts("0");
		u.setIsDeleted(false);
		int ua =userApplyService.selectCount(u);
		if(ua <5) {
			userApply.setApplyType("6");
			userApply.setCreatedDate(new Date());
			userApply.setCreatedBy(userApply.getUserid()+"");
			userApplyService.insert(userApply);
		}else {
			return JSONResult.errorMsg("您还有5个社刊申请在审核当中，请耐心等待");
		}
		return JSONResult.ok();
	}
	
	@ApiOperation(value="修改一个用户申请",notes="修改用户申请接口")
	@RequestMapping(value="/update",method = RequestMethod.POST)
	public JSONResult update(@RequestBody UserApply userApply) {
		userApply.setUpdatedDate(new Date());
		userApplyService.update(userApply);
		return JSONResult.ok();
	}
	
	@ApiOperation(value="根据id查询一条",notes="根据id查询一条")
	@ApiImplicitParam(name="id", value="id",required = true,
	dataType = "String",paramType = "query")
	@RequestMapping(value="/selectByid",method = RequestMethod.POST)
	public JSONResult selectByid(String id) {
		return JSONResult.ok(userApplyService.selectByid(id));
	}
	
	@ApiOperation(value="查询入群申请列表",notes="查询入群申请列表接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="communityId", value="社群id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="applyType", value="申请类型：只能是2",required = false,
		dataType = "String",paramType = "query")
	})
	@RequestMapping(value="/selectAddCommunityApply",method = RequestMethod.GET)
	public JSONResult selectAddCommunityApply(int communityId,String applyType) {
		if(StringUtil.isBlank(applyType)) {
			applyType="2";
		}
		return JSONResult.ok(userApplyService.selectApplyTargetList(communityId, applyType));
	}
	
	@ApiOperation(value="查询成为社群管理员的申请列表",notes="查询成为社群管理员的申请列表接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="communityId", value="社群id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="applyType", value="申请类型：只能是3",required = false,
		dataType = "String",paramType = "query")
	})
	@RequestMapping(value="/selectToAdminApply",method = RequestMethod.GET)
	public JSONResult selectToAdminApply(int communityId,String applyType) {
		if(StringUtil.isBlank(applyType)) {
			applyType="3";
		}
		return JSONResult.ok(userApplyService.selectApplyTargetList(communityId, applyType));
	}
	
	 
	
	
	@ApiOperation(value="查询自己的申请列表",notes="查询自己的申请列表接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userid", value="用户id",required = false,
				dataType = "String",paramType = "query"),
		
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/selectMeApply",method = RequestMethod.GET)
	public JSONResult selectMeApply(String userid,int page,int pageSize) {
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		PageInfo<UserApplyVo> info = userApplyService.selectMeApply(Integer.parseInt(userid), page, pageSize);
		return JSONResult.ok(info);
	}
	
	@ApiOperation(value="查询自己全部的申请列表",notes="查询自己全部的申请列表接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userid", value="用户id",required = false,
				dataType = "String",paramType = "query"),
		
		@ApiImplicitParam(name="page", value="当前第几页",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/selectMeApplyAll",method = RequestMethod.GET)
	public JSONResult selectMeApplyAll(String userid,int page,int pageSize) {
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		PageInfo<UserApplyVo> info = userApplyService.selectMeApplyAll(Integer.parseInt(userid), page, pageSize);
		return JSONResult.ok(info);
	}
	
	
	
	@ApiOperation(value="发送验证码到指定手机上，不做手机号码唯一判断",notes="发送验证码接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userid", value="申请用户id",required = true,
		dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="phone", value="电话号码",required = true,
		dataType = "String",paramType = "query")
	})
	@RequestMapping(value="/getCode",method = RequestMethod.POST)
	public JSONResult getCode(String userid,String phone) {
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		String code =String.valueOf((int)((Math.random()*9+1)*100000));
		setRedisSession("vip"+userid,code);
		setRedisSession("vip"+userid+"s",DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		AliyunSmsUtil.sendSmsCode(phone, code);
		
		return JSONResult.ok();
	}
	
	@ApiOperation(value="发送验证码到指定手机上，不做手机号码唯一判断",notes="发送验证码接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="userid", value="申请用户id",required = true,
		dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="phone", value="电话号码",required = true,
		dataType = "String",paramType = "query")
	})
	@RequestMapping(value="/getCodeu",method = RequestMethod.POST)
	public JSONResult getCodeu(String userid,String phone) {
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		String code =String.valueOf((int)((Math.random()*9+1)*100000));
		setRedisSession("u"+userid,code);
		setRedisSession("u"+userid+"s",DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
		
		AliyunSmsUtil.sendSmsCode(phone, code);
		
		return JSONResult.ok();
	}

	@ApiOperation(value="发送验证码到指定手机上，手机号码唯一性判断",notes="发送验证码接口")
	@ApiImplicitParams({
			@ApiImplicitParam(name="userid", value="申请用户id",required = true,
					dataType = "String",paramType = "query"),
			@ApiImplicitParam(name="phone", value="电话号码",required = true,
					dataType = "String",paramType = "query")
	})
	@RequestMapping(value="/getCodeUnique",method = RequestMethod.POST)
	public JSONResult getCodeUnique(String userid,String phone) {
		if(StringUtil.isBlank(userid)) {
			return JSONResult.error("请先授权登录");
		}
		//先校验手机号码的唯一性
		WechatUser user = new WechatUser();
		user.setPhone(phone);
		int count = wechatUserService.selectCount(user);
		if(count>0) {
			return JSONResult.errorMap("手机号不能重复");
		}
		String code =String.valueOf((int)((Math.random()*9+1)*100000));
		setRedisSession("u"+userid, code);
		setRedisSession("u"+userid+"s",DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));

		AliyunSmsUtil.sendSmsCode(phone, code);

		return JSONResult.ok();
	}

	private void setRedisSession(String userid,String code) {
		
		this.redis.set(userid, code, 1000*60*30);

	}
	

}
