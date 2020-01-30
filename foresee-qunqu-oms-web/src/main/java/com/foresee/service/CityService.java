package com.foresee.service;

import java.util.List;

import com.foresee.model.CommonCity;
import com.foresee.model.CommonProvince;


public interface CityService {

	List<CommonProvince> allProvince();
	
	List<CommonCity> selectByPid(Integer pid);
	
	CommonProvince selectProvinceById(Integer id);
	
	CommonCity selectByCityId(Integer id);
}
