package com.foresee.vo;

import java.util.Date;

import com.foresee.utils.DateUtils;

public class Articles {
    private Integer id;

    private String articleTitle;

    private Integer userid;

    private String articleIcon;

    private String articleOutline;

    private String isRecommend;

    private String articleTag;

    private Integer communityId;

    private String isDraft;

    private Integer readCount;

    private Integer followCount;

    private String flowDate;

    private String flowSts;

    private String createdDate;
    
    private String updatedDate;

    private String createdBy;
    
    private String createdName;

    private String updatedBy;
    
    private String updatedName;

    private Integer isDeleted;

    private Integer gatherId;

    private String recommendDate;

    private String articleContent;
    
    private String magazineTitle;
    
    private String tagNames;
    
    private String communityName;
    
    private String articleIconBg;
    
    public String getArticleIconBg() {
		return articleIconBg;
	}

	public void setArticleIconBg(String articleIconBg) {
		this.articleIconBg = articleIconBg;
	}

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
    
    public String getTagNames() {
		return tagNames;
	}

	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}
    
    public String getMagazineTitle() {
		return magazineTitle;
	}

	public void setMagazineTitle(String magazineTitle) {
		this.magazineTitle = magazineTitle;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getArticleIcon() {
        return articleIcon;
    }

    public void setArticleIcon(String articleIcon) {
        this.articleIcon = articleIcon == null ? null : articleIcon.trim();
    }

    public String getArticleOutline() {
        return articleOutline;
    }

    public void setArticleOutline(String articleOutline) {
        this.articleOutline = articleOutline == null ? null : articleOutline.trim();
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend == null ? null : isRecommend.trim();
    }

    public String getArticleTag() {
        return articleTag;
    }

    public void setArticleTag(String articleTag) {
        this.articleTag = articleTag == null ? null : articleTag.trim();
    }

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public String getIsDraft() {
        return isDraft;
    }

    public void setIsDraft(String isDraft) {
        this.isDraft = isDraft == null ? null : isDraft.trim();
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

    public String getFlowDate() {
        return flowDate;
    }

    public void setFlowDate(Date flowDate) {
        this.flowDate = DateUtils.getDateToString(flowDate, "yyyy-MM-dd HH:mm:ss");
    }

    public String getFlowSts() {
        return flowSts;
    }

    public void setFlowSts(String flowSts) {
        this.flowSts = flowSts == null ? null : flowSts.trim();
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

    public Integer getGatherId() {
        return gatherId;
    }

    public void setGatherId(Integer gatherId) {
        this.gatherId = gatherId;
    }

    public String getRecommendDate() {
        return recommendDate;
    }

    public void setRecommendDate(Date recommendDate) {
        this.recommendDate = DateUtils.getDateToString(recommendDate, "yyyy-MM-dd HH:mm:ss");
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }
}