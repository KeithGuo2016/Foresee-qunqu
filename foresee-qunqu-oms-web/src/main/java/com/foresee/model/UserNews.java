package com.foresee.model;

import java.io.Serializable;
import java.util.Date;
/**
 * 用户消息表
 * @version 1.0
 * @author wrh
 */
public class UserNews extends PageInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private Integer userId;
	private String newsTitle;
	private String newsType;
	private String newsContent;
	private String newsSts;
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
	
	public void setUserId(Integer value) {
		this.userId = value;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public void setNewsTitle(String value) {
		this.newsTitle = value;
	}
	
	public String getNewsTitle() {
		return this.newsTitle;
	}
	
	public void setNewsType(String value) {
		this.newsType = value;
	}
	
	public String getNewsType() {
		return this.newsType;
	}
	
	public void setNewsContent(String value) {
		this.newsContent = value;
	}
	
	public String getNewsContent() {
		return this.newsContent;
	}
	
	public void setNewsSts(String value) {
		this.newsSts = value;
	}
	
	public String getNewsSts() {
		return this.newsSts;
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
