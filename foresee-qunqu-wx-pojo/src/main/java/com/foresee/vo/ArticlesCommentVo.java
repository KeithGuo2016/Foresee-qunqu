package com.foresee.vo;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ArticlesCommentVo {
	
	 	private Integer id;

	    /**
	     * 评论者ID
	     */
		@ApiModelProperty(value="评论者ID",name="userid",example = "123",required = true)
	    private Integer userid;

	    /**
	     * 文章ID
	     */
	    @ApiModelProperty(value="文章ID",name="articlesId",example = "123",required = true)
	   
	    private Integer articlesId;

	    /**
	     * 父级id
	     */
	    @ApiModelProperty(value="父级id（跟帖时使用）",name="pid",example = "123",required = false)
	    private Integer pid;

	    /**
	     * 回复用户ID（跟帖）
	     */
	    @ApiModelProperty(value="回复用户ID（跟帖时使用）",name="toUserId",example = "123",required = false)
	    
	    private Integer toUserId;

	    /**
	     * 评论内容
	     */
	    @ApiModelProperty(value="评论内容",name="content",example = "写的真好，拜读了",required = true)
	    private String content;

	    /**
	     * 创建时间
	     */
	    @ApiModelProperty(hidden = true)
	    
	    private Date createdDate;

	    /**
	     * 创建人
	     */
	    @ApiModelProperty(hidden = true)
	    
	    private String createdBy;

	    /**
	     * 删除标志
	     */
	    @ApiModelProperty(hidden = true)
	   
	    private Boolean isDeleted;
	    
	  //倒计时时间（时分秒）
		private String countDown;
		
		//评论人名称
		private String userName;
		//背回复人名称
		private String touserName;
		//评论人头像
		private String userIcon;
		//背回复人头像
		private String touserIcon;
		//回复总数
		private int toCommentNum;
		private String articleTitle;
		private String articleIcon;
		
		
		
		private List<ArticlesCommentVo> list;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getUserid() {
			return userid;
		}
		public void setUserid(Integer userid) {
			this.userid = userid;
		}
		public Integer getArticlesId() {
			return articlesId;
		}
		public void setArticlesId(Integer articlesId) {
			this.articlesId = articlesId;
		}
		public Integer getPid() {
			return pid;
		}
		public void setPid(Integer pid) {
			this.pid = pid;
		}
		public Integer getToUserId() {
			return toUserId;
		}
		public void setToUserId(Integer toUserId) {
			this.toUserId = toUserId;
		}
		public String getContent() {
			return content;
		}
		public void setContent(String content) {
			this.content = content;
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
			this.createdBy = createdBy;
		}
		public Boolean getIsDeleted() {
			return isDeleted;
		}
		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}
		public String getCountDown() {
			return countDown;
		}
		public void setCountDown(String countDown) {
			this.countDown = countDown;
		}
		public String getUserName() {
			return userName;
		}
		public void setUserName(String userName) {
			this.userName = userName;
		}
		public String getUserIcon() {
			return userIcon;
		}
		public void setUserIcon(String userIcon) {
			this.userIcon = userIcon;
		}
		public String getTouserIcon() {
			return touserIcon;
		}
		public void setTouserIcon(String touserIcon) {
			this.touserIcon = touserIcon;
		}
		public String getTouserName() {
			return touserName;
		}
		public void setTouserName(String touserName) {
			this.touserName = touserName;
		}
		public List<ArticlesCommentVo> getList() {
			return list;
		}
		public void setList(List<ArticlesCommentVo> list) {
			this.list = list;
		}
		public int getToCommentNum() {
			return toCommentNum;
		}
		public void setToCommentNum(int toCommentNum) {
			this.toCommentNum = toCommentNum;
		}
		public String getArticleTitle() {
			return articleTitle;
		}
		public void setArticleTitle(String articleTitle) {
			this.articleTitle = articleTitle;
		}
		public String getArticleIcon() {
			return articleIcon;
		}
		public void setArticleIcon(String articleIcon) {
			this.articleIcon = articleIcon;
		}
		
		

}
