package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.foresee.utils.StringUtil;

import io.swagger.annotations.ApiModelProperty;
/**
 * 轮播图表
 * @author DELL
 *
 */
public class Carousels implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(hidden = true)
	@Id
    private Integer id;

    /**
     * 排序号
     */
    @Column(name = "order_num")
    private Integer orderNum;

    /**
     * 封面图
     */
    @Column(name = "carousel_icon")
    private String carouselIcon;

    /**
     * 类型，详情链接来自于哪里：1社群、2文章、3社刊
     */
    @Column(name = "carousel_type")
    private String carouselType;

    /**
     * 详情链接（存的是id）
     */
    @Column(name = "content_link")
    private String contentLink;

    /**
     * 创建时间
     */
    @Column(name = "created_date")
    private Date createdDate;

    /**
     * 更新时间
     */
    @Column(name = "updated_date")
    private Date updatedDate;

    /**
     * 创建人
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * 更新人
     */
    @Column(name = "updated_by")
    private String updatedBy;

    /**
     * 删除标志
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

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
     * 获取排序号
     *
     * @return order_num - 排序号
     */
    public Integer getOrderNum() {
        return orderNum;
    }

    /**
     * 设置排序号
     *
     * @param orderNum 排序号
     */
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取封面图
     *
     * @return carousel_icon - 封面图
     */
    public String getCarouselIcon() {
        return carouselIcon;
    }

    /**
     * 设置封面图
     *
     * @param carouselIcon 封面图
     */
    public void setCarouselIcon(String carouselIcon) {
        this.carouselIcon = carouselIcon;
    }

    /**
     * 获取类型，详情链接来自于哪里：社群、文章、社刊
     *
     * @return carousel_type - 类型，详情链接来自于哪里：社群、文章、社刊
     */
    public String getCarouselType() {
        return carouselType;
    }

    /**
     * 设置类型，详情链接来自于哪里：社群、文章、社刊
     *
     * @param carouselType 类型，详情链接来自于哪里：社群、文章、社刊
     */
    public void setCarouselType(String carouselType) {
        this.carouselType = carouselType;
    }

    /**
     * 获取详情链接
     *
     * @return content_link - 详情链接
     */
    public String getContentLink() {
        return contentLink;
    }

    /**
     * 设置详情链接
     *
     * @param contentLink 详情链接
     */
    public void setContentLink(String contentLink) {
        this.contentLink = contentLink;
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
}