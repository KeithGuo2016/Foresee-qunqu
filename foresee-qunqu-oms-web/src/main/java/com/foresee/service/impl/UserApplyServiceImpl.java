package com.foresee.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.dao.UserApplyMapper;
import com.foresee.model.User;
import com.foresee.model.UserApply;
import com.foresee.service.ArticlesService;
import com.foresee.service.ContributesService;
import com.foresee.service.MagazinesService;
import com.foresee.service.UserApplyService;
import com.foresee.service.UserNewsService;
import com.foresee.service.WechatUserService;
@Service
@Transactional(rollbackFor = Exception.class)
public class UserApplyServiceImpl implements UserApplyService{

	@Autowired UserApplyMapper userApplyDao;
	
	@Autowired WechatUserService wechatUserService;
	
	@Autowired ContributesService contributesService;
	
	@Autowired MagazinesService magazinesService;
	
	@Autowired ArticlesService articlesService;
	
	@Autowired UserNewsService  userNewsService; 
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userApplyDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(UserApply record) {
		// TODO Auto-generated method stub
		return userApplyDao.insert(record);
	}

	@Override
	public int insertSelective(UserApply record) {
		// TODO Auto-generated method stub
		return userApplyDao.insertSelective(record);
	}

	@Override
	public UserApply selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return userApplyDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(UserApply record) {
		// TODO Auto-generated method stub
		return userApplyDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(UserApply record) {
		// TODO Auto-generated method stub
		return userApplyDao.updateByPrimaryKey(record);
	}

	@Override
	public List<UserApply> selectList(UserApply record) {
		// TODO Auto-generated method stub
		return userApplyDao.selectList(record);
	}
	@Override
	public List<UserApply> selectListJoinCommunity(UserApply record) {
		// TODO Auto-generated method stub
		return userApplyDao.selectListJoinCommunity(record);
	}

	@Override
	public boolean adoptUserApply(User user,Integer id,String adoptType,String newsContent) {
		// TODO Auto-generated method stub
		UserApply userApply = userApplyDao.selectByPrimaryKey(id);
		int applyTargetId = Integer.parseInt(userApply.getApplyTargetId()==null?"0":userApply.getApplyTargetId()+"");
		int userid = userApply.getUserid();
		int adoptTypeInt = Integer.parseInt(adoptType);
		boolean fa = false;
		int applyType = Integer.parseInt(userApply.getApplyType());
		//1 VIP申请、2入群申请，3管理员申请、4创建社群申请、5征稿申请、6、社刊申请、7推荐发文申请
		if(applyType == 1){
			if(adoptTypeInt == 1){
				fa = wechatUserService.setVipWechatUser(user, userid);
			}else{
				fa = true;
			}
		}else if(applyType == 2){
			if(adoptTypeInt == 1){
				fa = wechatUserService.setOrdinaryWechatUser(user, userApply);
			}else{
				fa = true;
			}
		}else if(applyType == 3){
			if(adoptTypeInt == 1){
				fa = wechatUserService.setAdminWechatUser(user, userid);
			}else{
				fa = true;
			}
		}else if(applyType == 4){
			//线下处理
			fa = true;
		}else if(applyType == 5){
			fa = contributesService.applyContributes(user, applyTargetId, adoptType);
		}else if(applyType == 6){
			fa = magazinesService.applyMagazines(user, applyTargetId, adoptType);
		}else if(applyType == 7){
			fa = articlesService.applyArticlesRecommend(user, applyTargetId, adoptType);
		}
		if(fa){
			userApply.setUpdatedDate(new Date());
			userApply.setUpdatedBy(user.getTh1()+"");
			userApply.setApplySts(adoptType);
			userNewsService.insert(user, userid, applyType, adoptTypeInt, newsContent, applyTargetId);
			fa = userApplyDao.updateByPrimaryKeySelective(userApply)>0;
		}
		return fa;
	}
	
}