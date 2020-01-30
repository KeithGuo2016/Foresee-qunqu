package com.foresee.model;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class Menus extends PageInfo {
	

	private Integer id;

    @NotBlank(message="{menus.menuname.notblank}")
    @Length(min=1,max=50,message="{menus.menuname.length}")
    private String menuname;
    
    @NotBlank(message="{menus.menuurl.notblank}")
    @Length(min=1,max=50,message="{menus.menuurl.length}")
    private String menuurl;

    private String menuicon;

    @NotNull(message="{menus.menulevel.notnull}")
    @Min(value=1,message="{menus.menulevel.min}")
    @Max(value=3,message="{menus.menulevel.max}")
    private Integer menulevel;

    private Integer menupid;

    private Integer menudesc;

    @NotNull(message="{menus.createid.notnull}")
    @Min(value=1,message="{menus.createid.min}")
    private Integer createid;

    private Date createtime;

    private Integer isenable;

    private String th1;

    private String th2;

    private String th3;

    private String th4;

    private String th5;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname == null ? null : menuname.trim();
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl == null ? null : menuurl.trim();
    }

    public String getMenuicon() {
        return menuicon;
    }

    public void setMenuicon(String menuicon) {
        this.menuicon = menuicon == null ? null : menuicon.trim();
    }

    public Integer getMenulevel() {
        return menulevel;
    }

    public void setMenulevel(Integer menulevel) {
        this.menulevel = menulevel;
    }

    public Integer getMenupid() {
        return menupid;
    }

    public void setMenupid(Integer menupid) {
        this.menupid = menupid;
    }

    public Integer getMenudesc() {
        return menudesc;
    }

    public void setMenudesc(Integer menudesc) {
        this.menudesc = menudesc;
    }

    public Integer getCreateid() {
        return createid;
    }

    public void setCreateid(Integer createid) {
        this.createid = createid;
    }
    public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getIsenable() {
        return isenable;
    }

    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
    }

    public String getTh1() {
        return th1;
    }

    public void setTh1(String th1) {
        this.th1 = th1 == null ? null : th1.trim();
    }

    public String getTh2() {
        return th2;
    }

    public void setTh2(String th2) {
        this.th2 = th2 == null ? null : th2.trim();
    }

    public String getTh3() {
        return th3;
    }

    public void setTh3(String th3) {
        this.th3 = th3 == null ? null : th3.trim();
    }

    public String getTh4() {
        return th4;
    }

    public void setTh4(String th4) {
        this.th4 = th4 == null ? null : th4.trim();
    }

    public String getTh5() {
        return th5;
    }

    public void setTh5(String th5) {
        this.th5 = th5 == null ? null : th5.trim();
    }
}