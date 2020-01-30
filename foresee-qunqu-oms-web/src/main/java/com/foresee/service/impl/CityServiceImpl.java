package com.foresee.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.CommonCityMapper;
import com.foresee.dao.CommonProvinceMapper;
import com.foresee.model.CommonCity;
import com.foresee.model.CommonProvince;
import com.foresee.service.CityService;

@Service
@Transactional(rollbackFor = Exception.class)
public class CityServiceImpl implements CityService{
	
	
	@Autowired
	CommonProvinceMapper commonProvinceMapper;
	
	@Autowired
	CommonCityMapper commonCityMapper;

	public List<CommonProvince> allProvince(){
		return commonProvinceMapper.allProvince(null);
	}
	
	public List<CommonCity> selectByPid(Integer id){
		return commonCityMapper.selectByPid(id,null);
	}
	
	public CommonProvince selectProvinceById(Integer id){
		List<CommonProvince> province = commonProvinceMapper.allProvince(id);
		if(province != null && province.size() > 0) {
			return province.get(0);
		}
		return null;
	}

	public CommonCity selectByCityId(Integer id) {
		List<CommonCity> commonCity = commonCityMapper.selectByPid(null,id);
		if(commonCity != null && commonCity.size() > 0) {
			return commonCity.get(0);
		}
		return null;
	}
}
