package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import io.swagger.annotations.ApiModelProperty;

@Table(name = "wechat_user")
public class WechatUser implements Serializable{
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
    @ApiModelProperty(value="appId",name="appId",example = "0",required = true)
    @Column(name = "app_id")
    private String appId;

    /**
     * openId
     */
    @ApiModelProperty(value="openId：后台获取",name="openId",example = "wx1983t4",required = true)
    @Column(name = "open_id")
    private String openId;

    /**
     * unionId
     */
    @ApiModelProperty(value="unionId：后台获取",name="unionId",example = "wx1983t4",required = true)
    @Column(name = "union_id")
    private String unionId;

    /**
     * 微信昵称
     */
    @ApiModelProperty(value="微信昵称",name="nickName",example = "张三",required = true)
    @Column(name = "nick_name")
    private String nickName;

    /**
     * 微信头像
     */
    @ApiModelProperty(value="微信头像",name="headUrl",example = "URL",required = true)
    @Column(name = "head_url")
    private String headUrl;

    /**
     * 城市
     */
    @ApiModelProperty(value="城市:微信后台获取",name="city",example = "南京",required = true)
    private String city;

    /**
     * 更新时间
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "updated_date")
    private Date updatedDate;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 更新人
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 删除标志
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 所在社群
     */
    @ApiModelProperty(value="所在社群id",name="communityid",example = "0",required = false)
    @Column(name = "communityId")
    private Integer communityid;

    /**
     * 手机号码
     */
    @ApiModelProperty(value="手机号码",name="phone",example = "13572834657",required = false)
    private String phone;

    /**
     * 用户标签
     */
    @ApiModelProperty(value="用户标签",name="userMark",example = "签约作者",required = false)
    @Column(name = "user_mark")
    private String userMark;

    /**
     * 是否是VIP(0表示否，1表示是)
     */
    @ApiModelProperty(value="是否是VIP(0表示否，1表示是)",name="isVip",example = "0",required = false)
    @Transient
    private Integer isVip;

    /**
     * 是否是管理员(0表示否，1表示是)
     */
    @ApiModelProperty(value="是否是管理员(0表示否，1表示是)",name="isAdmin",example = "0",required = false)
    @Transient
    private Integer isAdmin;

    /**
     * 是否是社长(0表示否，1表示是)
     */
    @ApiModelProperty(value="是否是社长(0表示否，1表示是)",name="isCommunity",example = "0",required = false)
    @Transient
    private Integer isCommunity;

    /**
     * 用户身份
     */
    @ApiModelProperty(value="用户身份：游客、普通客户、VIP、管理员、社长",name="userType",example = "0",required = false)
    @Column(name = "user_type")
    private String userType;
    /**
     * 社群审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
    @ApiModelProperty(value="社群审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)",name="flowSts",example = "0",required = false)
    @Column(name = "flow_sts")
    private String flowSts;
    @Column(name = "user_desc")
    private String userDesc;
    @Column(name = "bg_icon")
    private String bgIcon;
    
    @ApiModelProperty(hidden = true)
	@Transient
	private String userRedisSession;//redis 缓存session

    @ApiModelProperty(value="邀请码",name="code",example = "0",required = false)
    @Transient
	private String code;//
    public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

    @ApiModelProperty(value="邀请数量",name="inviteCount",example = "0",required = false)
    @Transient
    private int inviteCount;//
    public int getInviteCount() { return inviteCount; }
    public void setInviteCount(int inviteCount) { this.inviteCount = inviteCount; }

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

    public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	/**
     * 获取user_id
     *
     * @return uid - user_id
     */
    public Integer getUid() {
        return uid;
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

	public String getBgIcon() {
		return bgIcon;
	}

	public void setBgIcon(String bgIcon) {
		this.bgIcon = bgIcon;
	}
    
    
}