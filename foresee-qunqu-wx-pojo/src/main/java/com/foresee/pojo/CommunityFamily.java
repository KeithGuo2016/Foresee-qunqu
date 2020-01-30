package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.foresee.utils.StringUtil;
import com.foresee.vo.CommunityVo;

@Table(name = "community_family")
public class CommunityFamily implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Integer id;

    /**
     * 类别名称
     */
    @Column(name = "family_name")
    private String familyName;

    /**
     * 备注说明
     */
    @Column(name = "family_desc")
    private String familyDesc;

    /**
     * 创建时间
     */
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * 更新时间
     */
    @Column(name = "updated_date")
    private Date updatedDate;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 更新人
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 删除标志
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    /**
     * 排序序列
     */
    @Column(name = "order_num")
    private Integer orderNum;

    @Transient
    private List<Communitys> list;
   
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
     * 获取类别名称
     *
     * @return family_name - 类别名称
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * 设置类别名称
     *
     * @param familyName 类别名称
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    /**
     * 获取备注说明
     *
     * @return family_desc - 备注说明
     */
    public String getFamilyDesc() {
        return familyDesc;
    }

    /**
     * 设置备注说明
     *
     * @param familyDesc 备注说明
     */
    public void setFamilyDesc(String familyDesc) {
        this.familyDesc = familyDesc;
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

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public List<Communitys> getList() {
		return list;
	}

	public void setList(List<Communitys> list) {
		this.list = list;
	}
    
    
}