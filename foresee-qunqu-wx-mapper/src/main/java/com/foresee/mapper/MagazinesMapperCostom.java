package com.foresee.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.foresee.utils.MyMapper;
import com.foresee.vo.MagazinesVo;

public interface MagazinesMapperCostom extends MyMapper<MagazinesVo> {
	public List<MagazinesVo> selectByCommunitysId(@Param("communitysId")int communitysId);
	
	public List<MagazinesVo> selectList();
	public List<MagazinesVo> searchMagazine(@Param("tagId") String tagId,@Param("searchDesc") String searchDesc,@Param("communitysId")String communitysId);
	public MagazinesVo selectById(@Param("magazinesid")int magazinesid);
}