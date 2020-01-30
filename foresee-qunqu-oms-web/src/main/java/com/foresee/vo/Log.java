package com.foresee.vo;

import java.util.Date;

import com.foresee.utils.DateUtils;

public class Log{
    private Integer id;

    private String logUrl;

    private String logDescription;

    private String logMethod;

    private String logClassMethod;

    private String logIp;

    private String logParameter;

    private Integer logOperationId;

    private String logOperationName;

    private String createTime;
    
	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogUrl() {
        return logUrl;
    }

    public void setLogUrl(String logUrl) {
        this.logUrl = logUrl == null ? null : logUrl.trim();
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription == null ? null : logDescription.trim();
    }

    public String getLogMethod() {
        return logMethod;
    }

    public void setLogMethod(String logMethod) {
        this.logMethod = logMethod == null ? null : logMethod.trim();
    }

    public String getLogClassMethod() {
        return logClassMethod;
    }

    public void setLogClassMethod(String logClassMethod) {
        this.logClassMethod = logClassMethod == null ? null : logClassMethod.trim();
    }

    public String getLogIp() {
        return logIp;
    }

    public void setLogIp(String logIp) {
        this.logIp = logIp == null ? null : logIp.trim();
    }

    public String getLogParameter() {
        return logParameter;
    }

    public void setLogParameter(String logParameter) {
        this.logParameter = logParameter == null ? null : logParameter.trim();
    }

    public Integer getLogOperationId() {
        return logOperationId;
    }

    public void setLogOperationId(Integer logOperationId) {
        this.logOperationId = logOperationId;
    }

    public String getLogOperationName() {
        return logOperationName;
    }

    public void setLogOperationName(String logOperationName) {
        this.logOperationName = logOperationName == null ? null : logOperationName.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = DateUtils.getDateToString(createTime, "yyyy-MM-dd HH:mm:ss");
    }
}