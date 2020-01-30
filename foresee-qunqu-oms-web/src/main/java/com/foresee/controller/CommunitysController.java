package com.foresee.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.model.Communitys;
import com.foresee.model.User;
import com.foresee.service.CommunitysService;
import com.foresee.util.ListCopyUtil;
import com.foresee.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 社群管理
 * @author wrh
 *
 */
@RestController

@RequestMapping("/communitys")
@Validated
public class CommunitysController {
	
	@Resource CommunitysService communitysService;
	/**
	 * @return
	 * 添加社群
	 */
	@PostMapping("/save")
	public Object saveCommunitys(HttpServletRequest request, @Valid Communitys communitys) {
		User account =  (User) request.getSession().getAttribute("accounts");
		communitys.setCreatedDate(new Date());
		communitys.setCreatedBy(account.getTh1()+"");
		communitys.setFlowSts("0");
		int num=communitysService.insertSelective(communitys);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	
	/**
	 * @return
	 * 修改社群
	 */
	@PostMapping("/update")
	public Object upCommunitys(HttpServletRequest request, @Valid Communitys communitys) {
		User account =  (User) request.getSession().getAttribute("accounts");
		communitys.setUpdatedDate(new Date());
		communitys.setUpdatedBy(account.getTh1()+"");
		int num=communitysService.updateByPrimaryKey(communitys);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 审核通过
	 */
	@PostMapping("/adopt")
	public Object adoptCommunitys(HttpServletRequest request, @Valid int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		int num=communitysService.adoptCommunitys(account,id);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 审核拒绝
	 */
	@PostMapping("/refuse")
	public Object refuseCommunitys(HttpServletRequest request, Communitys communitys) {
		User account =  (User) request.getSession().getAttribute("accounts");
		int num=communitysService.refuseCommunitys(account, communitys.getId(),communitys.getNewsContent());
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	
	/**
	 * @return
	 * 查询社群
	 */
	@PostMapping("/select")
	public Object selectCommunitys(HttpServletRequest request,int id) {
		Communitys communitys = communitysService.selectByPrimaryKey(id);
		if(communitys!=null) {
			com.foresee.vo.Communitys vo = new com.foresee.vo.Communitys();
			BeanUtils.copyProperties(communitys, vo);
			return ResultUtils.success(vo);
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * 查询社群列表
	 * @param request
	 * @param account
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/selectlist")
	public Object selectAccountsList(HttpServletRequest request, Communitys communitys) {
		PageHelper.startPage(communitys.getPage(), communitys.getLimit());
		 User accounts =  (User) request.getSession().getAttribute("accounts");
		 if(accounts.getChannelid()>0){
			 communitys.setId(accounts.getChannelid());
		 }
		List<Communitys> list = communitysService.selectList(communitys);
		List<com.foresee.vo.Communitys> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.Communitys());
		PageInfo<Communitys> pageInfo = new PageInfo<>(list);
		return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
	/**
	 * @return
	 * 修改社群
	 */
	@PostMapping("/updateisdeleted")
	public Object upCommunitysIsDeleted(HttpServletRequest request, int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		Communitys communitys = communitysService.selectByPrimaryKey(id);
		communitys.setUpdatedDate(new Date());
		communitys.setUpdatedBy(account.getTh1()+"");
		communitys.setIsDeleted(1);
		int num=communitysService.updateIsDeleted(communitys);
		//修改社群所有用户信息：
		
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
}
