package com.foresee.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.advice.WebLog;
import com.foresee.model.User;
import com.foresee.service.AccountsService;
import com.foresee.util.MD5Util;
import com.foresee.utils.ResultCode;
import com.foresee.utils.ResultUtils;
import com.foresee.utils.StringUtil;

/**
 * 登录
 * @author wrh
 *
 */
@RestController

@RequestMapping("/login")
@Validated
public class LoginController {
	@Resource  private AccountsService accountsService;
	
	
	/**
	 * 后台用户登录
	 * @param request
	 * @param account
	 * @return
	 */
	@RequestMapping("/system")
	@WebLog(description ="登录")
	public Object system(HttpServletRequest request, User account) {
		User query = new User();
		query.setUserphone(account.getUserphone());
		List<User> list = accountsService.selectListAccount(query);
		if(list.size()!=1){
			return ResultUtils.warn(ResultCode.PASSWORD_ERROR);
		}
		query = new User();
		query = list.get(0);
		String userpwd = MD5Util.encrypt32(account.getUserpwd());
		if(userpwd.equals(query.getUserpwd())){
			request.getSession().setAttribute("accounts", query);
			return ResultUtils.success("");
		}else{
			return ResultUtils.warn(ResultCode.PASSWORD_ERROR);
		}
	}
	
	
	/**
	 * 后台用户验证码登录
	 * @param request
	 * @param account
	 * @return
	 */
	@RequestMapping("/code")
	@WebLog(description ="验证码登录")
	public Object code(HttpServletRequest request, User account) {
		try {
			String verificationCode = (String) request.getSession().getAttribute("verificationCode");
			if(!StringUtil.isNotNull(verificationCode)){
				return ResultUtils.warn(ResultCode.VERIFICATIONCODE_ERROR);
			}
			
			String verificationCode2 = account.getVerificationCode();
			if(!verificationCode.equals(verificationCode2)){
				return ResultUtils.warn(ResultCode.VERIFICATIONCODE_ERROR);
			}
			User query = new User();
			query.setUserphone(account.getUserphone());
			List<User> list = accountsService.selectListAccount(query);
			if(list.size()!=1){
				return ResultUtils.warn(ResultCode.ACCOUNT_ERROR);
			}
			request.getSession().setAttribute("accounts", list.get(0));
			return ResultUtils.success("");
		} catch (Exception e) {
			// TODO: handle exception
			return ResultUtils.warn(ResultCode.LOGIN_ERROR);
		}
	}
	
	
	
	/**
	 * 后台用户登录详情
	 * @param request
	 * @return
	 */
	@RequestMapping("/datil")
	public Object datil(HttpServletRequest request) {
		User account =  (User) request.getSession().getAttribute("accounts");
		if(account!=null){
			return ResultUtils.success(account);
		}
		return ResultUtils.error("");
	}
	
	/**
	 *注销session
	 */
	@RequestMapping("/loginout")
	public void loginout(HttpServletRequest request) {
		request.getSession().invalidate();
	}
}
