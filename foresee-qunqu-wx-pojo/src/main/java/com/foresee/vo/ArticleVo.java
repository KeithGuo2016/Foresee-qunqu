package com.foresee.vo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * 文章视图
 * @author DELL
 *
 */
public class ArticleVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden = true)
	
    private Integer id;

    /**
     * 文章标题
     */
	@ApiModelProperty(value="文章标题",name="articleTitle",example = "文章标题测试",required = true)
    private String articleTitle;
	//点赞量
    private Integer articleZan;
    /**
     * 作者ID
     */
	@ApiModelProperty(value="文章作者id",name="userid",example = "286",required = true)
    private Integer userid;

    /**
     * 文章封面
     */
	@ApiModelProperty(value="文章封面图标",name="articleIcon",example = "图标地址",required = true)
    private String articleIcon;

    /**
     * 文章简述
     */
	@ApiModelProperty(value="文章简述",name="articleOutline",example = "简述测试",required = false)
    
    private String articleOutline;

    /**
     * 是否首页推荐(0代表否，1代表是)
     */
	@ApiModelProperty(value="是否首页推荐(0代表否，1代表是)",name="isRecommend",example = "0",required = false)
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
	@ApiModelProperty(value="文章所属社群id",name="communityId",example = "281",required = true)
    private Integer communityId;

    /**
     * 是否是草稿(0代表否，1代表是)
     */
	@ApiModelProperty(value="是否是草稿(0代表否，1代表是)",name="isDraft",example = "0",required = true)
    private String isDraft;

    /**
     * 文章阅读量
     */
	@ApiModelProperty(value="文章阅读量",name="readCount",example = "0",required = false)
    
    private Integer readCount;

    /**
     * 关注量
     */
	@ApiModelProperty(value="关注量",name="followCount",example = "0",required = false)
    private Integer followCount;

    /**
     * 审核通过时间
     */
	@ApiModelProperty(value="审核通过时间",name="flowDate",example = "",required = false)
    private Date flowDate;

    /**
     * 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
	@ApiModelProperty(value="审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)",name="flowSts",example = "0",required = false)
    
    private String flowSts;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    private Date createdDate;

    /**
     * 所属文集
     */
    @ApiModelProperty(value="所属文集id",name="gatherId",example = "",required = false)
    
    private Integer gatherId;

    /**
     * 推荐显示日期
     */
    @ApiModelProperty(value="推荐显示日期",name="recommendDate",example = "",required = false)
    
    private String recommendDate;

    /**
     * 文章内容
     */
    @ApiModelProperty(value="文章内容",name="articleContent",example = "内容，计算机语言Java的好处...",required = true)
    
    private String articleContent;
    
    
    /**
     * openId
     */
    private String openId;
    
    private String nickName;
    
    private String headUrl;
    
    private String communityName;
    
    private String communityIcon;
    
    private String communityMark;
    
    private String communityTag;
    //是否收藏
    private Integer isFollow;
    //是否点赞
    private Integer isZan;
    //回复总数
    private Integer commentNum;
    //是否已经关注作者
    private Integer isFollowAuthor;
    //社群用户数
    private Integer userNum;
    
    private String createdYear;
    private String createdMonth;
    private String createdDay;
    private String articleTagNmae;
    

	
    private String articleIconBg;
    
    
    public String getArticleIconBg() {
		return articleIconBg;
	}

	public void setArticleIconBg(String articleIconBg) {
		this.articleIconBg = articleIconBg;
	}
	public String getArticleTagNmae() {
		return articleTagNmae;
	}

	public void setArticleTagNmae(String articleTagNmae) {
		this.articleTagNmae = articleTagNmae;
	}

	public String getCreatedYear() {
		return createdYear;
	}

	public void setCreatedYear(String createdYear) {
		this.createdYear = createdYear;
	}

	public String getCreatedMonth() {
		return createdMonth;
	}

	public void setCreatedMonth(String createdMonth) {
		this.createdMonth = createdMonth;
	}

	public String getCreatedDay() {
		return createdDay;
	}

	public void setCreatedDay(String createdDay) {
		this.createdDay = createdDay;
	}

	public Integer getArticleZan() {
		return articleZan;
	}

	public void setArticleZan(Integer articleZan) {
		this.articleZan = articleZan;
	}

	public Integer getUserNum() {
		return userNum;
	}

	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}

	public Integer getIsFollowAuthor() {
		return isFollowAuthor;
	}

	public void setIsFollowAuthor(Integer isFollowAuthor) {
		this.isFollowAuthor = isFollowAuthor;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
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
		this.articleTitle = articleTitle;
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
		this.articleIcon = articleIcon;
	}

	public String getArticleOutline() {
		return articleOutline;
	}

	public void setArticleOutline(String articleOutline) {
		this.articleOutline = articleOutline;
	}

	public String getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(String isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getArticleTag() {
		return articleTag;
	}

	public void setArticleTag(String articleTag) {
		this.articleTag = articleTag;
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
		this.isDraft = isDraft;
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
		this.flowSts = flowSts;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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

	public void setRecommendDate(String recommendDate) {
		this.recommendDate = recommendDate;
	}

	public String getArticleContent() {
		return articleContent;
	}

	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
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

	public String getCommunityMark() {
		return communityMark;
	}

	public void setCommunityMark(String communityMark) {
		this.communityMark = communityMark;
	}

	public String getCommunityTag() {
		return communityTag;
	}

	public void setCommunityTag(String communityTag) {
		this.communityTag = communityTag;
	}

	public Integer getIsFollow() {
		return isFollow;
	}

	public void setIsFollow(Integer isFollow) {
		this.isFollow = isFollow;
	}

	public Integer getIsZan() {
		return isZan;
	}

	public void setIsZan(Integer isZan) {
		this.isZan = isZan;
	}
    
    

}
