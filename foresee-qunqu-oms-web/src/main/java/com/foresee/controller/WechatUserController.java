package com.foresee.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.model.WechatUser;
import com.foresee.model.User;
import com.foresee.service.WechatUserService;
import com.foresee.util.ListCopyUtil;
import com.foresee.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 用户管理
 * @author wrh
 *
 */
@RestController

@RequestMapping("/wechatuser")
@Validated
public class WechatUserController {
	@Resource WechatUserService wechatUserService;
	
	/**
	 * @param request
	 * @param account
	 * @return
	 * 设置VIP
	 */
	@PostMapping("/setvip")
	public Object setVipWechatUser(HttpServletRequest request,int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		boolean fa = wechatUserService.setVipWechatUser(account, id);
		if(fa) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @param request
	 * @param account
	 * @return
	 * 设置管理员
	 */
	@PostMapping("/setadmin")
	public Object setAdminWechatUser(HttpServletRequest request,int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		boolean fa = wechatUserService.setAdminWechatUser(account, id);
		if(fa) {
			
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	/**
	 * @param request
	 * @param account
	 * @return
	 * 取消社群管理员
	 */
	@PostMapping("/quxiaoadmin")
	public Object quxiaoAdminWechatUser(HttpServletRequest request,int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		boolean fa = wechatUserService.quxiaoAdminWechatUser(account, id);
		if(fa) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @param request
	 * @param account
	 * @return
	 * 设置后台用户为社长角色
	 */
	@PostMapping("/setusercommunity")
	public Object setUserCommunity(HttpServletRequest request,int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		boolean fa = wechatUserService.setUserCommunity(account, id);
		if(fa) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @param request
	 * @param account
	 * @return
	 * 设置后台用户为管理员角色
	 */
	@PostMapping("/setuseradmin")
	public Object setUserAdmin(HttpServletRequest request,int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		boolean fa = wechatUserService.setUserAdmin(account, id);
		if(fa) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @param request
	 * @param account
	 * @return
	 * 踢出社群
	 */
	@PostMapping("/kickout")
	public Object kickOutWechatUser(HttpServletRequest request, int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		/*boolean fa=wechatUserService.kickOutWechatUser(account, id);
		if(fa) {
			return ResultUtils.success("");
		}*/
		return ResultUtils.fail("");
	}
	
	/**
	 * 用户id查询
	 * @param request
	 * @param account
	 * @return
	 */
	@RequestMapping("/select")
	public Object selectWechatUser(HttpServletRequest request, int id) {
		WechatUser wechatUser = wechatUserService.selectByPrimaryKey(id);
		if(wechatUser!=null) {
			com.foresee.vo.WechatUser vo = new com.foresee.vo.WechatUser();
			BeanUtils.copyProperties(wechatUser, vo);
			return ResultUtils.success(vo);
		}
		return ResultUtils.error("");
	}
	
	
	/**
	 * 查询用户列表
	 * @param request
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectlist")
	public Object selectWechatUserList(HttpServletRequest request, WechatUser wechatUser) {
		PageHelper.startPage(wechatUser.getPage(), wechatUser.getLimit());
		User accounts =  (User) request.getSession().getAttribute("accounts");
		if(accounts.getChannelid()>0){
			wechatUser.setCommunityid(accounts.getChannelid());
		}
		List<WechatUser> list = wechatUserService.selectList(wechatUser);
		List<com.foresee.vo.WechatUser> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.WechatUser());
		PageInfo<WechatUser> pageInfo = new PageInfo<>(list);
		return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
	
	
	
	/**
	 * 查询社群关注用户列表
	 * @param request
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectlistcommunitysfollow")
	public Object selectListCommunitysFollow(HttpServletRequest request, WechatUser wechatUser) {
		PageHelper.startPage(wechatUser.getPage(), wechatUser.getLimit());
		User accounts =  (User) request.getSession().getAttribute("accounts");
		if(accounts.getChannelid()>0){
			wechatUser.setCommunitysId(accounts.getChannelid());
		}
		List<WechatUser> list = wechatUserService.selectListCommunitysFollow(wechatUser);
		List<com.foresee.vo.WechatUser> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.WechatUser());
		PageInfo<WechatUser> pageInfo = new PageInfo<>(list);
		return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
	
	/**
	 * @param request
	 * @param id
	 * @return
	 * 删除用户
	 */
	@PostMapping("/updateisdeleted")
	public Object upWechatUserIsDeleted(HttpServletRequest request, int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		WechatUser wechatUser = wechatUserService.selectByPrimaryKey(id);
		wechatUser.setUpdatedDate(new Date());
		wechatUser.setUpdatedBy(account.getTh1()+"");
		wechatUser.setIsDeleted(1);
		int num =wechatUserService.updateByPrimaryKeySelective(wechatUser,account,id);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
}
