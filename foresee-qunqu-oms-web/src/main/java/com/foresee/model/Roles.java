package com.foresee.model;

import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Roles extends PageInfo{
    private Integer id;

    private Date createtime;
    
    @NotBlank(message="{roles.rolename.notblank}")
    @Length(min=2,max=8,message="{roles.rolename.length}")
    private String rolename;

    private Integer roledesc;

    private Integer rolepid;

    @NotNull(message="{roles.createid.notnull}")
    @Min(value=1,message="{roles.createid.min}")
    private Integer createid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename == null ? null : rolename.trim();
    }

    public Integer getRoledesc() {
        return roledesc;
    }

    public void setRoledesc(Integer roledesc) {
        this.roledesc = roledesc;
    }

    public Integer getRolepid() {
        return rolepid;
    }

    public void setRolepid(Integer rolepid) {
        this.rolepid = rolepid;
    }

    public Integer getCreateid() {
        return createid;
    }

    public void setCreateid(Integer createid) {
        this.createid = createid;
    }
}