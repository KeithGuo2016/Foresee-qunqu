package com.foresee.task;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.foresee.init.InitUtils;
import com.foresee.pojo.Articles;
import com.foresee.pojo.CommunityFamily;
import com.foresee.pojo.Communitys;
import com.foresee.pojo.UserApply;
import com.foresee.pojo.UserNews;
import com.foresee.pojo.WechatUser;
import com.foresee.service.ArticlesService;
import com.foresee.service.CarouselsService;
import com.foresee.service.CommunityFamilyService;
import com.foresee.service.CommunitysService;
import com.foresee.service.UserApplyService;
import com.foresee.service.UserNewsService;
import com.foresee.service.WechatUserService;
import com.foresee.utils.DateUtils;
import com.foresee.utils.QueryCondition;
import com.foresee.utils.QueryParameter;
import com.foresee.utils.RedisOperator;
import com.foresee.vo.CommunityListVo;
import com.foresee.vo.WechatUserVo;

/**
 * 小程序首页定时任务，将首页数据每天存入缓存中，方便读取
 * @author DELL
 *
 */
@Configuration      //1.主要用于标记配置类，兼备Component的效果。
@EnableScheduling   //2 开启定时任务
public class WxHomeTask {
	protected static final String USER_REDIS_SESSION = "user-redis-session";
	@Autowired
	private ArticlesService articlesService;//文章首页
	@Autowired
	private CommunityFamilyService communityFamilyService;
	@Autowired
	private WechatUserService  wechatUserService;
	@Autowired
	private CommunitysService communitysService;//社群首页
	@Autowired
	private CarouselsService carouselsService;//轮播图首页
	@Autowired
	private UserApplyService userApplyService;
	@Autowired
	private UserNewsService userNewsService;
	@Autowired
	protected RedisOperator redis;
	//3.添加定时任务 每5分钟秒的时候执行一次
    @Scheduled(cron = "0 0/5 * * * ?")
	//@Scheduled(cron = "0/5 * * * * ?")
    private void configureTasks() {
    	 System.out.println("5分钟更新一次: " + LocalDateTime.now());
    	 //文章
    	 InitUtils.articleList = articlesService.selectHome();
    	 //轮播图
    	 QueryParameter carousels = new QueryParameter();
 		carousels.addParamter("isDeleted", false);
 		carousels.setSortField("order_num");
 		carousels.setSortOrder("asc");
 		InitUtils.carouselsList = carouselsService.pageList(carousels, 1, 5);
    	 //社群
 		System.out.println("5分钟更新社群 " );
 		List<CommunityFamily> list =communityFamilyService.selectListHaveCommunity();
 		for(CommunityFamily c:list) {
 			QueryParameter qpc = new QueryParameter();
 			qpc.addParamter("isDeleted", false);
 			qpc.addParamter("communitySts", "1");
 			qpc.addParamter("flowSts", "1");
 			qpc.addParamter("communityType", c.getId());
 			List<Communitys> community =communitysService.selectList(qpc);
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
		System.out.println("更新结束 " );
		//设置redis
			List<WechatUserVo> listVo= wechatUserService.initRedisSession();
			for(WechatUserVo vo:listVo) {
				setRedisSession(vo.getOpenId(),vo);	
			}
    }
    @Scheduled(cron = "1 0 0 * * ?")
    private void articleTasks() {
    	 Date d=new Date();   
		 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
		//前一天的日期
    	String recommendDate= df.format(new Date(d.getTime() - 1 * 24 * 60 * 60 * 1000));
    	Date dates =DateUtils.getDate(recommendDate);
    	InitUtils.articleMap.put(recommendDate, articlesService.selectHomeByDate(dates));
    	
    	//前二天的日期
    	recommendDate= df.format(new Date(d.getTime() - 2 * 24 * 60 * 60 * 1000));
    	dates =DateUtils.getDate(recommendDate);
    	InitUtils.articleMap.put(recommendDate, articlesService.selectHomeByDate(dates));
    	
    	//前三天的日期
    	recommendDate= df.format(new Date(d.getTime() - 3 * 24 * 60 * 60 * 1000));
    	dates =DateUtils.getDate(recommendDate);
    	InitUtils.articleMap.put(recommendDate, articlesService.selectHomeByDate(dates));
    	//前四天的日期
    	recommendDate= df.format(new Date(d.getTime() - 4 * 24 * 60 * 60 * 1000));
    	dates =DateUtils.getDate(recommendDate);
    	InitUtils.articleMap.put(recommendDate, articlesService.selectHomeByDate(dates));
    	//前5天的日期
    	recommendDate= df.format(new Date(d.getTime() - 5 * 24 * 60 * 60 * 1000));
    	dates =DateUtils.getDate(recommendDate);
    	InitUtils.articleMap.put(recommendDate, articlesService.selectHomeByDate(dates));
    	//前6天的日期
    	recommendDate= df.format(new Date(d.getTime() - 6 * 24 * 60 * 60 * 1000));
    	dates =DateUtils.getDate(recommendDate);
    	InitUtils.articleMap.put(recommendDate, articlesService.selectHomeByDate(dates));
    	
    }
   //定时任务3：每天晚上10点把前一天提交的且未审核的入群申请全部拒绝掉
    @Scheduled(cron = "0 0 22 * * ?")
    private void AddCommunityTasks() {
    	
    	 Date d=new Date();   
		 SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");  
		//前一天的日期
		 String recommendDate= df.format(new Date(d.getTime() - 1 * 24 * 60 * 60 * 1000));
		 Date dates =DateUtils.getDate(recommendDate);
		 QueryParameter qpc = new QueryParameter();
		 qpc.addParamter("isDeleted", false);
		 qpc.addParamter("applyType", "2");
		 qpc.addParamter("applySts", "0");
		 qpc.addParamter("createdDate", dates, QueryCondition.small);
		/* UserApply u =new UserApply();
		 u.setIsDeleted(false);
		 u.setApplyType("2");
		 u.setApplySts("0");*/
		 
		 List<UserApply> list = userApplyService.selectList(qpc);
		 for(UserApply apply:list) {
			 apply.setApplySts("2");
			 userApplyService.update(apply);
			 UserNews userNews= new UserNews();
				userNews.setCreatedBy("");
				userNews.setCreatedDate(new Date());
				userNews.setIsDeleted(false);
				userNews.setNewsContent("申请处理超时，自动退回，请重新申请");
				userNews.setNewsTitle("加入【"+communitysService.selectByid(apply.getApplyTargetId()+"").getCommunityName()+"】社群审核");
				userNews.setNewsSts("0");
				userNews.setNewsType("");
				userNews.setUserid(0);
				userNewsService.insert(userNews);
		 }
		 
    }
    
    //定时任务4：每天晚上12点把前一天提交的且未审核的入群申请全部拒绝掉
    @Scheduled(cron = "0 0 0 * * ?")
    private void reidsTasks() {
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
