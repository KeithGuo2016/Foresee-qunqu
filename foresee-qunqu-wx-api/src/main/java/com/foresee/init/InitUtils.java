package com.foresee.init;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.foresee.pojo.Carousels;
import com.foresee.pojo.CommunityFamily;
import com.foresee.pojo.Communitys;
import com.foresee.vo.ArticleVo;
import com.foresee.vo.CommunityListVo;



public class InitUtils {
	//首页文章列表。
	public static List<ArticleVo> articleList = new ArrayList<ArticleVo>();
	public static Map<String, List<ArticleVo> > articleMap = new HashMap<String, List<ArticleVo> >();
	public static List<Communitys> communityList = new ArrayList<Communitys>();
	public static List<Carousels> carouselsList = new ArrayList<Carousels>();
	
	public static CommunityListVo communityFamilyList = new CommunityListVo();


}
