package com.foresee.vo;

import java.io.Serializable;

public class Menus implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String menuname;
    
    private String menuurl;

    private String menuicon;

    private Integer menulevel;

    private Integer menupid;

    private Integer menudesc;

    private Integer createid;

    private Integer createtime;

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

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
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