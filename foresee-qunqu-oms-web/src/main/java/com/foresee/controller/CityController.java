package com.foresee.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.utils.ResultUtils;
import com.foresee.model.CommonCity;
import com.foresee.model.CommonProvince;
import com.foresee.service.CityService;
/**
 * 
 * @author wrh
 * 城市
 */
@RestController

@RequestMapping("/city")
@Validated
public class CityController {
	@Resource  
	private CityService cityService;
	
	/**
	 * 查询城市列表
	 * @param request
	 * @param account
	 * @return
	 */
	@RequestMapping(value = "/province",method = RequestMethod.POST)
	public Object allProvince(HttpServletRequest request) {
		List<CommonProvince> selectProvince = cityService.allProvince();
		return	ResultUtils.success(selectProvince);
	}
	

	@RequestMapping(value = "/all",method = RequestMethod.POST)
	public Object cityByPid(HttpServletRequest request, Integer pid) {
		List<CommonCity> citys = cityService.selectByPid(pid);
		return ResultUtils.success(citys);
	}
}
