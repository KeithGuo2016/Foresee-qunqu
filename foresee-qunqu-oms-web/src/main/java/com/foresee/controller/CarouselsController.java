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

import com.foresee.model.Carousels;
import com.foresee.model.User;
import com.foresee.service.CarouselsService;
import com.foresee.util.ListCopyUtil;
import com.foresee.utils.ResultUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 
 * @author wrh
 * 轮播设置
 */
@RestController
@RequestMapping("/carousels")
@Validated
public class CarouselsController {
	@Resource CarouselsService CarouselsService;
	/**
	 * @return
	 * 添加轮播
	 */
	@PostMapping("/save")
	public Object saveCarousels(HttpServletRequest request, @Valid Carousels Carousels) {
		User account =  (User) request.getSession().getAttribute("accounts");
		Carousels.setCreatedDate(new Date());
		Carousels.setCreatedBy(account.getTh1()+"");
		int num=CarouselsService.insertSelective(Carousels);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	
	/**
	 * @return
	 * 更新轮播
	 */
	@PostMapping("/update")
	public Object upCarousels(HttpServletRequest request, @Valid Carousels Carousels) {
		User account =  (User) request.getSession().getAttribute("accounts");
		Carousels.setUpdatedDate(new Date());
		Carousels.setUpdatedBy(account.getTh1()+"");
		int num=CarouselsService.updateByPrimaryKeySelective(Carousels);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 查询轮播
	 */
	@PostMapping("/select")
	public Object selectCarousels(HttpServletRequest request, @Valid int id) {
		Carousels Carousels = CarouselsService.selectByPrimaryKey(id);
		if(Carousels!=null) {
			com.foresee.vo.Carousels vo = new com.foresee.vo.Carousels();
			BeanUtils.copyProperties(Carousels, vo); 
			return ResultUtils.success(vo);
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 查询轮播列表
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/selectlist")
	public Object selectCarouselsList(HttpServletRequest request,Carousels Carousels) {
		 PageHelper.startPage(Carousels.getPage(), Carousels.getLimit());
		 List<Carousels> list = CarouselsService.selectList(Carousels);
		 List<com.foresee.vo.Carousels> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.Carousels());
		 PageInfo<Carousels> pageInfo = new PageInfo<>(list);
		 return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
	
	/**
	 * @return
	 * 删除轮播
	 */
	@PostMapping("/del")
	public Object delCarousels(HttpServletRequest request, int id) {
		int num=CarouselsService.deleteByPrimaryKey(id);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	

}
