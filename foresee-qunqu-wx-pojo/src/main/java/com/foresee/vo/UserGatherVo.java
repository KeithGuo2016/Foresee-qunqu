package com.foresee.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.foresee.utils.StringUtil;

import io.swagger.annotations.ApiModel;

@ApiModel(value="用户文集表",description = "用户文集表")
@Table(name = "user_gather")
public class UserGatherVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Integer id;

    /**
     * 用户id
     */
    
    private Integer userid;

    /**
     * 文集名称
     */
    
    private String gatherName;

    /**
     * 说明
     */
    
    private String gatherDesc;

    /**
     * 创建时间
     */
   
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
    private String gatherIcon;
    /**
     * 微信昵称
     */
   
    private String nickName;

    /**
     * 微信头像
     */
    
    private String headUrl;

    private Integer readCount;
    //文章数
    private Integer articleCount;
    
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

    public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	/**
     * 获取用户id
     *
     * @return userId - 用户id
     */
    public Integer getUserid() {
        return userid;
    }

    /**
     * 设置用户id
     *
     * @param userid 用户id
     */
    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    /**
     * 获取文集名称
     *
     * @return gather_name - 文集名称
     */
    public String getGatherName() {
        return gatherName;
    }

    /**
     * 设置文集名称
     *
     * @param gatherName 文集名称
     */
    public void setGatherName(String gatherName) {
        this.gatherName = gatherName;
    }

    public String getGatherIcon() {
		return gatherIcon;
	}

	public void setGatherIcon(String gatherIcon) {
		this.gatherIcon = gatherIcon;
	}

	/**
     * 获取说明
     *
     * @return gather_desc - 说明
     */
    public String getGatherDesc() {
        return gatherDesc;
    }

    /**
     * 设置说明
     *
     * @param gatherDesc 说明
     */
    public void setGatherDesc(String gatherDesc) {
        this.gatherDesc = gatherDesc;
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

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}
    
    
}