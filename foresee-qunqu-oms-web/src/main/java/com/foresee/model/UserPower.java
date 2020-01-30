package com.foresee.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户角色表
 * @version 1.0
 * @author wrh
 */
public class UserPower extends PageInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String userid;
	private String isVip;
	private String isAdmin;
	private String isCommunity;
	private Date createdDate;
	private Date updatedDate;
	private String createdBy;
	private String updatedBy;
	private Integer isDeleted;
		
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setUserid(String value) {
		this.userid = value;
	}
	
	public String getUserid() {
		return this.userid;
	}
	
	public void setIsVip(String value) {
		this.isVip = value;
	}
	
	public String getIsVip() {
		return this.isVip;
	}
	
	public void setIsAdmin(String value) {
		this.isAdmin = value;
	}
	
	public String getIsAdmin() {
		return this.isAdmin;
	}
	
	public void setIsCommunity(String value) {
		this.isCommunity = value;
	}
	
	public String getIsCommunity() {
		return this.isCommunity;
	}
	
	public void setCreatedDate(Date value) {
		this.createdDate = value;
	}
	
	public Date getCreatedDate() {
		return this.createdDate;
	}
	
	public void setUpdatedDate(Date value) {
		this.updatedDate = value;
	}
	
	public Date getUpdatedDate() {
		return this.updatedDate;
	}
	
	public void setCreatedBy(String value) {
		this.createdBy = value;
	}
	
	public String getCreatedBy() {
		return this.createdBy;
	}
	
	public void setUpdatedBy(String value) {
		this.updatedBy = value;
	}
	
	public String getUpdatedBy() {
		return this.updatedBy;
	}
	
	public void setIsDeleted(Integer value) {
		this.isDeleted = value;
	}
	
	public Integer getIsDeleted() {
		return this.isDeleted;
	}
	
}
