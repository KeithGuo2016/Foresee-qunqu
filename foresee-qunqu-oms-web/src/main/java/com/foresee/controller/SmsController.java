package com.foresee.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.model.User;
import com.foresee.service.AccountsService;
import com.foresee.utils.AliyunSmsUtil;
import com.foresee.utils.RandomUtil;
import com.foresee.utils.ResultCode;
import com.foresee.utils.ResultUtils;

/**
 * @author wangruiheng
 * 短信管理
 */
@RestController
@RequestMapping("/sms")
@Validated
public class SmsController {
	
	@Resource  private AccountsService accountsService;
	
	/**
	 * 发送验证码
	 * @param request
	 * @param userPhone
	 * @return
	 */
	@RequestMapping("/code")
	public Object code(HttpServletRequest request, String userPhone) {
		try {
			User query = new User();
			query.setUserphone(userPhone);
			List<User> list = accountsService.selectListAccount(query);
			if(list.size()!=1){
				return ResultUtils.warn(ResultCode.ACCOUNT_ERROR);
			}
			String code = RandomUtil.nextInt6();
			AliyunSmsUtil.sendSmsPassword(userPhone, code);
			request.getSession().setAttribute("verificationCode", code);
			System.out.println(code);
			return ResultUtils.success("");
		} catch (Exception e) {
			// TODO: handle exception
			return ResultUtils.warn(ResultCode.VERIFICATIONCODE_ERROR);
		}
	}

}
