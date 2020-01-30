package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foresee.utils.StringUtil;

public class Magazines implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Integer id;

    /**
     * 作者ID
     */
    @Column(name = "create_user_id")
    private Integer createUserId;

    /**
     * 所属社群id
     */
    @Column(name = "communitys_id")
    private Integer communitysId;

    /**
     * 社刊标题
     */
    @Column(name = "magazine_title")
    private String magazineTitle;

    /**
     * 封面图
     */
    @Column(name = "magazine_icon")
    private String magazineIcon;

    /**
     * 社刊简介
     */
    @Column(name = "magazine_desc")
    private String magazineDesc;

    /**
     * 社刊阅读量
     */
    @Column(name = "read_count")
    private Integer readCount;

    /**
     * 社刊收藏量
     */
    @Column(name = "follow_count")
    private Integer followCount;

    /**
     * 社刊标签
     */
    @Column(name = "magazine_tag")
    private String magazineTag;

    /**
     * 创建时间
     */
    @Column(name = "created_date")
    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
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
     * 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
    @Column(name = "flow_sts")
    private String flowSts;

    /**
     * 计划发布时间
     */
    @Column(name = "start_date")
    private Date startDate;
    
    @Transient
    private List<MagazineArticles> list;

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
     * @return create_user_id - 作者ID
     */
    public Integer getCreateUserId() {
        return createUserId;
    }

    /**
     * 设置作者ID
     *
     * @param createUserId 作者ID
     */
    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * 获取所属社群id
     *
     * @return communitys_id - 所属社群id
     */
    public Integer getCommunitysId() {
        return communitysId;
    }

    /**
     * 设置所属社群id
     *
     * @param communitysId 所属社群id
     */
    public void setCommunitysId(Integer communitysId) {
        this.communitysId = communitysId;
    }

    /**
     * 获取社刊标题
     *
     * @return magazine_title - 社刊标题
     */
    public String getMagazineTitle() {
        return magazineTitle;
    }

    /**
     * 设置社刊标题
     *
     * @param magazineTitle 社刊标题
     */
    public void setMagazineTitle(String magazineTitle) {
        this.magazineTitle = magazineTitle;
    }

    /**
     * 获取封面图
     *
     * @return magazine_icon - 封面图
     */
    public String getMagazineIcon() {
        return magazineIcon;
    }

    /**
     * 设置封面图
     *
     * @param magazineIcon 封面图
     */
    public void setMagazineIcon(String magazineIcon) {
        this.magazineIcon = magazineIcon;
    }

    /**
     * 获取社刊简介
     *
     * @return magazine_desc - 社刊简介
     */
    public String getMagazineDesc() {
        return magazineDesc;
    }

    /**
     * 设置社刊简介
     *
     * @param magazineDesc 社刊简介
     */
    public void setMagazineDesc(String magazineDesc) {
        this.magazineDesc = magazineDesc;
    }

    /**
     * 获取社刊阅读量
     *
     * @return read_count - 社刊阅读量
     */
    public Integer getReadCount() {
        return readCount;
    }

    /**
     * 设置社刊阅读量
     *
     * @param readCount 社刊阅读量
     */
    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    /**
     * 获取社刊收藏量
     *
     * @return follow_count - 社刊收藏量
     */
    public Integer getFollowCount() {
        return followCount;
    }

    /**
     * 设置社刊收藏量
     *
     * @param followCount 社刊收藏量
     */
    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    /**
     * 获取社刊标签
     *
     * @return magazine_tag - 社刊标签
     */
    public String getMagazineTag() {
        return magazineTag;
    }

    /**
     * 设置社刊标签
     *
     * @param magazineTag 社刊标签
     */
    public void setMagazineTag(String magazineTag) {
        this.magazineTag = magazineTag;
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

    /**
     * 获取审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     *
     * @return flow_sts - 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
    public String getFlowSts() {
        return flowSts;
    }

    /**
     * 设置审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     *
     * @param flowSts 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
    public void setFlowSts(String flowSts) {
        this.flowSts = flowSts;
    }

    /**
     * 获取计划发布时间
     *
     * @return start_date - 计划发布时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置计划发布时间
     *
     * @param startDate 计划发布时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

	public List<MagazineArticles> getList() {
		return list;
	}

	public void setList(List<MagazineArticles> list) {
		this.list = list;
	}
    
    
}