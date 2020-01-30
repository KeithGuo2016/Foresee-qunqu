package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 用户关注社刊表
 * @author DELL
 *
 */
@ApiModel(value="用户关注社刊表",description = "用户关注社刊表")
@Table(name = "magazine_follow")
public class MagazineFollow implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden = true)
	@Id
    private Integer id;

    /**
     * 关注用户ID
     */
	@ApiModelProperty(value="关注用户ID",name="userid",example = "286",required = true)
    @Column(name = "userId")
    private Integer userid;

    /**
     * 社刊ID
     */
	@ApiModelProperty(value="社刊ID",name="magazineId",example = "286",required = true)
    @Column(name = "magazine_id")
    private Integer magazineId;

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

  

    public Integer getMagazineId() {
		return magazineId;
	}

	public void setMagazineId(Integer magazineId) {
		this.magazineId = magazineId;
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