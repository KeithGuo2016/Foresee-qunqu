package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;

public class Tag implements Serializable{
    /**
     * 主键
     */
	@ApiModelProperty(hidden = true)
	@Id
    private Integer id;

    /**
     * 标签类型，0社群，1文章 2用户 3社刊 4身份 5征稿
     */
    @Column(name = "tag_type")
    private String tagType;

    /**
     * 标签名称
     */
    @Column(name = "tag_name")
    private String tagName;

    /**
     * 标签说明
     */
    @Column(name = "tag_desc")
    private String tagDesc;

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
     * 修改人
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 删除标志(0:未删除,1:删除)
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标签类型，0社群，1文章 2用户 3社刊 4身份 5征稿
     *
     * @return tag_type - 标签类型，0社群，1文章 2用户 3社刊 4身份 5征稿
     */
    public String getTagType() {
        return tagType;
    }

    /**
     * 设置标签类型，0社群，1文章 2用户 3社刊 4身份 5征稿
     *
     * @param tagType 标签类型，0社群，1文章 2用户 3社刊 4身份 5征稿
     */
    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    /**
     * 获取标签名称
     *
     * @return tag_name - 标签名称
     */
    public String getTagName() {
        return tagName;
    }

    /**
     * 设置标签名称
     *
     * @param tagName 标签名称
     */
    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    /**
     * 获取标签说明
     *
     * @return tag_desc - 标签说明
     */
    public String getTagDesc() {
        return tagDesc;
    }

    /**
     * 设置标签说明
     *
     * @param tagDesc 标签说明
     */
    public void setTagDesc(String tagDesc) {
        this.tagDesc = tagDesc;
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
     * 获取修改人
     *
     * @return updated_by - 修改人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置修改人
     *
     * @param updatedBy 修改人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取删除标志(0:未删除,1:删除)
     *
     * @return is_deleted - 删除标志(0:未删除,1:删除)
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置删除标志(0:未删除,1:删除)
     *
     * @param isDeleted 删除标志(0:未删除,1:删除)
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
}