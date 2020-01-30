package com.foresee.controller.view;


import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foresee.advice.AccountAuthenticationAnnotation;

/**
 * 
 * @author wrh
 * 评论管理视图
 */
@Controller
@RequestMapping("/view/artcomment")
public class ViewArticlesCommentController {
	//社群标签管理主页
	@RequestMapping("/artcommentmanage.html")
	@AccountAuthenticationAnnotation
	public Object tagManage(HttpServletRequest request,ModelMap model) {
		return "page/sqmanage/pinglun/main";
	}
}
