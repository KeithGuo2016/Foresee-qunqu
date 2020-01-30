package com.foresee.vo;

import java.io.Serializable;

public class TagVo implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键
     */
	
    private Integer id;

    /**
     * 标签类型，0社群，1文章 2用户 3社刊 4身份 5征稿
     */
    
    private String tagType;

    /**
     * 标签名称
     */
   
    private String tagName;

    /**
     * 标签说明
     */
    
    private String tagDesc;
    //收藏标签次数
    private Integer followNum;

    /**
     * 删除标志(0:未删除,1:删除)
     */
    
    private Boolean isDeleted;

    
    
	public Integer getFollowNum() {
		return followNum;
	}

	public void setFollowNum(Integer followNum) {
		this.followNum = followNum;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTagType() {
		return tagType;
	}

	public void setTagType(String tagType) {
		this.tagType = tagType;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTagDesc() {
		return tagDesc;
	}

	public void setTagDesc(String tagDesc) {
		this.tagDesc = tagDesc;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

}