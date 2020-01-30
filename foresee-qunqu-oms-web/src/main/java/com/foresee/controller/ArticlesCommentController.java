package com.foresee.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.foresee.model.ArticlesComment;
import com.foresee.service.ArticlesCommentService;
import com.foresee.util.ListCopyUtil;
import com.foresee.utils.ResultUtils;
/**
 * @version 1.0
 * @author wrh
 * 用户评论模块
 */
@RestController
@RequestMapping("/articlescomment")
@Validated
public class ArticlesCommentController{
    
    @Autowired
    private ArticlesCommentService articlesCommentService;
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectlist")
	public Object list(HttpServletRequest request,ArticlesComment articlesComment) {
		PageHelper.startPage(articlesComment.getPage(), articlesComment.getLimit());
		List<ArticlesComment> list = articlesCommentService.listPage(articlesComment);
		List<com.foresee.vo.ArticlesComment> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.ArticlesComment());
		PageInfo<ArticlesComment> pageInfo = new PageInfo<ArticlesComment>(list);
		return ResultUtils.successlist(listvo,pageInfo.getTotal());
	}
    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectarticlelist")
	public Object articleList(HttpServletRequest request,ArticlesComment articlesComment) {
		PageHelper.startPage(articlesComment.getPage(), articlesComment.getLimit());
		List<ArticlesComment> list = articlesCommentService.listPage(articlesComment);
		List<com.foresee.vo.ArticlesComment> listvo = ListCopyUtil.CopyList(list, new com.foresee.vo.ArticlesComment());
		ArticlesComment sq = new ArticlesComment();
		List<com.foresee.vo.ArticlesComment> listvo2 = new ArrayList<com.foresee.vo.ArticlesComment>();
		List<ArticlesComment> listPage =  new ArrayList<ArticlesComment>();
		List<Map<String, Object>> list2= new ArrayList<Map<String,Object>>();
		Map<String, Object>  map = new HashMap<String, Object>();
		for(com.foresee.vo.ArticlesComment a:listvo){
			map = new HashMap<String, Object>();
			listvo2 = new ArrayList<com.foresee.vo.ArticlesComment>();
			listPage =  new ArrayList<ArticlesComment>();
			sq = new ArticlesComment();
			sq.setIspid(1);
			sq.setPid(a.getId());
			sq.setOrderBy(1);
			listPage = articlesCommentService.listPage(sq);
			listvo2 = ListCopyUtil.CopyList(listPage, new com.foresee.vo.ArticlesComment());
			map.put("one", a);
			map.put("list", listvo2);
			list2.add(map);
		}
		PageInfo<ArticlesComment> pageInfo = new PageInfo<ArticlesComment>(list);
		return ResultUtils.successlist(list2,pageInfo.getTotal());
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Object doAdd(HttpServletRequest request,ArticlesComment articlesComment) {
		int num = articlesCommentService.add(articlesComment);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object doEdit(HttpServletRequest request,ArticlesComment articlesComment) {
		int num = articlesCommentService.update(articlesComment);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}
	

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Object delete(HttpServletRequest request,int id) {
		int num = articlesCommentService.delete(id);
		if(num>0) {
			return ResultUtils.success("");
		}
		return ResultUtils.fail("");
	}

}
