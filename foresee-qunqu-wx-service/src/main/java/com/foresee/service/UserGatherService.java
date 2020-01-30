package com.foresee.service;

import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.UserGather;
import com.foresee.vo.UserGatherVo;
import com.github.pagehelper.PageInfo;

public interface UserGatherService extends BasicsSvc< UserGather>{

	PageInfo<UserGatherVo> selectMyGather(int userid,int page,int pageSize);
}
