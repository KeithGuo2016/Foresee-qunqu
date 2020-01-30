package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Table(name = "user_power")
public class UserPower implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Integer id;

	/**
     * 用户id
     */
    
    @Column(name = "userid")
    private Integer userid;
    /**
     * 更新时间
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "updated_date")
    private Date updatedDate;

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
    
    /**
     * 是否是VIP(0表示否，1表示是)
     */
    @ApiModelProperty(value="是否是VIP(0表示否，1表示是)",name="isVip",example = "0",required = false)
    @Column(name = "is_vip")
    private Integer isVip;

    /**
     * 是否是管理员(0表示否，1表示是)
     */
    @ApiModelProperty(value="是否是管理员(0表示否，1表示是)",name="isAdmin",example = "0",required = false)
    @Column(name = "is_admin")
    private Integer isAdmin;

    /**
     * 是否是社长(0表示否，1表示是)
     */
    @ApiModelProperty(value="是否是社长(0表示否，1表示是)",name="isCommunity",example = "0",required = false)
    @Column(name = "is_community")
    private Integer isCommunity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getIsCommunity() {
		return isCommunity;
	}

	public void setIsCommunity(Integer isCommunity) {
		this.isCommunity = isCommunity;
	}
    
    
}
