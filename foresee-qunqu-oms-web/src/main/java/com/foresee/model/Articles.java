package com.foresee.model;

import java.util.Date;

public class Articles extends PageInfo{
    private Integer id;
    
    private String ids;

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

    private Date flowDate;

    private String flowSts;

    private Date createdDate;

    private Date updatedDate;

    private String createdBy;
    
    private String createdName;

    private String updatedBy;
    
    private String updatedName;

    private Integer isDeleted;

    private Integer gatherId;

    private Date recommendDate;

    private String articleContent;
    
    private String recommendDateStr;
    
    private String recommendDateStar;
    private String recommendDateEnd;
    private String updatedDateStar;
    private String  updatedDateEnd;
    private String createdDateStar;
    private String createdDateEnd;
    private String flowDateStar;
    private String flowDateEnd;
    private String tagNames;
    private String communityName;
    
    private Integer arNotInMa;
    
    private String articleIconBg;
    
    public Integer getArNotInMa() {
		return arNotInMa;
	}

	public void setArNotInMa(Integer arNotInMa) {
		this.arNotInMa = arNotInMa;
	}

	public String getUpdatedName() {
		return updatedName;
	}

	public void setUpdatedName(String updatedName) {
		this.updatedName = updatedName;
	}

	public String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
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

	private String magazineTitle;
    
    public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getMagazineTitle() {
		return magazineTitle;
	}

	public void setMagazineTitle(String magazineTitle) {
		this.magazineTitle = magazineTitle;
	}

	public String getRecommendDateStar() {
		return recommendDateStar;
	}

	public void setRecommendDateStar(String recommendDateStar) {
		this.recommendDateStar = recommendDateStar;
	}

	public String getRecommendDateEnd() {
		return recommendDateEnd;
	}

	public void setRecommendDateEnd(String recommendDateEnd) {
		this.recommendDateEnd = recommendDateEnd;
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

	public String getFlowDateStar() {
		return flowDateStar;
	}

	public void setFlowDateStar(String flowDateStar) {
		this.flowDateStar = flowDateStar;
	}

	public String getFlowDateEnd() {
		return flowDateEnd;
	}

	public void setFlowDateEnd(String flowDateEnd) {
		this.flowDateEnd = flowDateEnd;
	}

	public String getRecommendDateStr() {
		return recommendDateStr;
	}

	public void setRecommendDateStr(String recommendDateStr) {
		this.recommendDateStr = recommendDateStr;
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

    public Date getFlowDate() {
        return flowDate;
    }

    public void setFlowDate(Date flowDate) {
        this.flowDate = flowDate;
    }

    public String getFlowSts() {
        return flowSts;
    }

    public void setFlowSts(String flowSts) {
        this.flowSts = flowSts == null ? null : flowSts.trim();
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

    public Integer getGatherId() {
        return gatherId;
    }

    public void setGatherId(Integer gatherId) {
        this.gatherId = gatherId;
    }

    public Date getRecommendDate() {
        return recommendDate;
    }

    public void setRecommendDate(Date recommendDate) {
        this.recommendDate = recommendDate;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent == null ? null : articleContent.trim();
    }

	public String getArticleIconBg() {
		return articleIconBg;
	}

	public void setArticleIconBg(String articleIconBg) {
		this.articleIconBg = articleIconBg;
	}
    
}