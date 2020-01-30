package com.foresee.ctrl;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.Collator;
import java.util.*;

import javax.imageio.ImageIO;

import com.foresee.service.*;
import com.foresee.utils.*;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.foresee.baseCtrl.BasicsAPICtrl;
import com.foresee.finals.BasicsFinal;
import com.foresee.pojo.Articles;
import com.foresee.pojo.AuthorFollow;
import com.foresee.pojo.UserApply;
import com.foresee.pojo.UserPower;
import com.foresee.pojo.WechatUser;
import com.foresee.vo.ArticleVo;
import com.foresee.vo.CommunityUserVo;
import com.foresee.vo.WechatUserVo;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;



@RestController
@Api(value="小程序用户业务类",tags= {"小程序用户业务接口"})
@RequestMapping("/wxuser")
public class WechatUserCtrl extends BasicsAPICtrl{
	
	@Autowired
	private WechatUserService  wechatUserService;
	@Autowired
	private UserApplyService userApplyService;
	@Autowired
	private ArticlesService articlesService;
	@Autowired
	private UserPowerService userPowerService;
	@Autowired
	private AuthorFollowService authorFollowService;
	@Autowired
	private InviteHistoryService inviteHistoryService;
	
	@ApiOperation(value="小程序用户信息修改",notes="小程序用户信息修改接口")
	@RequestMapping(value="/update",method = RequestMethod.POST)
    public JSONResult update(@RequestBody WechatUser user){
		
		user.setUpdatedDate(new Date());
		wechatUserService.update(user);
		WechatUserVo vo= wechatUserService.selectOneByOpenid(user.getOpenId());
		this.redis.set(USER_REDIS_SESSION+":"+user.getOpenId(), JSONObject.toJSON(vo).toString(),1000*60*30);
		
		return JSONResult.ok(JSONObject.parseObject(this.redis.get(USER_REDIS_SESSION+":"+user.getOpenId()), WechatUserVo.class));
       
    }
	
	
	
	@ApiOperation(value="小程序用户信息修改",notes="小程序用户信息修改接口")
	@RequestMapping(value="/tichu",method = RequestMethod.POST)
    public JSONResult tichu(@RequestBody WechatUser user){
		
		wechatUserService.updateCommunity(user);
		WechatUserVo vo= wechatUserService.selectOneByOpenid(user.getOpenId());
		this.redis.set(USER_REDIS_SESSION+":"+user.getOpenId(), JSONObject.toJSON(vo).toString(),1000*60*30);
		
		return JSONResult.ok(JSONObject.parseObject(this.redis.get(USER_REDIS_SESSION+":"+user.getOpenId()), WechatUserVo.class));
       
    }
	
	@ApiOperation(value="小程序用户手机号码修改",notes="小程序用户手机号码修改接口")
	@RequestMapping(value="/updatePhone",method = RequestMethod.POST)
    public JSONResult updatePhone(@RequestBody WechatUser user){
		if(!this.redis.get("u"+user.getId()).equals(user.getCode())) {
			return JSONResult.errorMsg("验证码不正确");
		}
		String oldDate = this.redis.get("u"+user.getId()+"s");
		String newDate = DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
		
      long diff = (DateUtils.getTime(newDate).getTime() - DateUtils.getTime(oldDate).getTime())/1000;//这样得到的差值是毫秒级别  
      if(diff>60) {
    	  this.redis.del("u"+user.getId()+"s");
    	  this.redis.del("u"+user.getId());
    	  return JSONResult.errorMsg("验证超时");
      }
      this.redis.del("u"+user.getId()+"s");
	  this.redis.del("u"+user.getId());
		user.setUpdatedDate(new Date());
		wechatUserService.update(user);
		
		WechatUserVo vo= wechatUserService.selectOneByOpenid(user.getOpenId());
		this.redis.set(USER_REDIS_SESSION+":"+user.getOpenId(), JSONObject.toJSON(vo).toString(),1000*60*30);
		
		return JSONResult.ok(JSONObject.parseObject(this.redis.get(USER_REDIS_SESSION+":"+user.getOpenId()), WechatUserVo.class));
       
    }
	@ApiOperation(value="社长设置管理员和取消管理员",notes="社长设置管理员和取消管理员接口")
	@RequestMapping(value="/setAdmin",method = RequestMethod.POST)
    public JSONResult setAdmin(@RequestBody WechatUser user){
		if(user.getIsCommunity()>0 && user.getIsAdmin()>0) {
			return JSONResult.errorMsg("已经是社长不能再成为管理员");
		}
		UserPower up =new UserPower();
		up.setUserid(user.getId());
		userPowerService.updateUserPower(up);
		/*
		 * user.setUpdatedDate(new Date()); user.setIsAdmin(1);
		 * wechatUserService.update(user);
		 */
		WechatUserVo vo= wechatUserService.selectOneByOpenid(user.getOpenId());
		this.redis.set(USER_REDIS_SESSION+":"+user.getOpenId(), JSONObject.toJSON(vo).toString(),1000*60*30);
		
		return JSONResult.ok(JSONObject.parseObject(this.redis.get(USER_REDIS_SESSION+":"+user.getOpenId()), WechatUserVo.class));
       
    }
	
	@ApiOperation(value="小程序用户信息新增",notes="小程序用户信息新增接口")
	@RequestMapping(value="/insert",method = RequestMethod.POST)
    public JSONResult insert(@RequestBody WechatUser user){
		user.setCreatedDate(new Date());
		wechatUserService.insert(user);
		return JSONResult.ok(wechatUserService.selectOneByOpenid(user.getOpenId()));
       
    }
	@ApiOperation(value="查询用户试图",notes="查询用户试图接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="openid", value="用户openID",required = true,
			dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="iv", value="活动ID",
				dataType = "Integer",paramType = "query")
	})
	@RequestMapping(value="/selectByopenId",method = RequestMethod.GET)
	public JSONResult selectByopenId(String openid, Integer eid) {
		WechatUserVo vo = JSONObject.parseObject(this.redis.get(USER_REDIS_SESSION+":"+openid), WechatUserVo.class);
		vo.setInviteCode(InviteodeUtil.toSerialCode(vo.getId()));
		if(eid!=null && vo.getCommunityid()!=null){
			String accessToken = CreateQrcore.getToken();
			String params = "id="+ vo.getCommunityid() +"&ic=" + InviteodeUtil.toSerialCode(vo.getId()) + "&eid=" + eid;
			String twoCodeUrl = CreateQrcore.getminiqrQr(accessToken, params,vo.getId()+"");
			vo.setCommunityCodeIcon(twoCodeUrl);
		}
		if(eid!=null){
			int inviteCount = inviteHistoryService.getInviteCount(vo.getId(), eid);
			vo.setInviteCount(inviteCount);
		}
		return JSONResult.ok(vo);
	}
	
	@ApiOperation(value="用户上传头像（只能小程序联调）",notes="用户上传头像接口（只能小程序联调）")

	@ApiImplicitParams({
		@ApiImplicitParam(name="userId", value="用户ID",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="imageWidth", value="图片宽度",required = false,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="imageHeight", value="图片高度",required = false,
		dataType = "int",paramType = "query"),
	})
	@RequestMapping(value="/uploadFace",method = RequestMethod.POST)
	public JSONResult uploadFace(int userId,int imageWidth, int imageHeight,@RequestParam("file") MultipartFile[] files) throws IOException {
		if(StringUtil.isBlank(userId)) {
			return JSONResult.errorMsg("需要先登录！");
		}
		
		BufferedImage image = ImageIO.read(files[0].getInputStream());
		if (image == null) {
			return JSONResult.errorMsg("请选择图片上传");
			
		}else {
			
			if(image.getWidth()!=imageWidth || image.getHeight()!=imageHeight) {
				return JSONResult.errorMsg("请选择尺寸是"+imageWidth+":"+imageHeight+"的图片");
			}
		}
		String uploadPath =UploadUtilOss.uploadImage(files[0], userId+"");
		WechatUser user = new WechatUser();
		user.setId(userId);
		user.setHeadUrl(uploadPath);
		user.setUpdatedDate(new Date());
		wechatUserService.update(user);
		WechatUserVo vo= wechatUserService.selectOneByOpenid(wechatUserService.selectByid(userId+"").getOpenId());
		this.redis.set(USER_REDIS_SESSION+":"+vo.getOpenId(), JSONObject.toJSON(vo).toString(),1000*60*30);
		return JSONResult.ok(uploadPath);
	}
	
	
	@ApiOperation(value="用户入群审核",notes="用户入群审核接口")
	@RequestMapping(value="/addCommunityReview",method = RequestMethod.POST)
	public JSONResult addCommunityReview(@RequestBody UserApply userApply) {
		userApply.setUpdatedDate(new Date());
		/**
		 *当审核通过的时候修改用户信息，添加社群id
		 */
		if(userApply.getApplySts().equals("1")) {
			WechatUser user = new WechatUser();
			user.setId(userApply.getUserid());
			user.setCommunityid(userApply.getApplyTargetId());
			wechatUserService.update(user);
			WechatUserVo vo= wechatUserService.selectOneByOpenid(wechatUserService.selectByid(userApply.getUserid()+"").getOpenId());
			this.redis.set(USER_REDIS_SESSION+":"+vo.getOpenId(), JSONObject.toJSON(vo).toString(),1000*60*30);
		}
		userApplyService.update(userApply);
		return JSONResult.ok();
	}
	
	
	@ApiOperation(value="用户成为管理员申请审核",notes="用户成为管理员申请审核接口")
	@RequestMapping(value="/toadminReview",method = RequestMethod.POST)
	public JSONResult toadminReview(@RequestBody UserApply userApply) {
		userApply.setUpdatedDate(new Date());
		//审核通过以后，设置用户为管理员
		if(userApply.getApplySts().equals("1")) {
			UserPower p = new UserPower();
			p.setIsAdmin(1);
			p.setUserid(userApply.getUserid());
			userPowerService.updateUserPower(p);
			WechatUserVo vo= wechatUserService.selectOneByOpenid(wechatUserService.selectByid(userApply.getUserid()+"").getOpenId());
			this.redis.set(USER_REDIS_SESSION+":"+vo.getOpenId(), JSONObject.toJSON(vo).toString(),1000*60*30);
		}
		userApplyService.update(userApply);
		return JSONResult.ok();
	}
	
	@ApiOperation(value="根据openid查询用户详情",notes="根据openid查询用户详情接口")
	@ApiImplicitParam(name="openid", value="用户openID",required = true,
	dataType = "String",paramType = "query")
	@RequestMapping(value="/getUserInfo",method = RequestMethod.POST)
	public JSONResult getUserInfo(String openid) {
		WechatUser user = new WechatUser();
		user.setOpenId(openid);
		return JSONResult.ok(wechatUserService.selectOne(user));
	}
	
	@ApiOperation(value="根据社群id查询用户列表",notes="根据社群id查询用户列表接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="communityid", value="社群id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="页码",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/getUserListCommunityid",method = RequestMethod.GET)
	public JSONResult getUserListCommunityid(int communityid,int page,int pageSize) {
	
		QueryParameter qp = new QueryParameter();
		qp.addParamter("communityid", communityid);
		qp.addParamter("isDeleted", false);
		qp.setSortField("is_community desc,is_admin desc,nick_name");
		qp.setSortOrder("desc");
		return JSONResult.ok(wechatUserService.selectList(qp));
	}
	@ApiOperation(value="根据社群id查询用户列表(社群用户列表接口)",notes="根据社群id查询用户列表接口(社群用户列表接口)")
	@ApiImplicitParams({
		@ApiImplicitParam(name="communityid", value="社群id",required = true,
				dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="page", value="页码",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query")
	})

	@RequestMapping(value="/getCommunityUser",method = RequestMethod.GET)
	public JSONResult getCommunityUser(int communityid,int page,int pageSize) {
		List<WechatUserVo> listp =wechatUserService.selectByCommunityId(communityid);
		List<CommunityUserVo> listVo=new ArrayList<CommunityUserVo>();

		//管理员
		CommunityUserVo wu = new CommunityUserVo();
		List<WechatUserVo>  ulist =wechatUserService.selectAdminList(communityid);
		wu.setUserList(ulist);
		wu.setId(1);
		wu.setTitle("管理员");
		wu.setSumNum(ulist.size());
		listVo.add(wu);

		//按照拼音排序
		Pinyin4jUtil pinyin4jUtil = new Pinyin4jUtil();
		for(WechatUserVo v:listp) {
			if(StringUtils.isNotEmpty(v.getNickName()) && String.valueOf(v.getNickName().charAt(0)).matches("[\u4e00-\u9fa5]")) {
				// 如果开头为汉字，则加入汉字列表中
				v.setPinyin(pinyin4jUtil.getStringPinYin(v.getNickName()));
			}else if(StringUtils.isNotEmpty(v.getNickName())&& String.valueOf(v.getNickName().charAt(0)).matches("^[a-zA-Z]*")){
				v.setPinyin(v.getNickName());
			}else{
				v.setPinyin("#");
			}
		}
		Comparator<Object> com = Collator.getInstance(Locale.US);
		listp.sort((o1, o2) -> ((Collator) com).compare(o1.getPinyin(), o2.getPinyin()));
		System.out.println(listp.toString());
		int i = 1;
		String firstChar  = null;
		CommunityUserVo vo = null;
		for(WechatUserVo v:listp) {
			if(firstChar == null || !firstChar.equals(v.getPinyin().toUpperCase().substring(0, 1))){
				i++;
				vo = new CommunityUserVo();
				vo.setId(i);
				vo.setTitle(v.getPinyin().toUpperCase().substring(0, 1));
				listVo.add(vo);
				vo.setUserList( new ArrayList<WechatUserVo>());
				firstChar = v.getPinyin().toUpperCase().substring(0, 1);
			}
			vo.getUserList().add(v);
		}
		//统计数目
		for(CommunityUserVo v:listVo) {
			v.setSumNum(v.getUserList().size());
		}
		//#置后
        if(listVo.size()>2) {
            CommunityUserVo next = listVo.get(1);
            if("#".equals(next.getTitle())) {
				listVo.remove(1);
				listVo.add(next);
			}
        }
        return JSONResult.ok(listVo);
	}
	
	@ApiOperation(value="用户详情页面接口",notes="用户详情页面接口")
	@ApiImplicitParams({
		@ApiImplicitParam(name="openid", value="openid",required = true,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="page", value="页码",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="pageSize", value="每页显示条数",required = true,
		dataType = "int",paramType = "query"),
		@ApiImplicitParam(name="userid", value="userid",required = true,
		dataType = "int",paramType = "query")
	})
	@RequestMapping(value="/userPageInfo",method = RequestMethod.GET)
	public JSONResult userPageInfo(String openid,int page,int pageSize,int userid) {
		
		WechatUserVo vo =JSONObject.parseObject(this.redis.get(USER_REDIS_SESSION+":"+openid), WechatUserVo.class);
		
		PageInfo<ArticleVo> info = articlesService.selectListByUserId(vo.getId(), page, pageSize);
		if(userid>0) {
			AuthorFollow af =new AuthorFollow();
			af.setUserid(userid);
			af.setAuthorId(vo.getId());
			af.setIsDeleted(false);
			int isFollow =authorFollowService.selectCount(af);
			vo.setUid(isFollow);
		}else {
			vo.setUid(0);
		}
		
		vo.setList(info);
		
		return JSONResult.ok(vo);
	}

	private void setRedisSession(String openid,String code) {
		
		this.redis.set(openid, code, 1000*60*30);

	}
}
