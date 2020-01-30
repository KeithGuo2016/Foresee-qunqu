package com.foresee.service;



import com.foresee.baseService.BasicsSvc;
import com.foresee.pojo.Magazines;
import com.foresee.vo.MagazinesVo;
import com.github.pagehelper.PageInfo;

public interface MagazinesService extends BasicsSvc<Magazines>{
	PageInfo<MagazinesVo> selectList(int page,int pageSize);
	PageInfo<MagazinesVo> selectByCommunitysId(int communitysId,int page,int pageSize);
	PageInfo<MagazinesVo> searchMagazine(String tagId,String searchDesc, String communitysId,int page,int pageSize);
	MagazinesVo selectById(int magazinesid);
}
