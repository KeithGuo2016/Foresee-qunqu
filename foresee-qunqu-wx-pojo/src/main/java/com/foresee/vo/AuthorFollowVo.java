package com.foresee.vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AuthorFollowVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    /**
     * 关注用户ID
     */
	
    private Integer userid;

    /**
     * 作者id
     */
	
    private Integer authorId;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date createdDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date updatedDate;

    
    private String createdBy;

    /**
     * 更新人
     */
    
    private String updatedBy;

    /**
     * 删除标志
     */
   
    private Boolean isDeleted;
    //关注人数
    private Integer followUserNum;
    //发表作品数
    private Integer articlesNum;
    //总阅读量
    private Integer readNum;
    
    private String nickName;
    private String headUrl;
    
    /**
     * 是否是VIP(0表示否，1表示是)
     */
   
    private Integer isVip;

    /**
     * 是否是管理员(0表示否，1表示是)
     */
    
    private Integer isAdmin;

    /**
     * 是否是社长(0表示否，1表示是)
     */
    
    private Integer isCommunity;
    
    private String openId;

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

    public Integer getIsVip() {
		return isVip;
	}

	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getIsCommunity() {
		return isCommunity;
	}

	public void setIsCommunity(Integer isCommunity) {
		this.isCommunity = isCommunity;
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
     * 获取作者id
     *
     * @return author_id - 作者id
     */
    public Integer getAuthorId() {
        return authorId;
    }

    /**
     * 设置作者id
     *
     * @param authorId 作者id
     */
    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
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

	public Integer getFollowUserNum() {
		return followUserNum;
	}

	public void setFollowUserNum(Integer followUserNum) {
		this.followUserNum = followUserNum;
	}

	public Integer getArticlesNum() {
		return articlesNum;
	}

	public void setArticlesNum(Integer articlesNum) {
		this.articlesNum = articlesNum;
	}

	public Integer getReadNum() {
		return readNum;
	}

	public void setReadNum(Integer readNum) {
		this.readNum = readNum;
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

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
    
    
}
