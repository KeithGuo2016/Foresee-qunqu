package com.foresee.model;

import java.util.Date;

public class WechatUser extends PageInfo{
    private Integer id;

    private Integer uid;

    private String appId;

    private String openId;

    private String unionId;

    private String nickName;

    private String headUrl;

    private String city;

    private Date updatedDate;

    private Date createdDate;

    private String createdBy;

    private String updatedBy;

    private Integer isDeleted;

    private Integer communityid;

    private String phone;

    private String userMark;

    private String isVip;

    private String isAdmin;

    private String isCommunity;

    private String userType;

    private String flowSts;
    
    private String createdDateStar;
    private String updatedDateStar;
    
    private String createdDateEnd;
    private String updatedDateEnd;
    
    private String tagMarks;
    private String tagTypes;
    private String communityName;
    
    private Integer communitysId;
    
    private String createdName;

    private String updatedName;
    
    private Integer isAddCommunityAplly;
    
    
    public Integer getIsAddCommunityAplly() {
		return isAddCommunityAplly;
	}

	public void setIsAddCommunityAplly(Integer isAddCommunityAplly) {
		this.isAddCommunityAplly = isAddCommunityAplly;
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
    public Integer getCommunitysId() {
		return communitysId;
	}

	public void setCommunitysId(Integer communitysId) {
		this.communitysId = communitysId;
	}

	private Integer isCommunityid;
    
    public String getCreatedDateEnd() {
		return createdDateEnd;
	}

	public void setCreatedDateEnd(String createdDateEnd) {
		this.createdDateEnd = createdDateEnd;
	}

	public String getUpdatedDateEnd() {
		return updatedDateEnd;
	}

	public void setUpdatedDateEnd(String updatedDateEnd) {
		this.updatedDateEnd = updatedDateEnd;
	}

	public Integer getIsCommunityid() {
		return isCommunityid;
	}

	public void setIsCommunityid(Integer isCommunityid) {
		this.isCommunityid = isCommunityid;
	}

	public String getTagMarks() {
		return tagMarks;
	}

	public void setTagMarks(String tagMarks) {
		this.tagMarks = tagMarks;
	}

	public String getTagTypes() {
		return tagTypes;
	}

	public void setTagTypes(String tagTypes) {
		this.tagTypes = tagTypes;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getCreatedDateStar() {
		return createdDateStar;
	}

	public void setCreatedDateStar(String createdDateStar) {
		this.createdDateStar = createdDateStar;
	}

	public String getUpdatedDateStar() {
		return updatedDateStar;
	}

	public void setUpdatedDateStar(String updatedDateStar) {
		this.updatedDateStar = updatedDateStar;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId == null ? null : appId.trim();
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId == null ? null : openId.trim();
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId == null ? null : unionId.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl == null ? null : headUrl.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public Integer getCommunityid() {
        return communityid;
    }

    public void setCommunityid(Integer communityid) {
        this.communityid = communityid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUserMark() {
        return userMark;
    }

    public void setUserMark(String userMark) {
        this.userMark = userMark == null ? null : userMark.trim();
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip == null ? null : isVip.trim();
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin == null ? null : isAdmin.trim();
    }

    public String getIsCommunity() {
        return isCommunity;
    }

    public void setIsCommunity(String isCommunity) {
        this.isCommunity = isCommunity == null ? null : isCommunity.trim();
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType == null ? null : userType.trim();
    }

    public String getFlowSts() {
        return flowSts;
    }

    public void setFlowSts(String flowSts) {
        this.flowSts = flowSts == null ? null : flowSts.trim();
    }
}