package com.foresee.model;

import java.util.Date;

public class ContributeDelivery {
    private Integer id;

    private Integer userid;

    private Integer contributesId;

    private String deliveryTitle;

    private String deliveryIcon;

    private String deliveryContent;

    private String isSelect;

    private String isPay;

    private Date createdDate;

    private String createdBy;

    private Integer isDeleted;

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

    public Integer getContributesId() {
        return contributesId;
    }

    public void setContributesId(Integer contributesId) {
        this.contributesId = contributesId;
    }

    public String getDeliveryTitle() {
        return deliveryTitle;
    }

    public void setDeliveryTitle(String deliveryTitle) {
        this.deliveryTitle = deliveryTitle == null ? null : deliveryTitle.trim();
    }

    public String getDeliveryIcon() {
        return deliveryIcon;
    }

    public void setDeliveryIcon(String deliveryIcon) {
        this.deliveryIcon = deliveryIcon == null ? null : deliveryIcon.trim();
    }

    public String getDeliveryContent() {
        return deliveryContent;
    }

    public void setDeliveryContent(String deliveryContent) {
        this.deliveryContent = deliveryContent == null ? null : deliveryContent.trim();
    }

    public String getIsSelect() {
        return isSelect;
    }

    public void setIsSelect(String isSelect) {
        this.isSelect = isSelect == null ? null : isSelect.trim();
    }

    public String getIsPay() {
        return isPay;
    }

    public void setIsPay(String isPay) {
        this.isPay = isPay == null ? null : isPay.trim();
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
        this.createdBy = createdBy == null ? null : createdBy.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}