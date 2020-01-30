package com.foresee.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foresee.pojo.Articles;
import com.github.pagehelper.PageInfo;


public class WechatUserVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
	
	@Id
    private Integer id;

    /**
     * user_id
     */
    private Integer uid;

    /**
     * appId
     */
    
    
    private String appId;

    /**
     * openId
     */
   
    
    private String openId;

    /**
     * unionId
     */
   
    private String unionId;

    /**
     * 微信昵称
     */
   
    private String nickName;

    /**
     * 微信头像
     */
    
    private String headUrl;

    /**
     * 城市
     */
    
    private String city;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date updatedDate;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date createdDate;

    /**
     * 创建人
     */
    
    private String createdBy;

    /**
     * 更新人
     */
    
    private String updatedBy;

    /**
     * 删除标志
     */
   
    private Boolean isDeleted;

    /**
     * 所在社群
     */
    
    private Integer communityid;

    /**
     * 手机号码
     */
   
    private String phone;

    /**
     * 用户标签
     */
   
    private String userMark;

    /**
     * 是否是VIP(0表示否，1表示是)
     */
   
    private Integer isVip;
    private String userDesc;

    /**
     * 是否是管理员(0表示否，1表示是)
     */
    
    private Integer isAdmin;

    /**
     * 是否是社长(0表示否，1表示是)
     */
    
    private Integer isCommunity;

    /**
     * 用户身份
     */
   
    private String userType;
    /**
     * 社群审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
    
    private String flowSts;
    
    //社群名称
    private String communityName;
    //社群头像
    private String communityIcon;
    
    private PageInfo<ArticleVo> list;
   //创建社群申请
    private Integer isAddCommunityAplly;
    
	private String userRedisSession;//redis 缓存session

	 
	 private String communityCodeIcon;
	 private String pinyin;
	 private Integer pcount;
	 private String bgIcon;

	 private String inviteCode;
    private int inviteCount;//

	 
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

    public String getBgIcon() {
		return bgIcon;
	}

	public void setBgIcon(String bgIcon) {
		this.bgIcon = bgIcon;
	}

	public String getCommunityCodeIcon() {
		return communityCodeIcon;
	}

	public void setCommunityCodeIcon(String communityCodeIcon) {
		this.communityCodeIcon = communityCodeIcon;
	}

	public Integer getIsAddCommunityAplly() {
		return isAddCommunityAplly;
	}

	public void setIsAddCommunityAplly(Integer isAddCommunityAplly) {
		this.isAddCommunityAplly = isAddCommunityAplly;
	}

	public PageInfo<ArticleVo> getList() {
		return list;
	}

	public void setList(PageInfo<ArticleVo> list) {
		this.list = list;
	}

	/**
     * 获取user_id
     *
     * @return uid - user_id
     */
    public Integer getUid() {
        return uid;
    }

    public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	/**
     * 设置user_id
     *
     * @param uid user_id
     */
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    /**
     * 获取appId
     *
     * @return app_id - appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * 设置appId
     *
     * @param appId appId
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * 获取openId
     *
     * @return open_id - openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置openId
     *
     * @param openId openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 获取unionId
     *
     * @return union_id - unionId
     */
    public String getUnionId() {
        return unionId;
    }

    /**
     * 设置unionId
     *
     * @param unionId unionId
     */
    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    /**
     * 获取微信昵称
     *
     * @return nick_name - 微信昵称
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * 设置微信昵称
     *
     * @param nickName 微信昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * 获取微信头像
     *
     * @return head_url - 微信头像
     */
    public String getHeadUrl() {
        return headUrl;
    }

    /**
     * 设置微信头像
     *
     * @param headUrl 微信头像
     */
    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    /**
     * 获取城市
     *
     * @return city - 城市
     */
    public String getCity() {
        return city;
    }

    /**
     * 设置城市
     *
     * @param city 城市
     */
    public void setCity(String city) {
        this.city = city;
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
     * 获取所在社群
     *
     * @return communityId - 所在社群
     */
    public Integer getCommunityid() {
        return communityid;
    }

    /**
     * 设置所在社群
     *
     * @param communityid 所在社群
     */
    public void setCommunityid(Integer communityid) {
        this.communityid = communityid;
    }

    /**
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取用户标签
     *
     * @return user_mark - 用户标签
     */
    public String getUserMark() {
        return userMark;
    }

    /**
     * 设置用户标签
     *
     * @param userMark 用户标签
     */
    public void setUserMark(String userMark) {
        this.userMark = userMark;
    }

    /**
     * 获取是否是VIP(0表示否，1表示是)
     *
     * @return is_vip - 是否是VIP(0表示否，1表示是)
     */
    public Integer getIsVip() {
        return isVip;
    }

    /**
     * 设置是否是VIP(0表示否，1表示是)
     *
     * @param isVip 是否是VIP(0表示否，1表示是)
     */
    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    /**
     * 获取是否是管理员(0表示否，1表示是)
     *
     * @return is_admin - 是否是管理员(0表示否，1表示是)
     */
    public Integer getIsAdmin() {
        return isAdmin;
    }

    /**
     * 设置是否是管理员(0表示否，1表示是)
     *
     * @param isAdmin 是否是管理员(0表示否，1表示是)
     */
    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * 获取是否是社长(0表示否，1表示是)
     *
     * @return is_community - 是否是社长(0表示否，1表示是)
     */
    public Integer getIsCommunity() {
        return isCommunity;
    }

    /**
     * 设置是否是社长(0表示否，1表示是)
     *
     * @param isCommunity 是否是社长(0表示否，1表示是)
     */
    public void setIsCommunity(Integer isCommunity) {
        this.isCommunity = isCommunity;
    }

    /**
     * 获取用户身份
     *
     * @return user_type - 用户身份
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 设置用户身份
     *
     * @param userType 用户身份
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

	public String getFlowSts() {
		return flowSts;
	}

	public void setFlowSts(String flowSts) {
		this.flowSts = flowSts;
	}

	public String getUserRedisSession() {
		return userRedisSession;
	}

	public void setUserRedisSession(String userRedisSession) {
		this.userRedisSession = userRedisSession;
	}

	public String getCommunityName() {
		return communityName;
	}

	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}

	public String getCommunityIcon() {
		return communityIcon;
	}

	public void setCommunityIcon(String communityIcon) {
		this.communityIcon = communityIcon;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public Integer getPcount() {
		return pcount;
	}

	public void setPcount(Integer pcount) {
		this.pcount = pcount;
	}

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }

    public int getInviteCount() { return inviteCount; }
    public void setInviteCount(int inviteCount) { this.inviteCount = inviteCount; }
}