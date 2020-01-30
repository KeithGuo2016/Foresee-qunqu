package com.foresee.model;


import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.foresee.service.validation.NotAccountUserAccount;
import com.foresee.service.validation.NotAccountUserPhone;

public class User extends PageInfo{
    private Integer id;

    @NotBlank(message="{accounts.username.notblank}")
    @Length(min=2,max=8,message="{accounts.username.length}")
    private String username;

    @NotBlank(message="{accounts.useraccount.notblank}")
    @Pattern(regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,11}$",message="{accounts.useraccount.pattern}")
    @NotAccountUserAccount(message="{accounts.useraccount.notaccountuseraccount}")
    private String useraccount;

    @NotBlank(message="{accounts.userpwd.notblank}")
    @Pattern(regexp="^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$",message="{accounts.userpwd.pattern}")
    private String userpwd;

    private Date createtime;

    private Integer createid;

    @NotBlank(message="{accounts.userphone.notblank}")
    @Length(min=11,max=11,message="{accounts.userphone.length}")
    @NotAccountUserPhone(message="{accounts.userphone.notAccountuserphone}")
    private String userphone;

    private Integer roleid;

    private Integer isenable;
    
    private Integer provinceid;
    
    private Integer cityid;
    
    private Integer channelid;

    private String th1;

    private String th2;

    private String th3;

    private String th4;

    private String th5;
    
    private String rolename;
    
    private String verificationCode;
    
    public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUseraccount() {
        return useraccount;
    }

    public void setUseraccount(String useraccount) {
        this.useraccount = useraccount == null ? null : useraccount.trim();
    }

    public String getUserpwd() {
        return userpwd;
    }

    public void setUserpwd(String userpwd) {
        this.userpwd = userpwd == null ? null : userpwd.trim();
    }

    public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getCreateid() {
        return createid;
    }

    public void setCreateid(Integer createid) {
        this.createid = createid;
    }

    public String getUserphone() {
        return userphone;
    }

    public void setUserphone(String userphone) {
        this.userphone = userphone == null ? null : userphone.trim();
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
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

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Integer getProvinceid() {
		return provinceid;
	}

	public void setProvinceid(Integer provinceid) {
		this.provinceid = provinceid;
	}

	public Integer getCityid() {
		return cityid;
	}

	public void setCityid(Integer cityid) {
		this.cityid = cityid;
	}

	public Integer getChannelid() {
		return channelid;
	}

	public void setChannelid(Integer channelid) {
		this.channelid = channelid;
	}
    
    
}