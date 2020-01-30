package com.foresee.init;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.foresee.pojo.Articles;
import com.foresee.pojo.Carousels;
import com.foresee.pojo.CommunityFamily;
import com.foresee.pojo.Communitys;
import com.foresee.pojo.WechatUser;
import com.foresee.service.ArticlesService;
import com.foresee.service.CarouselsService;
import com.foresee.service.CommunityFamilyService;
import com.foresee.service.CommunitysService;
import com.foresee.service.WechatUserService;
import com.foresee.utils.QueryParameter;
import com.foresee.utils.RedisOperator;
import com.foresee.vo.CommunityListVo;
import com.foresee.vo.WechatUserVo;



@Component
public class ApplicationInit implements ApplicationRunner {
	protected static final String USER_REDIS_SESSION = "user-redis-session";
	@Autowired
	private ArticlesService articlesService;//文章首页
	
	@Autowired
	private CommunitysService communitysService;//社群首页
	
	@Autowired
	private CarouselsService carouselsService;//轮播图首页
	
	@Autowired
	private CommunityFamilyService communityFamilyService;
	@Autowired
	private WechatUserService  wechatUserService;
	
	@Autowired
	protected RedisOperator redis;
	//"==服务启动后，初始化数据操作=="
	 
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		System.out.println("服务启动后，初始化数据操作");
		InitUtils.articleList = articlesService.selectHome();
		QueryParameter carousels = new QueryParameter();
		carousels.addParamter("isDeleted", false);
		carousels.setSortField("order_num");
		carousels.setSortOrder("asc");
		InitUtils.carouselsList = carouselsService.pageList(carousels, 1, 5);
		
		
		//社群列表初始化：
		List<CommunityFamily> list =communityFamilyService.selectListHaveCommunity();
		for(CommunityFamily c:list) {
			QueryParameter qpc = new QueryParameter();
			qpc.addParamter("isDeleted", false);
			qpc.addParamter("communitySts", "1");
			qpc.addParamter("flowSts", "1");
			qpc.addParamter("communityType", c.getId());
			List<Communitys> community =communitysService.pageList(qpc,1,10);
			for(Communitys ct:community) {
				WechatUser user = new WechatUser();
				user.setCommunityid(ct.getId());
				ct.setUserNum(wechatUserService.selectCount(user));
				Articles article = new Articles();
				article.setCommunityId(ct.getId());
				article.setIsDraft("0");
				article.setIsDeleted(false);
				List<Articles> listA = articlesService.selectList(article);
				ct.setArticleNum(listA.size());
				int x=0;
				for(Articles a:listA) {
					x=x+a.getReadCount();
				}
				ct.setReadNum(x);
			}
			c.setList(community);
			
		}
		CommunityListVo listvo= new CommunityListVo();
		listvo.setList(list);
		listvo.setListAther(communitysService.selectHome());
		InitUtils.communityFamilyList = listvo;
		
		//设置redis
		List<WechatUserVo> listVo= wechatUserService.initRedisSession();
		for(WechatUserVo vo:listVo) {
			setRedisSession(vo.getOpenId(),vo);	
		}

	}
	
	private String setRedisSession(String openid,WechatUserVo user) {
		
		this.redis.set(USER_REDIS_SESSION+":"+openid, JSONObject.toJSON(user).toString(),1000*60*30);
		
		return USER_REDIS_SESSION+":"+openid;
	}

}
