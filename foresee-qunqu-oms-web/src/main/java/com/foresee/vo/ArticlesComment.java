package com.foresee.vo;

import java.util.Date;
import com.foresee.utils.DateUtils;
/**
 * 文章评论表
 * @version 1.0
 * @author wrh
 */
public class ArticlesComment{

	private Integer id;
	
	private Integer userid;
	
	private Integer articlesId;
	
	private Integer pid;
	
	private Integer toUserId;
	
	private String content;
	
	private String createdDate;
	
	private String createdBy;
	
	private Integer isDeleted;
	
	private String nickName;
	private String toNickName;
	
	private String headUrl;
    private String toHeadUrl;
    
	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getToHeadUrl() {
		return toHeadUrl;
	}

	public void setToHeadUrl(String toHeadUrl) {
		this.toHeadUrl = toHeadUrl;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getToNickName() {
		return toNickName;
	}

	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}
		
	public void setId(Integer value) {
		this.id = value;
	}
	
	public Integer getId() {
		return this.id;
	}
	public void setUserid(Integer value) {
		this.userid = value;
	}
	
	public Integer getUserid() {
		return this.userid;
	}
	public void setArticlesId(Integer value) {
		this.articlesId = value;
	}
	
	public Integer getArticlesId() {
		return this.articlesId;
	}
	public void setPid(Integer value) {
		this.pid = value;
	}
	
	public Integer getPid() {
		return this.pid;
	}
	public void setToUserId(Integer value) {
		this.toUserId = value;
	}
	
	public Integer getToUserId() {
		return this.toUserId;
	}
	public void setContent(String value) {
		this.content = value;
	}
	
	public String getContent() {
		return this.content;
	}
	public void setCreatedDate(Date value) {
		this.createdDate = DateUtils.getDateToString(value, "yyyy-MM-dd HH:mm:ss");
	}
	
	public String getCreatedDate() {
		return this.createdDate;
	}
	public void setCreatedBy(String value) {
		this.createdBy = value;
	}
	
	public String getCreatedBy() {
		return this.createdBy;
	}
	public void setIsDeleted(Integer value) {
		this.isDeleted = value;
	}
	
	public Integer getIsDeleted() {
		return this.isDeleted;
	}
}
