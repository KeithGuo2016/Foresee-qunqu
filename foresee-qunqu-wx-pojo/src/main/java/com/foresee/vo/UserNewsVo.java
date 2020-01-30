package com.foresee.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


public class UserNewsVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    private Integer id;

    /**
     * 提醒用户ID
     */
   
    private Integer userid;

    /**
     * 标题
     */
   
    private String newsTitle;

    /**
     * 消息类型
     */
   
    private String newsType;

    /**
     * 内容
     */
    
    private String newsContent;

    /**
     * 消息阅读状态（1已读、0未读）
     */
  
    private String newsSts;

    /**
     * 创建时间
     */
  
    private Date createdDate;

    /**
     * 更新时间
     */
  
    private Date updatedDate;

    /**
     * 创建人
     */

    private String createdBy;

    /**
     * 更新人
     */
    private String updatedBy;

    /**
     * 删除标志
     */
    private Boolean isDeleted;
    private String dateString;
    
    

    public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	/**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取提醒用户ID
     *
     * @return userId - 提醒用户ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置提醒用户ID
     *
     * @param userid 提醒用户ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取标题
     *
     * @return news_title - 标题
     */
    public String getNewsTitle() {
        return newsTitle;
    }

    /**
     * 设置标题
     *
     * @param newsTitle 标题
     */
    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    /**
     * 获取消息类型
     *
     * @return news_type - 消息类型
     */
    public String getNewsType() {
        return newsType;
    }

    /**
     * 设置消息类型
     *
     * @param newsType 消息类型
     */
    public void setNewsType(String newsType) {
        this.newsType = newsType;
    }

    /**
     * 获取内容
     *
     * @return news_content - 内容
     */
    public String getNewsContent() {
        return newsContent;
    }

    /**
     * 设置内容
     *
     * @param newsContent 内容
     */
    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    /**
     * 获取消息阅读状态（1已读、0未读）
     *
     * @return news_sts - 消息阅读状态（1已读、0未读）
     */
    public String getNewsSts() {
        return newsSts;
    }

    /**
     * 设置消息阅读状态（1已读、0未读）
     *
     * @param newsSts 消息阅读状态（1已读、0未读）
     */
    public void setNewsSts(String newsSts) {
        this.newsSts = newsSts;
    }

    /**
     * 获取创建时间
     *
     * @return created_date - 创建时间
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * 设置创建时间
     *
     * @param createdDate 创建时间
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 获取更新时间
     *
     * @return updated_date - 更新时间
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * 设置更新时间
     *
     * @param updatedDate 更新时间
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取更新人
     *
     * @return updated_by - 更新人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新人
     *
     * @param updatedBy 更新人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取删除标志
     *
     * @return is_deleted - 删除标志
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置删除标志
     *
     * @param isDeleted 删除标志
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}