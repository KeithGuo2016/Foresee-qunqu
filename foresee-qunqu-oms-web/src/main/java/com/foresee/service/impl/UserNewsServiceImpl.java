package com.foresee.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.service.UserNewsService;
import com.foresee.utils.DateUtils;
import com.foresee.utils.ResultCode;
import com.foresee.utils.StringUtil;
import com.foresee.dao.ArticlesMapper;
import com.foresee.dao.CommunitysMapper;
import com.foresee.dao.ContributesMapper;
import com.foresee.dao.MagazinesMapper;
import com.foresee.dao.UserNewsMapper;
import com.foresee.exception.ResultException;
import com.foresee.model.User;
import com.foresee.model.UserNews;

/**
 * @version 1.0
 * @author wrh
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserNewsServiceImpl implements UserNewsService {
	
    @Autowired private UserNewsMapper userNewsMapper;
    
    @Autowired CommunitysMapper communitysDao;
    
    @Autowired ContributesMapper contributesDao;
    
    @Autowired ArticlesMapper articlesDao;
    
    @Autowired MagazinesMapper magazinesDao;
    
    Lock lock = new ReentrantLock();
    
	public int add(UserNews userNews) {
		if(null == userNews){
			return 0;
		}
        int rows = userNewsMapper.insert(userNews);
        return rows;
	}

    
    public int update(UserNews userNews) {
		if(null == userNews){
			return 0;
		}
        int rows = userNewsMapper.update(userNews);
        return rows;
    }
    
    
    public int delete(Integer id) {
		if(null == id){
			return 0;
		}
        int rows = userNewsMapper.deleteById(id);
        return rows;
    }
    
    
    public UserNews getById(Integer id) {
		if(null == id){
			return null;
		}
		UserNews userNews = userNewsMapper.getById(id);
        return userNews;
    }
    
	public List<UserNews> listPage(UserNews userNews){
		List<UserNews> lists = userNewsMapper.listPage(userNews);
		return lists;
	}


	@Override
	public void insert(User user,int userid,int type,int isCheck,String newsContent,int targetId) {
		// TODO Auto-generated method stub
		//isCheck  1通过  2拒绝
		try {
			lock.lock();
			UserNews userNews = new UserNews();
			userNews.setCreatedBy(user.getId()+"");
			userNews.setCreatedDate(new Date());
			userNews.setIsDeleted(0);
			userNews.setNewsContent(newsContent);
			if(type==1) {
				userNews.setNewsTitle("成为VIP申请");
			}
			else if(type==2) {
				userNews.setNewsTitle("加入【"+communitysDao.selectByPrimaryKey(targetId).getCommunityName()+"】社群审核");
			}
			else if(type==3) {
				userNews.setNewsTitle("成为【"+communitysDao.selectByPrimaryKey(targetId).getCommunityName()+"】社群管理员审核");
			}
			else if(type==4) {
				userNews.setNewsTitle("【"+communitysDao.selectByPrimaryKey(targetId).getCommunityName()+"】"+"社群审核");
			}
			else if(type==5) {
				userNews.setNewsTitle("【"+contributesDao.selectByPrimaryKey(targetId).getContributeTitle()+"】"+"征稿审核");
			}
			else if(type==6) {
				userNews.setNewsTitle("【"+magazinesDao.selectByPrimaryKey(targetId).getMagazineTitle()+"】"+"社刊审核");
			}
			else if(type==7) {
				userNews.setNewsTitle("【"+articlesDao.selectByPrimaryKey(targetId).getArticleTitle()+"】"+"推荐发文审核");
			}
			if(isCheck==1){
				userNews.setNewsContent("审核通过");
			}else{
				if(StringUtil.isNotNull(newsContent)){
					userNews.setNewsContent(newsContent);
				}else{
					userNews.setNewsContent("审核拒绝");
				}
			}
			if(type == 7){
				if(isCheck==1){
					userNews.setNewsContent("初步审核通过，请耐心等待复审");
				}else if(isCheck==3){
					userNews.setNewsContent("复审审核通过，请耐心等待终审");
				}else if(isCheck==4){
					userNews.setNewsContent("终审审核通过，将于"+DateUtils.getDateToString(DateUtils.getStringToDate(newsContent, "yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd")+"日首页推荐");
				}
			}
			userNews.setNewsSts("0");
			userNews.setNewsType(type+"");
			userNews.setUserId(userid);
			userNewsMapper.insert(userNews);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ResultException(ResultCode.USERNEWS_UPDATE_ERROR);
		}finally {
			lock.unlock();
		}
		
	}
}
