package com.foresee.vo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foresee.pojo.ContributeDelivery;
import com.foresee.utils.StringUtil;
import com.github.pagehelper.PageInfo;

import io.swagger.annotations.ApiModelProperty;

public class ContributesVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(hidden = true)
	@Id
    private Integer id;

    /**
     * 创建人id
     */
	@ApiModelProperty(value=" 创建人id",name="createUserid",example = "286",required = true)
    
    private Integer createUserid;

    /**
     * 征稿人id，暂时不用
     */
    @ApiModelProperty(value="征稿人id，暂时不用",name="contributeUserid",example = "",required = false)
   
    private Integer contributeUserid;

    /**
     * 标题
     */
    @ApiModelProperty(value="标题",name="contributeTitle",example = "小微金融的未来发展方向",required = true)
    
    private String contributeTitle;

    /**
     * 封面图
     */
    @ApiModelProperty(value="封面图",name="contributeIcon",example = "http://123",required = true)
    
    private String contributeIcon;

    /**
     * 征稿方向
     */
    @ApiModelProperty(value="征稿方向",name="orientation",example = "金融、科研",required = true)
    private String orientation;

    /**
     * 标签
     */
    @ApiModelProperty(value="标签",name="contributeTag",example = "金融、银行、大数据",required = true)
    
    private String contributeTag;

    /**
     * 欲购稿件数量
     */
    @ApiModelProperty(value="欲购稿件数量",name="buyNum",example = "1",required = true)
    
    private Integer buyNum;

    /**
     * 赏金额度最大值
     */
    @ApiModelProperty(value="赏金额度最大值",name="maxMoney",example = "0.00",required = true)
    
    private String maxMoney;

    /**
     * 赏金最小值
     */
    @ApiModelProperty(value="赏金最小值",name="minMoney",example = "0.00",required = true)
    
    private String minMoney;

    /**
     * 赏金确定额度
     */
    @ApiModelProperty(value="赏金确定额度",name="trueMoney",example = "0.00",required = true)
    
    private String trueMoney;

    /**
     * 征稿开始时间
     */
    @ApiModelProperty(value="征稿开始时间",name="startDate",example = "2019-07-27",required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date startDate;

    /**
     * 征稿截止时间
     */
    @ApiModelProperty(value="征稿截止时间",name="endDate",example = "2019-08-01",required = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date endDate;

    /**
     * 征稿范围(0全部可见、1社群)
     */
    @ApiModelProperty(value=" 征稿范围(0全部可见、1社群)",name="contributeRange",example = "0",required = true)

    private String contributeRange;

    /**
     * 投放稿件可见范围（0全部可见，1仅征稿方）
     */
    @ApiModelProperty(value="投放稿件可见范围（0全部可见，1仅征稿方）",name="userSeeRange",example = "0",required = true)

    private String userSeeRange;

    /**
     * 征稿类型（1收费 0免费）
     */
    @ApiModelProperty(value="征稿类型（1收费 0免费）",name="contributeType",example = "0",required = true)
 
    private Integer contributeType;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",locale = "zh",timezone = "GMT+8")
    private Date createdDate;

    /**
     * 更新时间
     */
    @ApiModelProperty(hidden = true)

    private Date updatedDate;

    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)

    private String createdBy;

    /**
     * 更新人
     */
    @ApiModelProperty(hidden = true)

    private String updatedBy;

    /**
     * 删除标志
     */
    @ApiModelProperty(hidden = true)

    private Boolean isDeleted;

    /**
     * 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
    @ApiModelProperty(value="审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)",name="flowSts",example = "0",required = false)

    private String flowSts;

    /**
     * 征稿描述
     */
    @ApiModelProperty(value="征稿描述",name="contributeDesc",example = "征稿描述：需要注意事项",required = false)
    
    private String contributeDesc;

    /**
     * 征稿案例描述
     */
    @ApiModelProperty(value="征稿案例描述",name="caseDesc",example = "表述案例说明：测试",required = false)
 
    private String caseDesc;

    /**
     * 所属社群id
     */
	@ApiModelProperty(value="所属社群id",name="communityId",example = "281",required = true)
    
    private Integer communityId;
	//标签名
	private String tagName;
	//倒计时时间（时分秒）
	private String countDown;
	//进行状态,1进行中，0已结束
	private Integer status;
	//投稿列表
	private PageInfo<ContributeDeliveryVo> info;
    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取创建人id
     *
     * @return create_userId - 创建人id
     */
    public Integer getCreateUserid() {
        return createUserid;
    }

    /**
     * 设置创建人id
     *
     * @param createUserid 创建人id
     */
    public void setCreateUserid(Integer createUserid) {
        this.createUserid = createUserid;
    }

    /**
     * 获取征稿人id，暂时不用
     *
     * @return contribute_userId - 征稿人id，暂时不用
     */
    public Integer getContributeUserid() {
        return contributeUserid;
    }

    /**
     * 设置征稿人id，暂时不用
     *
     * @param contributeUserid 征稿人id，暂时不用
     */
    public void setContributeUserid(Integer contributeUserid) {
        this.contributeUserid = contributeUserid;
    }

    /**
     * 获取标题
     *
     * @return contribute_title - 标题
     */
    public String getContributeTitle() {
        return contributeTitle;
    }

    /**
     * 设置标题
     *
     * @param contributeTitle 标题
     */
    public void setContributeTitle(String contributeTitle) {
        this.contributeTitle = contributeTitle;
    }

    /**
     * 获取封面图
     *
     * @return contribute_icon - 封面图
     */
    public String getContributeIcon() {
        return contributeIcon;
    }

    /**
     * 设置封面图
     *
     * @param contributeIcon 封面图
     */
    public void setContributeIcon(String contributeIcon) {
        this.contributeIcon = contributeIcon;
    }

    /**
     * 获取征稿方向
     *
     * @return orientation - 征稿方向
     */
    public String getOrientation() {
        return orientation;
    }

    /**
     * 设置征稿方向
     *
     * @param orientation 征稿方向
     */
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    /**
     * 获取标签
     *
     * @return contribute_tag - 标签
     */
    public String getContributeTag() {
        return contributeTag;
    }

    /**
     * 设置标签
     *
     * @param contributeTag 标签
     */
    public void setContributeTag(String contributeTag) {
        this.contributeTag = contributeTag;
    }

    /**
     * 获取欲购稿件数量
     *
     * @return buy_num - 欲购稿件数量
     */
    public Integer getBuyNum() {
        return buyNum;
    }

    /**
     * 设置欲购稿件数量
     *
     * @param buyNum 欲购稿件数量
     */
    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    /**
     * 获取赏金额度最大值
     *
     * @return max_money - 赏金额度最大值
     */
    public String getMaxMoney() {
        return maxMoney;
    }

    /**
     * 设置赏金额度最大值
     *
     * @param maxMoney 赏金额度最大值
     */
    public void setMaxMoney(String maxMoney) {
        this.maxMoney = maxMoney;
    }

    /**
     * 获取赏金最小值
     *
     * @return min_money - 赏金最小值
     */
    public String getMinMoney() {
        return minMoney;
    }

    /**
     * 设置赏金最小值
     *
     * @param minMoney 赏金最小值
     */
    public void setMinMoney(String minMoney) {
        this.minMoney = minMoney;
    }

    /**
     * 获取赏金确定额度
     *
     * @return true_money - 赏金确定额度
     */
    public String getTrueMoney() {
        return trueMoney;
    }

    /**
     * 设置赏金确定额度
     *
     * @param trueMoney 赏金确定额度
     */
    public void setTrueMoney(String trueMoney) {
        this.trueMoney = trueMoney;
    }

    /**
     * 获取征稿开始时间
     *
     * @return start_date - 征稿开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置征稿开始时间
     *
     * @param startDate 征稿开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取征稿截止时间
     *
     * @return end_date - 征稿截止时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置征稿截止时间
     *
     * @param endDate 征稿截止时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取征稿范围(0全部可见、1社群)
     *
     * @return contribute_range - 征稿范围(0全部可见、1社群)
     */
    public String getContributeRange() {
        return contributeRange;
    }

    /**
     * 设置征稿范围(0全部可见、1社群)
     *
     * @param contributeRange 征稿范围(0全部可见、1社群)
     */
    public void setContributeRange(String contributeRange) {
        this.contributeRange = contributeRange;
    }

    /**
     * 获取投放稿件可见范围（0全部可见，1仅征稿方）
     *
     * @return user_see_range - 投放稿件可见范围（0全部可见，1仅征稿方）
     */
    public String getUserSeeRange() {
        return userSeeRange;
    }

    /**
     * 设置投放稿件可见范围（0全部可见，1仅征稿方）
     *
     * @param userSeeRange 投放稿件可见范围（0全部可见，1仅征稿方）
     */
    public void setUserSeeRange(String userSeeRange) {
        this.userSeeRange = userSeeRange;
    }

    /**
     * 获取征稿类型（1收费 0免费）
     *
     * @return contribute_type - 征稿类型（1收费 0免费）
     */
    public Integer getContributeType() {
        return contributeType;
    }

    /**
     * 设置征稿类型（1收费 0免费）
     *
     * @param contributeType 征稿类型（1收费 0免费）
     */
    public void setContributeType(Integer contributeType) {
        this.contributeType = contributeType;
    }

    /**
     * 获取创建时间
     *
     * @return created_date - 创建时间
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * 设置创建时间
     *
     * @param createdDate 创建时间
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * 获取更新时间
     *
     * @return updated_date - 更新时间
     */
    public Date getUpdatedDate() {
        return updatedDate;
    }

    /**
     * 设置更新时间
     *
     * @param updatedDate 更新时间
     */
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    /**
     * 获取创建人
     *
     * @return created_by - 创建人
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * 设置创建人
     *
     * @param createdBy 创建人
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * 获取更新人
     *
     * @return updated_by - 更新人
     */
    public String getUpdatedBy() {
        return updatedBy;
    }

    /**
     * 设置更新人
     *
     * @param updatedBy 更新人
     */
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    /**
     * 获取删除标志
     *
     * @return is_deleted - 删除标志
     */
    public Boolean getIsDeleted() {
    	if(StringUtil.isBlank(isDeleted)) {
    		isDeleted = false;
    	}
        return isDeleted;
    }

    /**
     * 设置删除标志
     *
     * @param isDeleted 删除标志
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     *
     * @return flow_sts - 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
    public String getFlowSts() {
        return flowSts;
    }

    /**
     * 设置审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     *
     * @param flowSts 审批阶段(0新建，1表示审核通过，2表示审核拒绝，3表示审核中)
     */
    public void setFlowSts(String flowSts) {
        this.flowSts = flowSts;
    }

    /**
     * 获取征稿描述
     *
     * @return contribute_desc - 征稿描述
     */
    public String getContributeDesc() {
        return contributeDesc;
    }

    /**
     * 设置征稿描述
     *
     * @param contributeDesc 征稿描述
     */
    public void setContributeDesc(String contributeDesc) {
        this.contributeDesc = contributeDesc;
    }

    /**
     * 获取征稿案例描述
     *
     * @return case_desc - 征稿案例描述
     */
    public String getCaseDesc() {
        return caseDesc;
    }

    /**
     * 设置征稿案例描述
     *
     * @param caseDesc 征稿案例描述
     */
    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

	public Integer getCommunityId() {
		return communityId;
	}

	public void setCommunityId(Integer communityId) {
		this.communityId = communityId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getCountDown() {
		return countDown;
	}

	public void setCountDown(String countDown) {
		this.countDown = countDown;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public PageInfo<ContributeDeliveryVo> getInfo() {
		return info;
	}

	public void setInfo(PageInfo<ContributeDeliveryVo> info) {
		this.info = info;
	}
    
    
}