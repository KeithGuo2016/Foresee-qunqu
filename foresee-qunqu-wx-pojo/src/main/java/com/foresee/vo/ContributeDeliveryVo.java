package com.foresee.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.foresee.utils.StringUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


public class ContributeDeliveryVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    private Integer id;

    /**
     * 投稿人ID
     */
	
    private Integer userid;

    /**
     * 约稿表ID
     */
	
    private Integer contributesId;

    /**
     * 投稿标题
     */

    private String deliveryTitle;

    /**
     * 封面图
     */
	
    private String deliveryIcon;

    /**
     * 内容
     */
	
    private String deliveryContent;

    /**
     * 是否选中(1选中，0没选中)
     */
	
    private String isSelect;

    /**
     * 付款状态（1已付，0未付）
     */

    private String isPay;

    /**
     * 创建时间
     */
   
    private Date createdDate;

    /**
     * 创建人
     */
   
    private String createdBy;

    /**
     * 删除标志
     */
   
    private Boolean isDeleted;
    //阅读量
    @Column(name = "read_num")
    private Integer readNum;
    
    private String userName;
    private String userIcon;
    private String deliveryDate;

    
    
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
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
     * 获取投稿人ID
     *
     * @return userid - 投稿人ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置投稿人ID
     *
     * @param userid 投稿人ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取约稿表ID
     *
     * @return contributes_id - 约稿表ID
     */
    public Integer getContributesId() {
        return contributesId;
    }

    /**
     * 设置约稿表ID
     *
     * @param contributesId 约稿表ID
     */
    public void setContributesId(Integer contributesId) {
        this.contributesId = contributesId;
    }

    /**
     * 获取投稿标题
     *
     * @return delivery_title - 投稿标题
     */
    public String getDeliveryTitle() {
        return deliveryTitle;
    }

    /**
     * 设置投稿标题
     *
     * @param deliveryTitle 投稿标题
     */
    public void setDeliveryTitle(String deliveryTitle) {
        this.deliveryTitle = deliveryTitle;
    }

    /**
     * 获取封面图
     *
     * @return delivery_icon - 封面图
     */
    public String getDeliveryIcon() {
        return deliveryIcon;
    }

    /**
     * 设置封面图
     *
     * @param deliveryIcon 封面图
     */
    public void setDeliveryIcon(String deliveryIcon) {
        this.deliveryIcon = deliveryIcon;
    }

    /**
     * 获取内容
     *
     * @return delivery_content - 内容
     */
    public String getDeliveryContent() {
        return deliveryContent;
    }

    /**
     * 设置内容
     *
     * @param deliveryContent 内容
     */
    public void setDeliveryContent(String deliveryContent) {
        this.deliveryContent = deliveryContent;
    }

    /**
     * 获取是否选中(1选中，0没选中)
     *
     * @return is_select - 是否选中(1选中，0没选中)
     */
    public String getIsSelect() {
        return isSelect;
    }

    /**
     * 设置是否选中(1选中，0没选中)
     *
     * @param isSelect 是否选中(1选中，0没选中)
     */
    public void setIsSelect(String isSelect) {
        this.isSelect = isSelect;
    }

    /**
     * 获取付款状态（1已付，0未付）
     *
     * @return is_pay - 付款状态（1已付，0未付）
     */
    public String getIsPay() {
        return isPay;
    }

    /**
     * 设置付款状态（1已付，0未付）
     *
     * @param isPay 付款状态（1已付，0未付）
     */
    public void setIsPay(String isPay) {
        this.isPay = isPay;
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
     * 获取删除标志
     *
     * @return is_deleted - 删除标志
     */
    public Boolean getIsDeleted() {
    	if(StringUtil.isBlank(isDeleted)) {
    		isDeleted = false;
    	}
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

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
	}
    
    
}