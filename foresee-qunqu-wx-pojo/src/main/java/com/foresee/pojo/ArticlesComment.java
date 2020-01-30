package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="文章评论表",description = "文章评论表")
@Table(name = "articles_comment")
public class ArticlesComment implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(hidden = true)
	@Id
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
    @Column(name = "articles_id")
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
    @Column(name = "to_user_id")
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
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 删除标志
     */
    @ApiModelProperty(hidden = true)
    @Column(name = "is_deleted")
    private Boolean isDeleted;
    
    

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
     * 获取评论者ID
     *
     * @return userid - 评论者ID
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置评论者ID
     *
     * @param userid 评论者ID
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取文章ID
     *
     * @return articles_id - 文章ID
     */
    public Integer getArticlesId() {
        return articlesId;
    }

    /**
     * 设置文章ID
     *
     * @param articlesId 文章ID
     */
    public void setArticlesId(Integer articlesId) {
        this.articlesId = articlesId;
    }

    /**
     * 获取父级id
     *
     * @return pid - 父级id
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 设置父级id
     *
     * @param pid 父级id
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 获取回复用户ID（跟帖）
     *
     * @return to_user_id - 回复用户ID（跟帖）
     */
    public Integer getToUserId() {
        return toUserId;
    }

    /**
     * 设置回复用户ID（跟帖）
     *
     * @param toUserId 回复用户ID（跟帖）
     */
    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * 获取评论内容
     *
     * @return content - 评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     *
     * @param content 评论内容
     */
    public void setContent(String content) {
        this.content = content;
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
}