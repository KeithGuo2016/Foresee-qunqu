package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foresee.utils.StringUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="文章表",description = "文章表")
public class Articles implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    private Integer id;

    /**
     * 文章标题
     */
	@ApiModelProperty(value="文章标题",name="articleTitle",example = "文章标题测试",required = true)
    @Column(name = "article_title")
    private String articleTitle;

    /**
     * 作者ID
     */
	@ApiModelProperty(value="文章作者id",name="userid",example = "286",required = false)
    @Column(name = "userId")
    private Integer userid;

    /**
     * 文章封面
     */
	@ApiModelProperty(value="文章封面图标",name="articleIcon",example = "图标地址",required = false)
    @Column(name = "article_icon")
    private String articleIcon;

    /**
     * 文章简述
     */
	@ApiModelProperty(value="文章简述",name="articleOutline",example = "简述测试",required = false)
    @Column(name = "article_outline")
    private String articleOutline;

    /**
     * 是否首页推荐(0代表否，1代表是)
     */
	@ApiModelProperty(value="是否首页推荐(0代表否，1代表是)",name="isRecommend",example = "0",required = false)
    @Column(name = "is_recommend")
    private String isRecommend;

    /**
     * 文章标签
     */
	@ApiModelProperty(value="文章标签",name="articleTag",example = "学术、计算机、Java",required = false)
    @Column(name = "article_tag")
    private String articleTag;

    /**
     * 社群id
     */
	@ApiModelProperty(hidden = true)
	//@ApiModelProperty(value="文章所属社群id",name="communityId",example = "281",required = false)
    @Column(name = "community_id")
    private Integer communityId;

    /**
     * 是否是草稿(0代表否，1代表是)
     */
	@ApiModelProperty(value="是否是草稿(0代表否，1代表是)",name="isDraft",example = "0",required = true)
    @Column(name = "is_draft")
    private String isDraft;

    /**
     * 文章阅读量
     */
	@ApiModelProperty(hidden = true)
	//@ApiModelProperty(value="文章阅读量",name="readCount",example = "0",required = false)
    @Column(name = "read_count")
    private Integer readCount;

    /**
     * 关注量
     */
	@ApiModelProperty(hidden = true)
	//@ApiModelProperty(value="关注量",name="followCount",example = "0",required = false)
    @Column(name = "follow_count")
    private Integer followCount;

	@ApiModelProperty(hidden = true)
	//@ApiModelProperty(value="关注量",name="followCount",example = "0",required = false)
    @Column(name = "article_zan")
    private Integer articleZan;
	
    /**
     * 审核通过时间
     */
	@ApiModelProperty(hidden = true)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
	//@ApiModelProperty(value="审核通过时间",name="flowDate",example = "",required = false)
    @Column(name = "flow_date")
    private Date flowDate;

    /**
     * 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
	@ApiModelProperty(hidden = true)
	//@ApiModelProperty(value="审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)",name="flowSts",example = "0",required = false)
    @Column(name = "flow_sts")
    private String flowSts;

    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    @ApiModelProperty(hidden = true)
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    @ApiModelProperty(hidden = true)
    @Column(name = "updated_date")
    private Date updatedDate;

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
     * 所属文集
     */
    
    @ApiModelProperty(value="所属文集id",name="gatherId",example = "",required = false)
    @Column(name = "gather_id")
    private Integer gatherId;

    /**
     * 推荐显示日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    @ApiModelProperty(value="推荐显示日期",name="recommendDate",example = "",required = false)
    @Column(name = "recommend_date")
    private Date recommendDate;
    @ApiModelProperty(value="openid",name="openid",example = "286",required = true)
    @Transient
    private String openid;

    /**
     * 文章内容
     */
    @ApiModelProperty(value="文章内容",name="articleContent",example = "内容，计算机语言Java的好处...",required = false)
    @Column(name = "article_content")
    private String articleContent;
    @Column(name = "article_iconBg")
    private String articleIconBg;
    
    
    public String getArticleIconBg() {
		return articleIconBg;
	}

	public void setArticleIconBg(String articleIconBg) {
		this.articleIconBg = articleIconBg;
	}

	public Integer getArticleZan() {
		return articleZan;
	}

	public void setArticleZan(Integer articleZan) {
		this.articleZan = articleZan;
	}

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

    /**
     * 获取文章标题
     *
     * @return article_title - 文章标题
     */
    public String getArticleTitle() {
        return articleTitle;
    }

    /**
     * 设置文章标题
     *
     * @param articleTitle 文章标题
     */
    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    /**
     * 获取作者ID
     *
     * @return userId - 作者ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置作者ID
     *
     * @param userid 作者ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取文章封面
     *
     * @return article_icon - 文章封面
     */
    public String getArticleIcon() {
        return articleIcon;
    }

    /**
     * 设置文章封面
     *
     * @param articleIcon 文章封面
     */
    public void setArticleIcon(String articleIcon) {
        this.articleIcon = articleIcon;
    }

    /**
     * 获取文章简述
     *
     * @return article_outline - 文章简述
     */
    public String getArticleOutline() {
        return articleOutline;
    }

    /**
     * 设置文章简述
     *
     * @param articleOutline 文章简述
     */
    public void setArticleOutline(String articleOutline) {
        this.articleOutline = articleOutline;
    }

    /**
     * 获取是否首页推荐(0代表否，1代表是)
     *
     * @return is_recommend - 是否首页推荐(0代表否，1代表是)
     */
    public String getIsRecommend() {
        return isRecommend;
    }

    /**
     * 设置是否首页推荐(0代表否，1代表是)
     *
     * @param isRecommend 是否首页推荐(0代表否，1代表是)
     */
    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * 获取文章标签
     *
     * @return article_tag - 文章标签
     */
    public String getArticleTag() {
        return articleTag;
    }

    /**
     * 设置文章标签
     *
     * @param articleTag 文章标签
     */
    public void setArticleTag(String articleTag) {
        this.articleTag = articleTag;
    }

    /**
     * 获取社群id
     *
     * @return community_id - 社群id
     */
    public Integer getCommunityId() {
        return communityId;
    }

    /**
     * 设置社群id
     *
     * @param communityId 社群id
     */
    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    /**
     * 获取是否是草稿(0代表否，1代表是)
     *
     * @return is_draft - 是否是草稿(0代表否，1代表是)
     */
    public String getIsDraft() {
        return isDraft;
    }

    /**
     * 设置是否是草稿(0代表否，1代表是)
     *
     * @param isDraft 是否是草稿(0代表否，1代表是)
     */
    public void setIsDraft(String isDraft) {
        this.isDraft = isDraft;
    }

    /**
     * 获取文章阅读量
     *
     * @return read_count - 文章阅读量
     */
    public Integer getReadCount() {
        return readCount;
    }

    /**
     * 设置文章阅读量
     *
     * @param readCount 文章阅读量
     */
    public void setReadCount(Integer readCount) {
        this.readCount = readCount;
    }

    /**
     * 获取关注量
     *
     * @return follow_count - 关注量
     */
    public Integer getFollowCount() {
        return followCount;
    }

    /**
     * 设置关注量
     *
     * @param followCount 关注量
     */
    public void setFollowCount(Integer followCount) {
        this.followCount = followCount;
    }

    /**
     * 获取审核通过时间
     *
     * @return flow_date - 审核通过时间
     */
    public Date getFlowDate() {
        return flowDate;
    }

    /**
     * 设置审核通过时间
     *
     * @param flowDate 审核通过时间
     */
    public void setFlowDate(Date flowDate) {
        this.flowDate = flowDate;
    }

    /**
     * 获取审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     *
     * @return flow_sts - 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
    public String getFlowSts() {
        return flowSts;
    }

    /**
     * 设置审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     *
     * @param flowSts 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
    public void setFlowSts(String flowSts) {
        this.flowSts = flowSts;
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
    	if(StringUtil.isBlank(isDeleted)){
    		isDeleted =false;
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

    /**
     * 获取所属文集
     *
     * @return gather_id - 所属文集
     */
    public Integer getGatherId() {
        return gatherId;
    }

    /**
     * 设置所属文集
     *
     * @param gatherId 所属文集
     */
    public void setGatherId(Integer gatherId) {
        this.gatherId = gatherId;
    }

    /**
     * 获取推荐显示日期
     *
     * @return recommend_date - 推荐显示日期
     */
    public Date getRecommendDate() {
        return recommendDate;
    }

    /**
     * 设置推荐显示日期
     *
     * @param recommendDate 推荐显示日期
     */
    public void setRecommendDate(Date recommendDate) {
        this.recommendDate = recommendDate;
    }

    /**
     * 获取文章内容
     *
     * @return article_content - 文章内容
     */
    public String getArticleContent() {
        return articleContent;
    }

    /**
     * 设置文章内容
     *
     * @param articleContent 文章内容
     */
    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
    }

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
    
    
}