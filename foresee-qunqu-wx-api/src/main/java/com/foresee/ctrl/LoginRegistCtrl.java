package com.foresee.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

import com.foresee.service.InviteHistoryService;
import com.foresee.utils.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foresee.baseCtrl.BasicsAPICtrl;
import com.foresee.finals.BasicsFinal;
import com.foresee.pojo.UserPower;
import com.foresee.pojo.WechatUser;
import com.foresee.service.UserPowerService;
import com.foresee.service.WechatUserService;
import com.foresee.vo.WechatUserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="用户登录注册",tags= {"用户登录、注册、注销接口"})
public class LoginRegistCtrl extends BasicsAPICtrl{
	@Autowired
	private WechatUserService  wechatUserService;
	@Autowired
	private UserPowerService userPowerService;
	@Autowired
	private InviteHistoryService inviteHistoryService;
	/**
	 * 用户授权登录：获得用户openID，unionId，其他微信开放信息
	 * 存入数据库
	 * 从数据控中查询出用户信息 存放在Redis中
	 * @param code
	 * @param encryptedData
	 * @param iv
	 * @return
	 */
	@ApiOperation(value="用户授权登录（只能小程序联调）",notes="用户授权登录接口（只能小程序联调）")
	@ApiImplicitParams({
		@ApiImplicitParam(name="code", value="用户授权code",required = true,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="encryptedData", value="用户授权登录以后用户信息密文",required = true,
				dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="iv", value="偏移量，也是在授权登录以后的用户信息中",required = true,
		dataType = "String",paramType = "query"),
		@ApiImplicitParam(name="iv", value="活动ID",
			dataType = "Integer",paramType = "query")
	})
	@RequestMapping(value="/doLogin",method = RequestMethod.POST)
    public JSONResult doLogin(String code,String encryptedData, String iv, Integer eid){
		// 登录凭证不能为空
		System.out.println("code:"+code+" 密文encryptedData:"+encryptedData+" 偏移量iv"+iv);
		if (StringUtil.isBlank(code)) {
			return JSONResult.errorMsg("登录凭证不能为空");
		}
		
        String url = BasicsFinal.WX_URL;
        String param = "appid=" + BasicsFinal.APPID + "&secret=" + BasicsFinal.SECRET + "&js_code=" + code + "&grant_type=authorization_code";
        // 发送请求
     	String sr = HttpRequest.sendGet(url, param);
     	// 解析相应内容（转换成json对象）
     	JSONObject json = JSON.parseObject(sr);
     	System.out.println(json);
     	// 获取会话密钥（session_key）
     	String session_key = json.get("session_key").toString();
     	// 用户的唯一标识（openid）
     	String openid = (String) json.get("openid");
     	//先获取缓存中的数据，如果有就返回，如果没有就新增
     	String userSession = this.redis.get(USER_REDIS_SESSION+":"+openid);
     	WechatUserVo vo = null;
     	if(StringUtil.notBlank(userSession)) {
     		vo = JSONObject.parseObject(userSession, WechatUserVo.class);
     	}
		////////////////2、对encryptedData加密数据进行AES解密 ////////////////
		if(vo ==null){
			InputStream in =null;
			try {

				String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
				if (null != result && result.length() > 0) {
					WechatUser user = new WechatUser();
					user.setOpenId(openid);
					JSONObject userInfoJSON = JSON.parseObject(result);
					user.setNickName(userInfoJSON.get("nickName").toString());
					user.setHeadUrl(userInfoJSON.get("avatarUrl").toString());
					user.setCreatedDate(new Date());
					user.setCreatedBy("");
					user.setAppId("");
					user.setUid(0);
					if(StringUtil.notBlank(userInfoJSON.get("city"))) {
						user.setCity(userInfoJSON.get("city").toString());
					}else {
						user.setCity("");
					}
					if(StringUtil.notBlank(userInfoJSON.get("unionId"))) {
						user.setUnionId(userInfoJSON.get("unionId").toString());
					}else {
						user.setUnionId("");
					}
					//读取背景
				/*ImgUtil.toBDFile(user.getHeadUrl(), "/img/"+user.getOpenId()+".png");
				ImgUtil.createThumb2("/img/"+user.getOpenId()+".png", "/img/"+user.getOpenId()+"_2.png", 375, 375);
				ImgUtil.cutImage( "/img/"+user.getOpenId()+"_2.png", 0,  80, 375,  199,"/img/"+user.getOpenId()+"_3.png");
		         File f=new File("/img/"+user.getOpenId()+".png");
		         f.delete();
		         File f2=new File("/img/"+user.getOpenId()+"_2.png");
		         f2.delete();
		         in = new FileInputStream("/img/"+user.getOpenId()+"_3.png");
					byte[] data = new byte[in.available()];
			        in.read(data);
			        in.close();
			        user.setBgIcon(AliyunOSSUtil.uploadImg(new String(Base64.encodeBase64(data)),"/"+user.getOpenId()+"/"));
			        File f3=new File("/img/"+user.getOpenId()+"_3.png");
			        f3.delete();*/

					//存入数据库
					wechatUserService.insert(user);
					UserPower p = new UserPower();
					int userid = wechatUserService.selectOneByOpenid(user.getOpenId()).getId();
					p.setCreatedBy(userid+"");
					p.setUserid(userid);
					p.setCreatedDate(new Date());
					p.setIsAdmin(0);
					p.setIsCommunity(0);
					p.setIsDeleted(false);
					p.setIsVip(0);
					userPowerService.insert(p);

				} else {
					return JSONResult.errorMsg("解密失败");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				try {
					if(in!=null) {
						in.close();
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			vo= wechatUserService.selectOneByOpenid(openid);
		}
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
		//存入Redis
		vo.setUserRedisSession(setRedisSession(openid,vo));
		
		return JSONResult.ok(vo);
       
    }

	
	@ApiOperation(value="用户注册",notes="用户手机注册接口：用户id，和手机号码作为必填参数")
	@RequestMapping(value="/regist",method = RequestMethod.POST)
    public JSONResult regist(@RequestBody WechatUser user){
		wechatUserService.update(user);
		return JSONResult.ok();
       
    }
	@ApiOperation(value="获取用户最新信息",notes="获取用户最新信息")
	@RequestMapping(value="/getNewInfo",method = RequestMethod.POST)
    public JSONResult getNewInfo( String openid){
		String userSession = this.redis.get(USER_REDIS_SESSION+":"+openid);
     	if(StringUtil.notBlank(userSession)) {
     		return JSONResult.ok(JSONObject.parseObject(userSession, WechatUserVo.class)) ;
     	}
     	return JSONResult.ok();
    }
	
	
	
	/**
	 * 设置用户redis缓存
	 * @param openid
	 * @return
	 */
	private String setRedisSession(String openid,WechatUserVo user) {
		
		this.redis.set(USER_REDIS_SESSION+":"+openid, JSONObject.toJSON(user).toString(),1000*60*30);	
		return USER_REDIS_SESSION+":"+openid;
	}
}
