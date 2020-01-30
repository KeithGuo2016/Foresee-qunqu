package com.foresee.pojo;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

public class InviteHistory implements Serializable{
    /**
     * 主键
     */
	@ApiModelProperty(hidden = true)
	@Id
    private Integer id;

    /**
     * 邀请落地的类型，1 为入群邀请，2 为征稿邀请
     */
    @Column(name = "target_type")
    private int targetType;

    /**
     * 活动 id
     */
    @Column(name = "event_id")
    private Integer eventId;

    /**
     * 活动所产生的结果数据的id
     */
    @Column(name = "related_id")
    private Integer relatedId;


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
     * 获取活动目标
     *
     * @return target_type - 活动目标
     */
    public int getTargetType() {
        return targetType;
    }

    public void setTargetType(int targetType) {
        this.targetType = targetType;
    }

    /**
     * 获取活动ID
     *
     * @return event_id - 活动ID
     */
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    /**
     * 获取活动ID
     *
     * @return related_id - 活动所产生的结果数据的id
     */
    public Integer getRelatedId() { return relatedId; }

    public void setRelatedId(Integer relatedId) {
        this.relatedId = relatedId;
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