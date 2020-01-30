package com.foresee.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.model.User;
import com.foresee.model.UserApply;
import com.foresee.service.UserApplyService;
import com.foresee.util.ListCopyUtil;
import com.foresee.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author wangruiheng
 * 用户申请管理
 */
@RestController
@RequestMapping("/userapply")
@Validated
public class UserApplyController {

	@Resource UserApplyService userApplyService;
	
	
	/**
	 * 通过申请
	 * @param request
	 * @param userApply
	 * @return
	 */
	@RequestMapping("/adoptuserapply")
	public Object adoptUserApply(HttpServletRequest request, int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		boolean fa = userApplyService.adoptUserApply(account, id,"1","");
		if(fa){
			return ResultUtils.success("");
		}
		return ResultUtils.error("");
	}
	
	/**
	 * 拒绝申请
	 * @param request
	 * @param userApply
	 * @return
	 */
	@RequestMapping("/refuseuserapply")
	public Object refuseUserApply(HttpServletRequest request,  UserApply userApply) {
		User account =  (User) request.getSession().getAttribute("accounts");
		boolean fa = userApplyService.adoptUserApply(account, userApply.getId(),"2",userApply.getNewsContent());
		if(fa){
			return ResultUtils.success("");
		}
		return ResultUtils.error("");
	}
	
	
	
	
	/**
	 * 用户申请列表
	 * @param request
	 * @param userApply
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectlist")
	public Object selectAccountsList(HttpServletRequest request, UserApply userApply) {
		User account = (User) request.getSession().getAttribute("accounts");
		if(account.getChannelid()!=null && account.getChannelid()>0){
			userApply.setChannelId(account.getChannelid());
		}
		PageHelper.startPage(userApply.getPage(), userApply.getLimit());
		List<UserApply> list = userApplyService.selectList(userApply);
		List<com.foresee.vo.UserApply> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.UserApply());
		PageInfo<UserApply> pageInfo = new PageInfo<>(list);
		return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
	
	/**
	 * 用户申请详情
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/select")
	public Object selectAccountsList(HttpServletRequest request, int id) {
		com.foresee.vo.UserApply vo = new com.foresee.vo.UserApply();
		UserApply userApply = userApplyService.selectByPrimaryKey(id);
		BeanUtils.copyProperties(userApply, vo);
		return ResultUtils.success(vo);
	}
	
	/**
	 * 用户入群申请
	 * @param request
	 * @param userApply
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectListJoinCommunity")
	public Object selectListJoinCommunity(HttpServletRequest request, UserApply userApply) {
		User account = (User) request.getSession().getAttribute("accounts");
		if(account.getChannelid()!=null && account.getChannelid()>0){
			userApply.setApplyTargetId(account.getChannelid()); 
		}
		PageHelper.startPage(userApply.getPage(), userApply.getLimit());
		List<UserApply> list = userApplyService.selectListJoinCommunity(userApply);
		List<com.foresee.vo.UserApply> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.UserApply());
		PageInfo<UserApply> pageInfo = new PageInfo<>(list);
		return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
	
	/**
	 * 删除
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/updateisdeleted")
	public Object upUserApplyIsDeleted(HttpServletRequest request, int id) {
		/*User account =  (User) request.getSession().getAttribute("accounts");
		UserApply userApply = userApplyService.selectByPrimaryKey(id);
		userApply.setUpdatedDate(new Date());
		userApply.setUpdatedBy(account.getTh1()+"");
		userApply.setIsDeleted(1);*/
		int num = userApplyService.deleteByPrimaryKey(id);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
}
