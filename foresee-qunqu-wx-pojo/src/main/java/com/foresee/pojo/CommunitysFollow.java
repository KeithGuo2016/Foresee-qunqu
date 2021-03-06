package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 用户关注社群表
 * @author DELL
 *
 */
@ApiModel(value="用户关注社群表",description = "用户关注社群表")
@Table(name = "communitys_follow")
public class CommunitysFollow implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(hidden = true)
	@Id
    private Integer id;

    /**
     * 用户ID
     */
	@ApiModelProperty(value="关注用户ID",name="userid",example = "286",required = true)
    @Column(name = "userId")
    private Integer userid;

    /**
     * 社群ID
     */
	@ApiModelProperty(value="关注社群ID",name="communitysId",example = "281",required = true)
    @Column(name = "communitys_id")
    private Integer communitysId;

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
     * 获取作者ID
     *
     * @return userId - 作者ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置作者ID
     *
     * @param userid 作者ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取社群ID
     *
     * @return communitys_id - 社群ID
     */
    public Integer getCommunitysId() {
        return communitysId;
    }

    /**
     * 设置社群ID
     *
     * @param communitysId 社群ID
     */
    public void setCommunitysId(Integer communitysId) {
        this.communitysId = communitysId;
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