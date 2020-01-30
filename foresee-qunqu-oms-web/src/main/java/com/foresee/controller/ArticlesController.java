package com.foresee.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.foresee.model.Articles;
import com.foresee.model.User;
import com.foresee.service.ArticlesService;
import com.foresee.util.ListCopyUtil;
import com.foresee.utils.DateUtils;
import com.foresee.utils.ResultUtils;
import com.foresee.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author wangruiheng
 * 文章管理
 */
@RestController
@RequestMapping("/articles")
@Validated
public class ArticlesController {
	@Resource ArticlesService articlesService;
	/**
	 * @return
	 * 添加文章
	 */
	@PostMapping("/save")
	public Object saveArticles(HttpServletRequest request, @Valid Articles articles) {
		User account =  (User) request.getSession().getAttribute("accounts");
		articles.setRecommendDate(DateUtils.getStringToDate(articles.getRecommendDateStr(), "yyyy-MM-dd HH:mm:ss")); 
		articles.setCreatedDate(new Date());
		articles.setCreatedBy(account.getTh1()+"");
		articles.setUserid(Integer.valueOf(account.getTh1()==null?"0":account.getTh1()));
		if(!StringUtil.isNotNull(articles.getCommunityId())){
			articles.setCommunityId(account.getChannelid());
		}
		if("1".equals(articles.getIsRecommend())){
			articles.setFlowSts("0");
		}else{
			articles.setFlowSts("4");
		}
		articles.setFlowDate(new Date());
		int num=articlesService.insertSelective(articles,account);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	
	/**
	 * @return
	 * 更新文章(推荐)
	 */
	@PostMapping("/update")
	public Object upArticles(HttpServletRequest request, @Valid Articles articles) {
		User account =  (User) request.getSession().getAttribute("accounts");
		articles.setRecommendDate(DateUtils.getStringToDate(articles.getRecommendDateStr(), "yyyy-MM-dd HH:mm:ss")); 
		articles.setUpdatedDate(new Date());
		articles.setUpdatedBy(account.getTh1()+"");
		int num=articlesService.updateByPrimaryKeySelective(articles,account);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	/**
	 * @return
	 * 更新文章(推荐)
	 */
	@PostMapping("/updateunRecommend")
	public Object upUnRecommendArticles(HttpServletRequest request, @Valid Articles articles) {
		User account =  (User) request.getSession().getAttribute("accounts");
		articles.setRecommendDate(DateUtils.getStringToDate(articles.getRecommendDateStr(), "yyyy-MM-dd HH:mm:ss"));
		articles.setUpdatedDate(new Date());
		articles.setUpdatedBy(account.getTh1()+"");
		int num=articlesService.updateByPrimaryKeySelective(articles,account);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 查询文章
	 */
	@PostMapping("/select")
	public Object selectArticles(HttpServletRequest request, @Valid int id) {
		Articles Articles = articlesService.selectByPrimaryKey(id);
		if(Articles!=null) {
			com.foresee.vo.Articles vo = new com.foresee.vo.Articles();
			BeanUtils.copyProperties(Articles, vo); 
			return ResultUtils.success(vo);
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 推荐发送审核
	 */
	@PostMapping("/apply")
	public Object applyArticles(HttpServletRequest request, @Valid int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		boolean applyArticles = articlesService.applyArticles(account,id);
		if(applyArticles) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	
	/**
	 * @return
	 * 查询文章列表
	 */
	@SuppressWarnings("unchecked")
	@PostMapping("/selectlist")
	public Object selectArticlesList(HttpServletRequest request,Articles articles) {
		 User accounts =  (User) request.getSession().getAttribute("accounts");
		 if(accounts.getChannelid()>0){
			articles.setCommunityId(accounts.getChannelid());
		 }
		 PageHelper.startPage(articles.getPage(), articles.getLimit());
		 List<Articles> list = articlesService.selectList(articles);
		 List<com.foresee.vo.Articles> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.Articles());
		 PageInfo<Articles> pageInfo = new PageInfo<>(list);
		 return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
	
	/**
	 * @return
	 * 更新文章
	 */
	@PostMapping("/updateisdeleted")
	public Object upArticlesIsDeleted(HttpServletRequest request,int id) {
		User account =  (User) request.getSession().getAttribute("accounts");
		Articles articles = articlesService.selectByPrimaryKey(id);
		articles.setUpdatedDate(new Date());
		articles.setUpdatedBy(account.getTh1()+"");
		articles.setIsDeleted(1);
		int num=articlesService.updateByPrimaryKeySelective(articles,account);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
}
