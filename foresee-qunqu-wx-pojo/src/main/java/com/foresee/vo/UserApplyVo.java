package com.foresee.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foresee.utils.StringUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户申请表",description = "用户申请表")
@Table(name = "user_apply")
public class UserApplyVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
    private Integer id;

    /**
     * 申请人id
     */
	@ApiModelProperty(value="申请人id",name="userid",example = "286",required = true)
    private Integer userid;

    /**
     * 申请类型（VIP申请、入群申请、管理员申请）
     */
	 
    private String applyType;

    /**
     * 申请内容说明
     */
    private String applyDesc;

    /**
     * 申请类型：1 VIP申请、2入群申请，3管理员申请、4创建社群申请、5征稿申请、6、社刊申请、7推荐发文申请
     */
	@ApiModelProperty(value="申请类型：1 VIP申请、2入群申请，3管理员申请、4创建社群申请、5征稿申请、6、社刊申请、7推荐发文申请",name="applySts",example = "0",required = true)
    
    private String applySts;

    /**
     * 申请人手机号码
     */
	   
    private String phone;

    /**
     * 申请人qq号码
     */
	 private String qq;

    /**
     * 申请人微信号码
     */
	
    private String wechatNum;
	/**
     * 申请业务id
     */
	@ApiModelProperty(value="申请业务id：比如VIP申请 填写用户表id，入群申请填写社群表id",name="applyTargetId",example = "0",required = false)
	 private int applyTargetId;
	

    /**
     * 创建时间
     */
	@ApiModelProperty(hidden = true)
    
    private Date createdDate;

    /**
     * 更新时间
     */
	@JsonIgnore
	@ApiModelProperty(hidden = true)
    
    private Date updatedDate;

    /**
     * 创建人
     */
	@ApiModelProperty(hidden = true)
    
    private String createdBy;

    /**
     * 更新人
     */
	@JsonIgnore
	@ApiModelProperty(hidden = true)
    
    private String updatedBy;

    /**
     * 删除标志
     */
    private Boolean isDeleted;
	private String userName;
	
	private String openId;
    
    private String nickName;
    
    private String headUrl;
    
    
    private String targetName;
    
    private String targetIcon;
    private String applyTypeName;
    private String createdDates;

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

    public String getCreatedDates() {
		return createdDates;
	}

	public void setCreatedDates(String createdDates) {
		this.createdDates = createdDates;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApplyTypeName() {
		return applyTypeName;
	}

	public void setApplyTypeName(String applyTypeName) {
		this.applyTypeName = applyTypeName;
	}

	/**
     * 获取申请人id
     *
     * @return userId - 申请人id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置申请人id
     *
     * @param userid 申请人id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取申请类型（VIP申请、入群申请、管理员申请）
     *
     * @return apply_type - 申请类型（VIP申请、入群申请、管理员申请）
     */
    public String getApplyType() {
        return applyType;
    }

    /**
     * 设置申请类型（VIP申请、入群申请、管理员申请）
     *
     * @param applyType 申请类型（VIP申请、入群申请、管理员申请）
     */
    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    /**
     * 获取申请内容说明
     *
     * @return apply_desc - 申请内容说明
     */
    public String getApplyDesc() {
        return applyDesc;
    }

    /**
     * 设置申请内容说明
     *
     * @param applyDesc 申请内容说明
     */
    public void setApplyDesc(String applyDesc) {
        this.applyDesc = applyDesc;
    }

    /**
     * 获取审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     *
     * @return apply_sts - 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
    public String getApplySts() {
        return applySts;
    }

    /**
     * 设置审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     *
     * @param applySts 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
    public void setApplySts(String applySts) {
        this.applySts = applySts;
    }

    /**
     * 获取申请人手机号码
     *
     * @return phone - 申请人手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置申请人手机号码
     *
     * @param phone 申请人手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取申请人qq号码
     *
     * @return qq - 申请人qq号码
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置申请人qq号码
     *
     * @param qq 申请人qq号码
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取申请人微信号码
     *
     * @return wechat_num - 申请人微信号码
     */
    public String getWechatNum() {
        return wechatNum;
    }

    /**
     * 设置申请人微信号码
     *
     * @param wechatNum 申请人微信号码
     */
    public void setWechatNum(String wechatNum) {
        this.wechatNum = wechatNum;
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

	public int getApplyTargetId() {
		return applyTargetId;
	}

	public void setApplyTargetId(int applyTargetId) {
		this.applyTargetId = applyTargetId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public String getTargetIcon() {
		return targetIcon;
	}

	public void setTargetIcon(String targetIcon) {
		this.targetIcon = targetIcon;
	}
	
	
    
}