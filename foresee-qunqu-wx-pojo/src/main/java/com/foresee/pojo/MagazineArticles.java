package com.foresee.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import com.foresee.utils.StringUtil;

@Table(name = "magazine_articles")
public class MagazineArticles implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    private Integer id;

    /**
     * 社刊id
     */
    @Column(name = "magazines_id")
    private Integer magazinesId;

    /**
     * 文章id
     */
    @Column(name = "articles_id")
    private Integer articlesId;

    /**
     * 列表名称
     */
    @Column(name = "magazine_title")
    private String magazineTitle;

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
     * 获取社刊id
     *
     * @return magazines_id - 社刊id
     */
    public Integer getMagazinesId() {
        return magazinesId;
    }

    /**
     * 设置社刊id
     *
     * @param magazinesId 社刊id
     */
    public void setMagazinesId(Integer magazinesId) {
        this.magazinesId = magazinesId;
    }

    /**
     * 获取文章id
     *
     * @return articles_id - 文章id
     */
    public Integer getArticlesId() {
        return articlesId;
    }

    /**
     * 设置文章id
     *
     * @param articlesId 文章id
     */
    public void setArticlesId(Integer articlesId) {
        this.articlesId = articlesId;
    }

    /**
     * 获取列表名称
     *
     * @return magazine_title - 列表名称
     */
    public String getMagazineTitle() {
        return magazineTitle;
    }

    /**
     * 设置列表名称
     *
     * @param magazineTitle 列表名称
     */
    public void setMagazineTitle(String magazineTitle) {
        this.magazineTitle = magazineTitle;
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