package com.foresee.vo;

import java.util.List;


public class CommunityUserVo {
		
	private List<WechatUserVo> userList;
	private Integer sumNum;
	private String title;
	private Integer id;
	
	public List<WechatUserVo> getUserList() {
		return userList;
	}
	public void setUserList(List<WechatUserVo> userList) {
		this.userList = userList;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSumNum() {
		return sumNum;
	}
	public void setSumNum(Integer sumNum) {
		this.sumNum = sumNum;
	}
	
}
