package com.foresee.model;

import java.util.Date;

public class Contributes extends PageInfo{
    private Integer id;

    private Integer createUserid;

    private Integer contributeUserid;

    private String contributeTitle;

    private String contributeIcon;

    private String orientation;

    private String contributeTag;

    private Integer buyNum;

    private String maxMoney;

    private String minMoney;

    private String trueMoney;

    private Date startDate;

    private Date endDate;
    
    private String startDateStr;

    private String endDateStr;

    private String contributeRange;

    private String userSeeRange;

    private String contributeType;

    private Date createdDate;

    private Date updatedDate;

    private String createdBy;

    private String updatedBy;

    private Integer isDeleted;

    private String flowSts;

    private Integer communityId;
    
    
    private String startDateStar;
    private String startDateEnd;
    private String endDateStar;
    private String endDateEnd;
    private String createdDateStar;
    private String createdDateEnd;
    private String updatedDateStar;
    private String updatedDateEnd;
    private String tagNames;
    private String communityName;
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

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getStartDateStar() {
		return startDateStar;
	}

	public void setStartDateStar(String startDateStar) {
		this.startDateStar = startDateStar;
	}

	public String getStartDateEnd() {
		return startDateEnd;
	}

	public void setStartDateEnd(String startDateEnd) {
		this.startDateEnd = startDateEnd;
	}

	public String getEndDateStar() {
		return endDateStar;
	}

	public void setEndDateStar(String endDateStar) {
		this.endDateStar = endDateStar;
	}

	public String getEndDateEnd() {
		return endDateEnd;
	}

	public void setEndDateEnd(String endDateEnd) {
		this.endDateEnd = endDateEnd;
	}

	public String getCreatedDateStar() {
		return createdDateStar;
	}

	public void setCreatedDateStar(String createdDateStar) {
		this.createdDateStar = createdDateStar;
	}

	public String getCreatedDateEnd() {
		return createdDateEnd;
	}

	public void setCreatedDateEnd(String createdDateEnd) {
		this.createdDateEnd = createdDateEnd;
	}

	public String getUpdatedDateStar() {
		return updatedDateStar;
	}

	public void setUpdatedDateStar(String updatedDateStar) {
		this.updatedDateStar = updatedDateStar;
	}

	public String getUpdatedDateEnd() {
		return updatedDateEnd;
	}

	public void setUpdatedDateEnd(String updatedDateEnd) {
		this.updatedDateEnd = updatedDateEnd;
	}

	public String getTagNames() {
		return tagNames;
	}

	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreateUserid() {
        return createUserid;
    }

    public void setCreateUserid(Integer createUserid) {
        this.createUserid = createUserid;
    }

    public Integer getContributeUserid() {
        return contributeUserid;
    }

    public void setContributeUserid(Integer contributeUserid) {
        this.contributeUserid = contributeUserid;
    }

    public String getContributeTitle() {
        return contributeTitle;
    }

    public void setContributeTitle(String contributeTitle) {
        this.contributeTitle = contributeTitle == null ? null : contributeTitle.trim();
    }

    public String getContributeIcon() {
        return contributeIcon;
    }

    public void setContributeIcon(String contributeIcon) {
        this.contributeIcon = contributeIcon == null ? null : contributeIcon.trim();
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation == null ? null : orientation.trim();
    }

    public String getContributeTag() {
        return contributeTag;
    }

    public void setContributeTag(String contributeTag) {
        this.contributeTag = contributeTag == null ? null : contributeTag.trim();
    }

    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public String getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(String maxMoney) {
        this.maxMoney = maxMoney == null ? null : maxMoney.trim();
    }

    public String getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(String minMoney) {
        this.minMoney = minMoney == null ? null : minMoney.trim();
    }

    public String getTrueMoney() {
        return trueMoney;
    }

    public void setTrueMoney(String trueMoney) {
        this.trueMoney = trueMoney == null ? null : trueMoney.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getContributeRange() {
        return contributeRange;
    }

    public void setContributeRange(String contributeRange) {
        this.contributeRange = contributeRange == null ? null : contributeRange.trim();
    }

    public String getUserSeeRange() {
        return userSeeRange;
    }

    public void setUserSeeRange(String userSeeRange) {
        this.userSeeRange = userSeeRange == null ? null : userSeeRange.trim();
    }

    public String getContributeType() {
        return contributeType;
    }

    public void setContributeType(String contributeType) {
        this.contributeType = contributeType == null ? null : contributeType.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
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

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }
}