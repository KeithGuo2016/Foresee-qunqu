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

import com.foresee.model.Tag;
import com.foresee.model.User;
import com.foresee.service.TagService;
import com.foresee.util.ListCopyUtil;
import com.foresee.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author wangruiheng
 * 标签管理
 */
@RestController
@RequestMapping("/tag")
@Validated
public class TagController {
	@Resource TagService tagService;
	/**
	 * @return
	 * 添加标签
	 */
	@PostMapping("/save")
	public Object saveTag(HttpServletRequest request, @Valid Tag Tag) {
		User account =  (User) request.getSession().getAttribute("accounts");
		Tag.setCreatedDate(new Date());
		Tag.setCreatedBy(account.getTh1()+"");
		int num=tagService.insertSelective(Tag);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	
	/**
	 * @return
	 * 更新标签
	 */
	@PostMapping("/update")
	public Object upTag(HttpServletRequest request, @Valid Tag Tag) {
		User account =  (User) request.getSession().getAttribute("accounts");
		Tag.setUpdatedDate(new Date());
		Tag.setUpdatedBy(account.getTh1()+"");
		int num=tagService.updateByPrimaryKeySelective(Tag);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 查询标签
	 */
	@PostMapping("/select")
	public Object selectTag(HttpServletRequest request, @Valid int id) {
		Tag Tag = tagService.selectByPrimaryKey(id);
		if(Tag!=null) {
			com.foresee.vo.Tag vo = new com.foresee.vo.Tag();
			BeanUtils.copyProperties(Tag, vo); 
			return ResultUtils.success(vo);
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 查询标签列表
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/selectlist")
	public Object selectTagList(HttpServletRequest request,Tag Tag) {
		 PageHelper.startPage(Tag.getPage(), Tag.getLimit());
		 List<Tag> list = tagService.selectList(Tag);
		 List<com.foresee.vo.Tag> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.Tag());
		 PageInfo<Tag> pageInfo = new PageInfo<>(list);
		 return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
	
	/**
	 * @return
	 * 更新标签
	 */
	@PostMapping("/updateisdeleted")
	public Object upTagIsDeleted(HttpServletRequest request, int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		Tag Tag = tagService.selectByPrimaryKey(id);
		Tag.setUpdatedDate(new Date());
		Tag.setUpdatedBy(account.getTh1()+"");
		Tag.setIsDeleted(1);
		int num=tagService.updateByPrimaryKeySelective(Tag);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
}
