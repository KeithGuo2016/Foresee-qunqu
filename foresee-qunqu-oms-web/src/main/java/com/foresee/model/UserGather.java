package com.foresee.model;

import java.util.Date;

public class UserGather {
    private Integer id;

    private Integer userid;

    private String gatherName;

    private String gatherDesc;

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

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getGatherName() {
        return gatherName;
    }

    public void setGatherName(String gatherName) {
        this.gatherName = gatherName == null ? null : gatherName.trim();
    }

    public String getGatherDesc() {
        return gatherDesc;
    }

    public void setGatherDesc(String gatherDesc) {
        this.gatherDesc = gatherDesc == null ? null : gatherDesc.trim();
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