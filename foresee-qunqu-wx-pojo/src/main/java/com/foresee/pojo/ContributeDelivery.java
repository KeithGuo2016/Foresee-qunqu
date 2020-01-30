package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.foresee.utils.StringUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户投稿表",description = "用户投稿表")
@Table(name = "contribute_delivery")
public class ContributeDelivery implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(hidden = true)
	@Id
    private Integer id;

    /**
     * 投稿人ID
     */
	@ApiModelProperty(value=" 投稿人id",name="userid",example = "286",required = true)
    private Integer userid;

    /**
     * 约稿表ID
     */
	@ApiModelProperty(value=" 约稿需求id",name="contributesId",example = "281",required = true)
    @Column(name = "contributes_id")
    private Integer contributesId;

    /**
     * 投稿标题
     */
	@ApiModelProperty(value="投稿标题",name="deliveryTitle",example = "关于银行小微企业发展方向的研究",required = true)
    @Column(name = "delivery_title")
    private String deliveryTitle;

    /**
     * 封面图
     */
	@ApiModelProperty(value="封面图",name="deliveryIcon",example = "file",required = false)
    @Column(name = "delivery_icon")
    private String deliveryIcon;

    /**
     * 内容
     */
	@ApiModelProperty(value="内容",name="deliveryContent",example = "投稿内容。。。。",required = true)
    @Column(name = "delivery_content")
    private String deliveryContent;

    /**
     * 是否选中(1选中，0没选中)
     */
	@ApiModelProperty(value="是否选中(1选中，0没选中)",name="isSelect",example = "0",required = true)
    @Column(name = "is_select")
    private Integer isSelect;

    /**
     * 付款状态（1已付，0未付）
     */
	@ApiModelProperty(value="付款状态（1已付，0未付）",name="isPay",example = "0",required = true)
    @Column(name = "is_pay")
    private Integer isPay;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 删除标志
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    //阅读量
    @Column(name = "read_num")
    private Integer readNum;


    //邀请码
    @ApiModelProperty(value="邀请码",name="inviteCode",example = "123456",required = false)
    @Transient
    private String inviteCode;
    public String getInviteCode() {
        return inviteCode;
    }
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    //活动id
    @ApiModelProperty(value="邀请码",name="eid",example = "123456",required = false)
    @Transient
    private String eid;
    public String getEid() {
        return eid;
    }
    public void setEid(String eid) {
        this.eid = eid;
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
    public Integer getIsSelect() {
        return isSelect;
    }

    /**
     * 设置是否选中(1选中，0没选中)
     *
     * @param isSelect 是否选中(1选中，0没选中)
     */
    public void setIsSelect(Integer isSelect) {
        this.isSelect = isSelect;
    }

    /**
     * 获取付款状态（1已付，0未付）
     *
     * @return is_pay - 付款状态（1已付，0未付）
     */
    public Integer getIsPay() {
        return isPay;
    }

    /**
     * 设置付款状态（1已付，0未付）
     *
     * @param isPay 付款状态（1已付，0未付）
     */
    public void setIsPay(Integer isPay) {
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