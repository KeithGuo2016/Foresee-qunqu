package com.foresee.utils;

import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

public class AliyunSmsUtil {

	/**
	 * 模板：SMS_172352478 
	 * 内容：您好，您的密码已经初始化为${password}，请及时登录并修改密码。
	 * @param phone
	 * @param code
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static boolean sendSmsPassword(String phone, String code) {
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI92gHh4s0aRuW",
				"ncAaIocNBoaOg5bXySeVGA280bmgHj");
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setAction("SendSms");
		request.setVersion("2017-05-25");
		request.putQueryParameter("RegionId", "default");
		request.putQueryParameter("PhoneNumbers", phone);
		request.putQueryParameter("TemplateCode", "SMS_172352478");
		request.putQueryParameter("SignName", "预见遇见文化");
		request.putQueryParameter("TemplateParam", "{\"password\":\"" + code + "\"}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			Map map = JSON.toJavaObject(JSON.parseObject(response.getData()), Map.class);
			String Code = map.get("Code").toString();
			if ("OK".equals(Code)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}
	
	
	@SuppressWarnings("rawtypes")
	public static boolean sendSmsCode(String phone, String code) {
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI92gHh4s0aRuW",
				"ncAaIocNBoaOg5bXySeVGA280bmgHj");
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setAction("SendSms");
		request.setVersion("2017-05-25");
		request.putQueryParameter("RegionId", "default");
		request.putQueryParameter("PhoneNumbers", phone);
		request.putQueryParameter("TemplateCode", "SMS_172736980");
		request.putQueryParameter("SignName", "预见遇见文化");
		request.putQueryParameter("TemplateParam", "{\"password\":\"" + code + "\"}");
		try {
			CommonResponse response = client.getCommonResponse(request);
			Map map = JSON.toJavaObject(JSON.parseObject(response.getData()), Map.class);
			String Code = map.get("Code").toString();
			if ("OK".equals(Code)) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			try {
				throw e;
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return false;
	}

}
