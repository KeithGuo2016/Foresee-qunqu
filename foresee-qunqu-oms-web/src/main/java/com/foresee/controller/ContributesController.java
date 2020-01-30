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

import com.foresee.constant.UserRoleConfig;
import com.foresee.model.ContributesWithBLOBs;
import com.foresee.model.User;
import com.foresee.service.ContributesService;
import com.foresee.util.ListCopyUtil;
import com.foresee.utils.DateUtils;
import com.foresee.utils.ResultCode;
import com.foresee.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 征稿管理
 * @author wrh
 *
 */
@RestController

@RequestMapping("/contributes")
@Validated
public class ContributesController {
	@Resource ContributesService contributesService;
	
	/**
	 * @return
	 * 添加征稿
	 */
	@PostMapping("/save")
	public Object saveContributes(HttpServletRequest request,ContributesWithBLOBs contributes) {
		User account =  (User) request.getSession().getAttribute("accounts");
		contributes.setStartDate(DateUtils.getStringToDate(contributes.getStartDateStr(), "yyyy-MM-dd HH:mm:ss")); 
		contributes.setEndDate(DateUtils.getStringToDate(contributes.getEndDateStr(), "yyyy-MM-dd HH:mm:ss")); 
		contributes.setCreatedDate(new Date());
		contributes.setCreatedBy(account.getTh1()+"");
		contributes.setCreateUserid(Integer.valueOf(account.getTh1()==null?"0":account.getTh1()));
		if(contributes.getCommunityId()==null || contributes.getCommunityId()==0){
			contributes.setCommunityId(account.getChannelid());
		}
		int num=contributesService.insertSelective(contributes,account);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 编辑征稿
	 */
	@PostMapping("/update")
	public Object upContributes(HttpServletRequest request,ContributesWithBLOBs contributes) {
		User account =  (User) request.getSession().getAttribute("accounts");
		contributes.setStartDate(DateUtils.getStringToDate(contributes.getStartDateStr(), "yyyy-MM-dd HH:mm:ss")); 
		contributes.setEndDate(DateUtils.getStringToDate(contributes.getEndDateStr(), "yyyy-MM-dd HH:mm:ss"));
		contributes.setUpdatedDate(new Date());
		contributes.setUpdatedBy(account.getTh1()+"");
		
		ContributesWithBLOBs c = contributesService.selectByPrimaryKey(contributes.getId());
		if("1".equals(c.getFlowSts())){
			if(account.getRoleid()!=UserRoleConfig.COMMUNITY_ROLE_USER && account.getRoleid()!= UserRoleConfig.SUPER_ADMINISTRATOR_USER){
				return ResultUtils.warn(ResultCode.PERMISSION_ERROR);
			}
		}
		int num=contributesService.updateByPrimaryKeySelective(contributes);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * id查询征稿
	 */
	@PostMapping("/select")
	public Object selectContributes(HttpServletRequest request,int id) {
		ContributesWithBLOBs contributesWithBLOBs = contributesService.selectByPrimaryKey(id);
		if(contributesWithBLOBs!=null) {
			com.foresee.vo.ContributesWithBLOBs vo = new com.foresee.vo.ContributesWithBLOBs();
			BeanUtils.copyProperties(contributesWithBLOBs, vo);
			return ResultUtils.success(vo);
		}
		return ResultUtils.fail("");
	}
	
	
	/**
	 * @return
	 * 发起审核
	 */
	@PostMapping("/apply")
	public Object applyContributes(HttpServletRequest request,int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		boolean applyContributes = contributesService.applyContributes(id,account);
		if(applyContributes) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	
	/**
	 * @return
	 * 查询征稿列表
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/selectlist")
	public Object selectContributesList(HttpServletRequest request, ContributesWithBLOBs contributes) {
		 PageHelper.startPage(contributes.getPage(), contributes.getLimit());
		 User accounts =  (User) request.getSession().getAttribute("accounts");
		 if(accounts.getChannelid()>0){
			 contributes.setCommunityId(accounts.getChannelid());
		 }
		 List<ContributesWithBLOBs> list = contributesService.selectList(contributes);
		 List<com.foresee.vo.ContributesWithBLOBs> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.ContributesWithBLOBs());
		 PageInfo<ContributesWithBLOBs> pageInfo = new PageInfo<>(list);
		 return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
	
	
	/**
	 * @return
	 * 编辑征稿
	 */
	@PostMapping("/updateisdeleted")
	public Object upContributesIsDeleted(HttpServletRequest request,int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		ContributesWithBLOBs contributes = contributesService.selectByPrimaryKey(id);
		contributes.setUpdatedDate(new Date());
		contributes.setUpdatedBy(account.getTh1()+"");
		contributes.setIsDeleted(1);
		int num=contributesService.updateByPrimaryKeySelective(contributes);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
}
