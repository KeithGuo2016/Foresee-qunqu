package com.foresee.vo;


public class Roles {
    private Integer id;

    private Integer createtime;
    
    private String rolename;

    private Integer roledesc;

    private Integer rolepid;

    private Integer createid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
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