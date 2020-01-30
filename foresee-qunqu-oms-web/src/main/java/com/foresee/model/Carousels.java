package com.foresee.model;

import java.util.Date;

public class Carousels extends PageInfo{
    private Integer id;

    private Integer orderNum;

    private String carouselIcon;

    private String carouselType;

    private String contentLink;

    private Date createdDate;

    private Date updatedDate;

    private String createdBy;

    private String updatedBy;

    private Integer isDeleted;
    
    private String createdName;

    private String updatedName;
    
    
    
    public String getCreatedName() {
		return createdName;
	}

	public void setCreatedName(String createdName) {
		this.createdName = createdName;
	}

	public String getUpdatedName() {
		return updatedName;
	}

	public void setUpdatedName(String updatedName) {
		this.updatedName = updatedName;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getCarouselIcon() {
        return carouselIcon;
    }

    public void setCarouselIcon(String carouselIcon) {
        this.carouselIcon = carouselIcon == null ? null : carouselIcon.trim();
    }

    public String getCarouselType() {
        return carouselType;
    }

    public void setCarouselType(String carouselType) {
        this.carouselType = carouselType == null ? null : carouselType.trim();
    }

    public String getContentLink() {
        return contentLink;
    }

    public void setContentLink(String contentLink) {
        this.contentLink = contentLink == null ? null : contentLink.trim();
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy == null ? null : updatedBy.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}