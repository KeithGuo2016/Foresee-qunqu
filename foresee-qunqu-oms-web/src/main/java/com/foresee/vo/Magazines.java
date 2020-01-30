package com.foresee.vo;

import java.util.Date;

import com.foresee.utils.DateUtils;

public class Magazines{
    private Integer id;

    private Integer createUserId;

    private Integer communitysId;

    private String magazineTitle;

    private String magazineIcon;

    private String magazineDesc;

    private Integer readCount;

    private Integer followCount;

    private String magazineTag;

    private String createdDate;

    private String updatedDate;

    private String createdBy;

    private String updatedBy;

    private Integer isDeleted;

    private String flowSts;

    private String startDate;
    
    private String tagNames;
    
    private String communityName;
    
    private String articlesIds;
    
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
    
    public String getArticlesIds() {
		return articlesIds;
	}

	public void setArticlesIds(String articlesIds) {
		this.articlesIds = articlesIds;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
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

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getCommunitysId() {
        return communitysId;
    }

    public void setCommunitysId(Integer communitysId) {
        this.communitysId = communitysId;
    }

    public String getMagazineTitle() {
        return magazineTitle;
    }

    public void setMagazineTitle(String magazineTitle) {
        this.magazineTitle = magazineTitle == null ? null : magazineTitle.trim();
    }

    public String getMagazineIcon() {
        return magazineIcon;
    }

    public void setMagazineIcon(String magazineIcon) {
        this.magazineIcon = magazineIcon == null ? null : magazineIcon.trim();
    }

    public String getMagazineDesc() {
        return magazineDesc;
    }

    public void setMagazineDesc(String magazineDesc) {
        this.magazineDesc = magazineDesc == null ? null : magazineDesc.trim();
    }

    public Integer getReadCount() {
        return readCount;
    }

    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    public Integer getFollowCount() {
        return followCount;
    }

    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    public String getMagazineTag() {
        return magazineTag;
    }

    public void setMagazineTag(String magazineTag) {
        this.magazineTag = magazineTag == null ? null : magazineTag.trim();
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = DateUtils.getDateToString(startDate, "yyyy-MM-dd HH:mm:ss");
    }
}