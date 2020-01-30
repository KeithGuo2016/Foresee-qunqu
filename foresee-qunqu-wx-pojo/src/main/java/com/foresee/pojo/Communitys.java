package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foresee.utils.StringUtil;

import io.swagger.annotations.ApiModelProperty;
/**
 * 社群
 * @author DELL
 *
 */

public class Communitys implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(hidden = true)
	@Id
    private Integer id;

    /**
     * 社群名称
     */
	@ApiModelProperty(value="社群名称",name="communityName",example = "测试社群",required = true)
    
    @Column(name = "community_name")
    private String communityName;

    /**
     * 社群图标
     */
    @Column(name = "community_icon")
    private String communityIcon;

    /**
     * 社群签名
     */
    @Column(name = "community_mark")
    private String communityMark;

    /**
     * 社群标签
     */
    @Column(name = "community_tag")
    private String communityTag;

    /**
     * 社群背景图
     */
    @Column(name = "bg_icon")
    private String bgIcon;

    /**
     * 社群认证信息说明
     */
    @Column(name = "auth_desc")
    private String authDesc;

    /**
     * 社群公告
     */
    @Column(name = "community_notice")
    private String communityNotice;

    /**
     * 社群说明
     */
    @Column(name = "community_desc")
    private String communityDesc;

    /**
     * 社群类别
     */
    @Column(name = "community_type")
    private Integer communityType;

    /**
     * 社长ID
     */
    @Column(name = "admin_id")
    private Integer adminId;

    /**
     * 社群状态(1.正常、0非正常)
     */
    @Column(name = "community_sts")
    private String communitySts;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    @Column(name = "created_date")
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
    
    //社群二维码
    @Column(name = "community_code_icon")
    private String communityCodeIcon;
    
    //社群人数
    @Transient
    private int userNum;
    //社群发布文章数
    @Transient
    private int ArticleNum;
    //总阅读量
    @Transient
    private int readNum;
    

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

	public int getUserNum() {
		return userNum;
	}

	public void setUserNum(int userNum) {
		this.userNum = userNum;
	}

	public int getArticleNum() {
		return ArticleNum;
	}

	public void setArticleNum(int articleNum) {
		ArticleNum = articleNum;
	}

	public int getReadNum() {
		return readNum;
	}

	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}
    
    
}