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

import com.foresee.constant.UserRoleConfig;
import com.foresee.exception.ResultException;
import com.foresee.model.Magazines;
import com.foresee.model.User;
import com.foresee.service.MagazinesService;
import com.foresee.util.ListCopyUtil;
import com.foresee.utils.DateUtils;
import com.foresee.utils.ResultCode;
import com.foresee.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
/**
 * 社刊管理
 * @author wrh
 *
 */
@RestController

@RequestMapping("/magazines")
@Validated
public class MagazinesController {
	@Resource MagazinesService magazinesService;
	/**
	 * @return
	 * 添加社刊
	 */
	@PostMapping("/save")
	public Object saveMagazines(HttpServletRequest request, @Valid Magazines magazines) {
		User account =  (User) request.getSession().getAttribute("accounts");
		
		//超级管理员
		if(account.getRoleid() == UserRoleConfig.SUPER_ADMINISTRATOR_USER){
			if(magazines.getCommunitysId()==null || magazines.getCommunitysId()==0){
				throw new ResultException(ResultCode.MAGAZINES_COMMUNITYSID_NULL_ERROR);
			}
			magazines.setFlowSts("1");
		}else{
			if(account.getChannelid()!=null && account.getChannelid()>0){
				magazines.setCommunitysId(account.getChannelid());
			}
		}
		magazines.setCreatedDate(new Date());
		magazines.setCreatedBy(account.getTh1()+"");
		magazines.setStartDate(DateUtils.getStringToDate(magazines.getStartDateStr(), "yyyy-MM-dd HH:mm:ss"));
		magazines.setCreateUserId(Integer.valueOf(account.getTh1()==null?"0":account.getTh1()));
		int num=magazinesService.insertSelective(magazines);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 更新社刊
	 */
	@PostMapping("/update")
	public Object upMagazines(HttpServletRequest request, @Valid Magazines magazines) {
		User account =  (User) request.getSession().getAttribute("accounts");
		magazinesService.ckeckMagazinesUser(account, magazines.getId());;
		magazines.setUpdatedDate(new Date());
		magazines.setUpdatedBy(account.getTh1()+"");
		magazines.setStartDate(DateUtils.getStringToDate(magazines.getStartDateStr(), "yyyy-MM-dd HH:mm:ss"));
		int num=magazinesService.updateByPrimaryKeySelective(magazines);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 查询社刊
	 */
	@PostMapping("/select")
	public Object selectMagazines(HttpServletRequest request, @Valid int id) {
		Magazines magazines = magazinesService.selectByPrimaryKey(id);
		if(magazines!=null) {
			com.foresee.vo.Magazines vo = new com.foresee.vo.Magazines();
			BeanUtils.copyProperties(magazines, vo);
			return ResultUtils.success(vo);
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 发起审核
	 */
	@PostMapping("/apply")
	public Object applyMagazines(HttpServletRequest request, int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		boolean applyMagazines = magazinesService.applyMagazines(id,account);
		if(applyMagazines) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 查询社刊列表
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/selectlist")
	public Object selectMagazinesList(HttpServletRequest request,Magazines magazines) {
		 PageHelper.startPage(magazines.getPage(), magazines.getLimit());
		 User accounts =  (User) request.getSession().getAttribute("accounts");
		 if(accounts.getChannelid()>0){
			 magazines.setCommunitysId(accounts.getChannelid());
		 }
		 List<Magazines> list = magazinesService.selectList(magazines);
		 List<com.foresee.vo.Magazines> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.Magazines());
		 PageInfo<Magazines> pageInfo = new PageInfo<>(list);
		 return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
	
	/**
	 * @return
	 * 更新社刊
	 */
	@PostMapping("/updateisdeleted")
	public Object upMagazinesIsDeleted(HttpServletRequest request,int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		magazinesService.ckeckMagazinesUser(account, id);;
		Magazines magazines = magazinesService.selectByPrimaryKey(id);
		magazines.setUpdatedDate(new Date());
		magazines.setUpdatedBy(account.getTh1()+"");
		magazines.setIsDeleted(1);
		int num=magazinesService.updateByPrimaryKeySelectiveIsDeleted(magazines);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
}
