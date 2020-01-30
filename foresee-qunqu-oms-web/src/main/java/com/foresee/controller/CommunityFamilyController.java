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

import com.foresee.model.CommunityFamily;
import com.foresee.model.User;
import com.foresee.service.CommunityFamilyService;
import com.foresee.util.ListCopyUtil;
import com.foresee.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author wrh
 * 社群类别
 */
@RestController

@RequestMapping("/communityfamily")
@Validated
public class CommunityFamilyController {

	@Resource CommunityFamilyService communityFamilyService;
	/**
	 * @return
	 * 添加类别
	 */
	@PostMapping("/save")
	public Object saveCommunityFamily(HttpServletRequest request, @Valid CommunityFamily communityFamily) {
		User account =  (User) request.getSession().getAttribute("accounts");
		communityFamily.setCreatedDate(new Date());
		communityFamily.setCreatedBy(account.getTh1()+"");
		int num=communityFamilyService.insertSelective(communityFamily);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 更新类别
	 */
	@PostMapping("/update")
	public Object upCommunityFamily(HttpServletRequest request, @Valid CommunityFamily communityFamily) {
		User account =  (User) request.getSession().getAttribute("accounts");
		communityFamily.setUpdatedDate(new Date());
		communityFamily.setUpdatedBy(account.getTh1()+"");
		int num=communityFamilyService.updateByPrimaryKeySelective(communityFamily);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 查询类别
	 */
	@PostMapping("/select")
	public Object selectCommunityFamily(HttpServletRequest request, @Valid int id) {
		CommunityFamily communityFamily = communityFamilyService.selectByPrimaryKey(id);
		if(communityFamily!=null) {
			com.foresee.vo.CommunityFamily vo = new com.foresee.vo.CommunityFamily();
			BeanUtils.copyProperties(communityFamily, vo); 
			return ResultUtils.success(vo);
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 查询类别列表
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/selectlist")
	public Object selectCommunityFamilyList(HttpServletRequest request,CommunityFamily communityFamily) {
		 PageHelper.startPage(communityFamily.getPage(), communityFamily.getLimit());
		 List<CommunityFamily> list = communityFamilyService.selectList(communityFamily);
		 List<com.foresee.vo.CommunityFamily> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.CommunityFamily());
		 PageInfo<CommunityFamily> pageInfo = new PageInfo<>(list);
		 return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
	
	/**
	 * @return
	 * 更新类别
	 */
	@PostMapping("/updateisdeleted")
	public Object upCommunityFamilyIsDeleted(HttpServletRequest request, int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		CommunityFamily communityFamily = communityFamilyService.selectByPrimaryKey(id);
		communityFamily.setUpdatedDate(new Date());
		communityFamily.setUpdatedBy(account.getTh1()+"");
		communityFamily.setIsDeleted(1);
		int num=communityFamilyService.updateByPrimaryKeySelective(communityFamily);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
}
