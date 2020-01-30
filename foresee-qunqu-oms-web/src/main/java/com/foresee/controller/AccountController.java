package com.foresee.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.foresee.utils.Result;
import com.foresee.utils.ResultUtils;
import com.foresee.utils.StringUtil;
import com.foresee.model.CommonCity;
import com.foresee.model.CommonProvince;
import com.foresee.model.User;
import com.foresee.service.AccountsService;
import com.foresee.service.CityService;
import com.foresee.util.ListCopyUtil;
import com.foresee.util.MD5Util;

/**
 * @author wangruiheng 用户管理
 */
@RestController

@RequestMapping("/account")
@Validated
public class AccountController {
	@Resource
	private AccountsService accountsService;

	@Resource
	private CityService cityService;

	/**
	 * 查询user列表
	 * 
	 * @param request
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectaccounts")
	public Object selectAccountsList(HttpServletRequest request, User account) {
		PageHelper.startPage(account.getPage(), account.getLimit());
		User accounts = (User) request.getSession().getAttribute("accounts");
		if (accounts.getChannelid() > 0) {
			account.setChannelid(accounts.getChannelid());
		}
		List<User> list = accountsService.selectListAccount(account);
		List<com.foresee.vo.Accounts> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.Accounts());
		PageInfo<User> pageInfo = new PageInfo<>(list);
		int i = 1;
		for (com.foresee.vo.Accounts acc : listvo) {
			Integer provinceid = acc.getProvinceid();
			CommonProvince province = cityService.selectProvinceById(provinceid);
			if (province != null) {
				acc.setProvincename(province.getProvinceName());
			}
			Integer cityid = acc.getCityid();
			CommonCity city = cityService.selectByCityId(cityid);
			if (city != null) {
				acc.setCityName(city.getCityName());
			}
			acc.setIndex(i++);
		}
		return ResultUtils.successlist(listvo, pageInfo.getTotal());
	}

	/**
	 * @return 添加用户
	 */
	@RequestMapping("/save")
	public Object saveAccount(HttpServletRequest request, User account) {
		User accounts =  (User) request.getSession().getAttribute("accounts");
		account.setCreateid(accounts.getId());
		account.setCreatetime(new Date());
		String userpwd = account.getUserpwd();
		String encrypt32 = MD5Util.encrypt32(userpwd);
		account.setUserpwd(encrypt32);
		int num = accountsService.insertSelective(account);
		if (num > 0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}

	/**
	 * @return 删除用户
	 */
	@RequestMapping("/del")
	public Object delAccount(HttpServletRequest request,
			@NotNull(message = "{accounts.id.notnull}") @Min(value = 1, message = "{accounts.id.min}") Integer id) {
		Result<Object> result = ResultUtils.fail("");
		User account = accountsService.selectById(id);
		if (account != null) {
			account.setIsenable(-1);
			int num = accountsService.updateByPrimaryKeySelective(account);
			if (num > 0) {
				result = ResultUtils.success("");
			}
		}
		return result;
	}

	/**
	 * @return 编辑用户
	 */
	@RequestMapping("/update")
	public Object updateAccount(HttpServletRequest request, User account) {
		String userpwd = account.getUserpwd();
		if (StringUtil.isNotNull(userpwd)) {
			account.setUserpwd(MD5Util.encrypt32(userpwd));
		}
		int num = accountsService.updateByPrimaryKeySelective(account);
		if (num > 0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}

	/**
	 * 查询用户
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/selectone")
	public Object selectAccount(HttpServletRequest request, int id) {
		User accounts = accountsService.selectById(id);
		if (accounts != null) {
			return ResultUtils.success(accounts);
		}
		return ResultUtils.fail("");
	}
}
