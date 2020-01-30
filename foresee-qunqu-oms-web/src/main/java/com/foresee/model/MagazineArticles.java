package com.foresee.model;

import java.util.Date;

public class MagazineArticles {
    private Integer id;

    private Integer magazinesId;

    private Integer articlesId;

    private String magazineTitle;

    private Date createdDate;

    private Date updatedDate;

    private String createdBy;

    private String updatedBy;

    private Integer isDeleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMagazinesId() {
        return magazinesId;
    }

    public void setMagazinesId(Integer magazinesId) {
        this.magazinesId = magazinesId;
    }

    public Integer getArticlesId() {
        return articlesId;
    }

    public void setArticlesId(Integer articlesId) {
        this.articlesId = articlesId;
    }

    public String getMagazineTitle() {
        return magazineTitle;
    }

    public void setMagazineTitle(String magazineTitle) {
        this.magazineTitle = magazineTitle == null ? null : magazineTitle.trim();
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