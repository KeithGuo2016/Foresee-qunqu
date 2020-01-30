package com.foresee.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foresee.pojo.WechatUser;
import com.foresee.utils.StringUtil;
/**
 * 社群视图
 * @author DELL
 *
 */
public class CommunityVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
    private Integer id;

    
    
   
    private String communityName;

    /**
     * 社群图标
     */
    
    private String communityIcon;

    /**
     * 社群签名
     */
   
    private String communityMark;

    /**
     * 社群标签
     */
   
    private String communityTag;

    /**
     * 社群背景图
     */
   
    private String bgIcon;

    /**
     * 社群认证信息说明
     */
   
    private String authDesc;

    /**
     * 社群公告
     */
   
    private String communityNotice;

    /**
     * 社群说明
     */
   
    private String communityDesc;

    /**
     * 社群类别
     */
    
    private Integer communityType;

    /**
     * 社长ID
     */
    
    private Integer adminId;

    /**
     * 社群状态(1.正常、0非正常)
     */
    
    private String communitySts;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy年MM月dd日",locale = "zh",timezone = "GMT+8")
    private Date createdDate;

    /**
     * 更新时间
     */
   
    private Date updatedDate;

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
    
  //社群二维码
    @Column(name = "community_code_icon")
    private String communityCodeIcon;
    
  
    private String flowSts;
    //社群成员总数
    private Integer memberCount;
    //社群作品数
    private Integer  workCount;
    //社群总阅读量
    private Integer readCount;
    
    private Integer isjion;
    //用户是否已经关注(0:未关注，1已关注)
    private Integer CommunitysFollow;
    //用户关注社群表id
    private Integer CommunitysFollowId;
    //社群成员
    private List<WechatUser> userList;
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

    public String getCommunityCodeIcon() {
		return communityCodeIcon;
	}

	public void setCommunityCodeIcon(String communityCodeIcon) {
		this.communityCodeIcon = communityCodeIcon;
	}

	public List<WechatUser> getUserList() {
		return userList;
	}

	public void setUserList(List<WechatUser> userList) {
		this.userList = userList;
	}

	public Integer getIsjion() {
		return isjion;
	}

	public void setIsjion(Integer isjion) {
		this.isjion = isjion;
	}

	/**
     * 获取社群名称
     *
     * @return community_name - 社群名称
     */
    public String getCommunityName() {
        return communityName;
    }

    /**
     * 设置社群名称
     *
     * @param communityName 社群名称
     */
    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    /**
     * 获取社群图标
     *
     * @return community_icon - 社群图标
     */
    public String getCommunityIcon() {
        return communityIcon;
    }

    /**
     * 设置社群图标
     *
     * @param communityIcon 社群图标
     */
    public void setCommunityIcon(String communityIcon) {
        this.communityIcon = communityIcon;
    }

    /**
     * 获取社群签名
     *
     * @return community_mark - 社群签名
     */
    public String getCommunityMark() {
        return communityMark;
    }

    /**
     * 设置社群签名
     *
     * @param communityMark 社群签名
     */
    public void setCommunityMark(String communityMark) {
        this.communityMark = communityMark;
    }

    /**
     * 获取社群标签
     *
     * @return community_tag - 社群标签
     */
    public String getCommunityTag() {
        return communityTag;
    }

    /**
     * 设置社群标签
     *
     * @param communityTag 社群标签
     */
    public void setCommunityTag(String communityTag) {
        this.communityTag = communityTag;
    }

    /**
     * 获取社群背景图
     *
     * @return bg_icon - 社群背景图
     */
    public String getBgIcon() {
        return bgIcon;
    }

    /**
     * 设置社群背景图
     *
     * @param bgIcon 社群背景图
     */
    public void setBgIcon(String bgIcon) {
        this.bgIcon = bgIcon;
    }

    /**
     * 获取社群认证信息说明
     *
     * @return auth_desc - 社群认证信息说明
     */
    public String getAuthDesc() {
        return authDesc;
    }

    /**
     * 设置社群认证信息说明
     *
     * @param authDesc 社群认证信息说明
     */
    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc;
    }

    /**
     * 获取社群公告
     *
     * @return community_notice - 社群公告
     */
    public String getCommunityNotice() {
        return communityNotice;
    }

    /**
     * 设置社群公告
     *
     * @param communityNotice 社群公告
     */
    public void setCommunityNotice(String communityNotice) {
        this.communityNotice = communityNotice;
    }

    /**
     * 获取社群说明
     *
     * @return community_desc - 社群说明
     */
    public String getCommunityDesc() {
        return communityDesc;
    }

    /**
     * 设置社群说明
     *
     * @param communityDesc 社群说明
     */
    public void setCommunityDesc(String communityDesc) {
        this.communityDesc = communityDesc;
    }

    /**
     * 获取社群类别
     *
     * @return community_type - 社群类别
     */
    public Integer getCommunityType() {
        return communityType;
    }

    /**
     * 设置社群类别
     *
     * @param communityType 社群类别
     */
    public void setCommunityType(Integer communityType) {
        this.communityType = communityType;
    }

    /**
     * 获取社长ID
     *
     * @return admin_id - 社长ID
     */
    public Integer getAdminId() {
        return adminId;
    }

    /**
     * 设置社长ID
     *
     * @param adminId 社长ID
     */
    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    /**
     * 获取社群状态(1.正常、0非正常)
     *
     * @return community_sts - 社群状态(1.正常、0非正常)
     */
    public String getCommunitySts() {
        return communitySts;
    }

    /**
     * 设置社群状态(1.正常、0非正常)
     *
     * @param communitySts 社群状态(1.正常、0非正常)
     */
    public void setCommunitySts(String communitySts) {
        this.communitySts = communitySts;
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

	public String getFlowSts() {
		return flowSts;
	}

	public void setFlowSts(String flowSts) {
		this.flowSts = flowSts;
	}

	public Integer getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(Integer memberCount) {
		this.memberCount = memberCount;
	}

	public Integer getWorkCount() {
		return workCount;
	}

	public void setWorkCount(Integer workCount) {
		this.workCount = workCount;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Integer getCommunitysFollow() {
		return CommunitysFollow;
	}

	public void setCommunitysFollow(Integer communitysFollow) {
		CommunitysFollow = communitysFollow;
	}

	public Integer getCommunitysFollowId() {
		return CommunitysFollowId;
	}

	public void setCommunitysFollowId(Integer communitysFollowId) {
		CommunitysFollowId = communitysFollowId;
	}
    
    
}