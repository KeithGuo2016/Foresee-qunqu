package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.foresee.utils.StringUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="用户申请表",description = "用户申请表")
@Table(name = "user_apply")
public class UserApply implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
    private Integer id;

    /**
     * 申请人id
     */
	@ApiModelProperty(value="申请人id",name="userid",example = "286",required = true)
    @Column(name = "userId")
    private Integer userid;

    /**
     * 申请类型：1 VIP申请、2入群申请，3管理员申请、4创建社群申请、5征稿申请、6、社刊申请、7推荐发文申请
     */
	@ApiModelProperty(value="申请类型：1 VIP申请、2入群申请，3管理员申请、4创建社群申请、5征稿申请、6、社刊申请、7推荐发文申请",name="applyType",example = "4",required = true)
    @Column(name = "apply_type")
    private String applyType;

    /**
     * 申请内容说明
     */
	@ApiModelProperty(value="申请内容说明",name="applyDesc",example = "测试说明",required = false)
    @Column(name = "apply_desc")
    private String applyDesc;

    /**
     * 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
	@ApiModelProperty(value="审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)",name="applySts",example = "0",required = true)
    @Column(name = "apply_sts")
    private String applySts;

    /**
     * 申请人手机号码
     */
	@ApiModelProperty(value="申请人手机号码",name="phone",example = "13818273645",required = true)
	   
    private String phone;

    /**
     * 申请人qq号码
     */
	@ApiModelProperty(value="申请人qq号码",name="qq",example = "1234567891",required = false)
    private String qq;

    /**
     * 申请人微信号码
     */
	@ApiModelProperty(value="申请人微信号码",name="wechatNum",example = "1234567891",required = false)
    @Column(name = "wechat_num")
    private String wechatNum;
	/**
     * 申请业务id
     */
	@ApiModelProperty(value="申请业务id：比如VIP申请 填写用户表id，入群申请填写社群表id",name="applyTargetId",example = "0",required = false)
	 @Column(name = "apply_target_id")
	 private Integer applyTargetId;
	
	//验证码
	@ApiModelProperty(value="验证码",name="code",example = "123456",required = false)
	@Transient
	private String code;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

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
     * 创建时间
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * 更新时间
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "updated_date")
    private Date updatedDate;

    /**
     * 创建人
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 更新人
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 删除标志
     */
	@ApiModelProperty(hidden = true)
    @Column(name = "is_deleted")
    private Boolean isDeleted;
	
	
	@ApiModelProperty(value="申请人姓名",name="userName",example = "张三",required = false)
    @Column(name = "user_name")
    private String userName;
	
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

	public Integer getApplyTargetId() {
		return applyTargetId;
	}

	public void setApplyTargetId(Integer applyTargetId) {
		this.applyTargetId = applyTargetId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}