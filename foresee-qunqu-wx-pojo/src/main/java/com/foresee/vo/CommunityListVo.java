package com.foresee.vo;

import java.util.List;


import com.foresee.pojo.CommunityFamily;

public class CommunityListVo {
	 
	  private List<CommunityVo> listAther;
	  private List<CommunityFamily> list;
	  
	  private List<String> names;
	  private List<FamilyVo> familys;
	  
	  
	public List<String> getNames() {
		return names;
	}
	public void setNames(List<String> names) {
		this.names = names;
	}
	public List<FamilyVo> getFamilys() {
		return familys;
	}
	public void setFamilys(List<FamilyVo> familys) {
		this.familys = familys;
	}
	public List<CommunityVo> getListAther() {
		return listAther;
	}
	public void setListAther(List<CommunityVo> listAther) {
		this.listAther = listAther;
	}
	public List<CommunityFamily> getList() {
		return list;
	}
	public void setList(List<CommunityFamily> list) {
		this.list = list;
	}

}
