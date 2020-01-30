package com.foresee.service;

import java.util.List;

import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.Communitys;
import com.foresee.vo.CommunityVo;
import com.github.pagehelper.PageInfo;

public interface CommunitysService extends BasicsSvc<Communitys> {
	//作废
	List<CommunityVo> selectHome();
	/**
	 * 首页缓存,每天初始化缓存数据
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<Communitys> selectListByType();
	/**
	 * 根据id查询社群详情
	 * @param id
	 * @return
	 */
	public CommunityVo selectCommunityVoById(int id,String userid);
	/**
	 * 查询用户关注的社群信息列表
	 * @param userid
	 * @return
	 */
	PageInfo<CommunityVo> selectFollowList(int userid,int page,int pageSize);
	/**
	 * 搜索社群
	 * @param tagId
	 * @param searchDesc
	 * @param page
	 * @param pageSize
	 * @return
	 */
	PageInfo<CommunityVo> searchCommunitys(String tagId,String searchDesc,int page,int pageSize);
	List<Communitys> selectApllyCommunity(int userid);
}
