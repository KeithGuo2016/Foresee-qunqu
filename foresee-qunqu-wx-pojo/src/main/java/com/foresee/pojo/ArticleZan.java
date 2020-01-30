package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel(value="文章点赞表",description = "文章点赞表")
@Table(name = "article_zan")
public class ArticleZan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    private Integer id;


    /**
     * 作者ID
     */
	@ApiModelProperty(value="用户id",name="userid",example = "286",required = true)
    @Column(name = "userid")
    private Integer userid;

    /**
     * 文章封面
     */
	@ApiModelProperty(value="文章id",name="articleid",example = "288",required = true)
    @Column(name = "articleid")
    private Integer articleid;
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd",locale = "zh",timezone = "GMT+8")
    @ApiModelProperty(hidden = true)
    @Column(name = "created_date")
    private Date createdDate;
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
	public Integer getArticleid() {
		return articleid;
	}
	public void setArticleid(Integer articleid) {
		this.articleid = articleid;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

  
    
    
}