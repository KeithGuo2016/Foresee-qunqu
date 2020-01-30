package com.foresee.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Roleandmenu extends PageInfo{
    private Integer id;

    @NotNull(message="{roleandmenu.roleid.notnull}")
    @Min(value=1,message="{roleandmenu.roleid.min}")
    private Integer roleid;

    @NotBlank(message="{roleandmenu.menuids.notblank}")
    @Length(min=1,message="{roleandmenu.menuids.length}")
    private String menuids;
    
    private Integer menuid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public String getMenuids() {
        return menuids;
    }

    public void setMenuids(String menuids) {
        this.menuids = menuids == null ? null : menuids.trim();
    }

	public Integer getMenuid() {
		return menuid;
	}

	public void setMenuid(Integer menuid) {
		this.menuid = menuid;
	}
    
    
}