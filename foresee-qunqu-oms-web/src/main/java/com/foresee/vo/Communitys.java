package com.foresee.vo;

import java.util.Date;

import com.foresee.utils.DateUtils;

public class Communitys{
    private Integer id;

    private String communityName;

    private String communityIcon;

    private String communityMark;

    private String communityTag;

    private String bgIcon;

    private String authDesc;

    private String communityNotice;

    private String communityDesc;

    private Integer communityType;

    private Integer adminId;

    private String communitySts;

    private String createdDate;

    private String updatedDate;

    private String createdBy;

    private String updatedBy;

    private Integer isDeleted;

    private String flowSts;
    
    private String  tagNames;
    
    private String createdName;

    private String updatedName;
    
    
    
    public String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}

	public String getUpdatedName() {
		return updatedName;
	}

	public void setUpdatedName(String updatedName) {
		this.updatedName = updatedName;
	}
    
    public String getTagNames() {
		return tagNames;
	}

	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName == null ? null : communityName.trim();
    }

    public String getCommunityIcon() {
        return communityIcon;
    }

    public void setCommunityIcon(String communityIcon) {
        this.communityIcon = communityIcon == null ? null : communityIcon.trim();
    }

    public String getCommunityMark() {
        return communityMark;
    }

    public void setCommunityMark(String communityMark) {
        this.communityMark = communityMark == null ? null : communityMark.trim();
    }

    public String getCommunityTag() {
        return communityTag;
    }

    public void setCommunityTag(String communityTag) {
        this.communityTag = communityTag == null ? null : communityTag.trim();
    }

    public String getBgIcon() {
        return bgIcon;
    }

    public void setBgIcon(String bgIcon) {
        this.bgIcon = bgIcon == null ? null : bgIcon.trim();
    }

    public String getAuthDesc() {
        return authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc == null ? null : authDesc.trim();
    }

    public String getCommunityNotice() {
        return communityNotice;
    }

    public void setCommunityNotice(String communityNotice) {
        this.communityNotice = communityNotice == null ? null : communityNotice.trim();
    }

    public String getCommunityDesc() {
        return communityDesc;
    }

    public void setCommunityDesc(String communityDesc) {
        this.communityDesc = communityDesc == null ? null : communityDesc.trim();
    }

    public Integer getCommunityType() {
        return communityType;
    }

    public void setCommunityType(Integer communityType) {
        this.communityType = communityType;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getCommunitySts() {
        return communitySts;
    }

    public void setCommunitySts(String communitySts) {
        this.communitySts = communitySts == null ? null : communitySts.trim();
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = DateUtils.getDateToString(createdDate, "yyyy-MM-dd HH:mm:ss");
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = DateUtils.getDateToString(updatedDate, "yyyy-MM-dd HH:mm:ss");
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getFlowSts() {
        return flowSts;
    }

    public void setFlowSts(String flowSts) {
        this.flowSts = flowSts == null ? null : flowSts.trim();
    }
}