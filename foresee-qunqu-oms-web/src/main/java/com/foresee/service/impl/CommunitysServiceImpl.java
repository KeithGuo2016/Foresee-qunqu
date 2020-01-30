package com.foresee.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.constant.WechatUserCofig;
import com.foresee.dao.CommunitysMapper;
import com.foresee.dao.WechatUserMapper;
import com.foresee.exception.ResultException;
import com.foresee.model.Communitys;
import com.foresee.model.User;
import com.foresee.model.WechatUser;
import com.foresee.service.CommunitysService;
import com.foresee.service.UserNewsService;
import com.foresee.service.WechatUserService;
import com.foresee.utils.ResultCode;
@Service
@Transactional(rollbackFor = Exception.class)
public class CommunitysServiceImpl implements CommunitysService{

	Lock lock = new ReentrantLock();
	
	@Autowired CommunitysMapper communitysDao;
	@Autowired WechatUserMapper wechatUserDao;
	@Autowired WechatUserService wechatUserService;
	@Autowired UserNewsService userNewsService;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return communitysDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Communitys record) {
		// TODO Auto-generated method stub
		return communitysDao.insert(record);
	}

	@Override
	public int insertSelective(Communitys record) {
		// TODO Auto-generated method stub
		int n=0;
		try {
			lock.lock();
			WechatUser wechatUser = wechatUserDao.selectByPrimaryKey(record.getAdminId());
			if(!"1".equals(wechatUser.getIsCommunity())){
				Communitys communitys = new Communitys();
				communitys.setNotFlowSts("2");
				communitys.setAdminId(record.getAdminId());
				List<Communitys> selectList = communitysDao.selectList(communitys);
				if(selectList.size()>0){
					throw new ResultException(ResultCode.IS_COMMUNITY_ERROR);
				}
				record.setFlowSts("0");
				n = communitysDao.insertSelective(record);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally {
			lock.unlock();
		}
		return n;
		
	}

	@Override
	public Communitys selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return communitysDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Communitys record) {
		// TODO Auto-generated method stub
		return communitysDao.updateByPrimaryKeySelective(record);
	}
	/**
	 * 删除社群，并且修改社群内所有用户信息
	 */
	@Override
	public int updateIsDeleted(Communitys record) {
		int returnNum = communitysDao.updateByPrimaryKeySelective(record);
		WechatUser wechatUser = new WechatUser();
		wechatUser.setCommunityid(record.getId());
		List<WechatUser> list=wechatUserDao.selectList(wechatUser);
		for(WechatUser u:list){
			u.setCommunityid(null);
			u.setIsAdmin("0");
			u.setIsCommunity("0");
			u.setUserType(WechatUserCofig.TOURIST_WECHAT_USER);
			wechatUserDao.updateByPrimaryKey(u);
			wechatUserService.setWechatUserRedis(u);
		}
		return returnNum;
	}

	@Override
	public int updateByPrimaryKey(Communitys record) {
		// TODO Auto-generated method stub
		int n=0;
		try {
			lock.lock();
			Communitys communitys = communitysDao.selectByPrimaryKey(record.getId());
			
			//没有更换社长情况
			if(communitys.getAdminId().equals(record.getAdminId())){
				n = communitysDao.updateByPrimaryKeySelective(record);
			}else{
				
				//设置最新的用户为社长
				WechatUser wechatUser = wechatUserDao.selectByPrimaryKey(record.getAdminId());
				
				//改用户身份不是社长状态
				if(!"1".equals(wechatUser.getIsCommunity())){
					
					
					if(communitys.getAdminId()!=null && communitys.getAdminId()>0){
						//之前社长设置为普通用户
						WechatUser wechatUserOid = wechatUserDao.selectByPrimaryKey(communitys.getAdminId());
						if(wechatUserOid!=null){
							wechatUserOid.setUpdatedDate(new Date());
							wechatUserOid.setUpdatedBy(record.getUpdatedBy());
							wechatUserOid.setIsCommunity("0");
							wechatUserOid.setIsAdmin("0");
							wechatUserOid.setUserType(WechatUserCofig.ORDINARY_WECHAT_USER);
							wechatUserDao.updateByPrimaryKey(wechatUserOid);
							wechatUserService.setWechatUserRedis(wechatUserOid);
						}
						
					}
					
					wechatUser.setUpdatedDate(new Date());
					wechatUser.setUpdatedBy(record.getUpdatedBy());
					wechatUser.setIsCommunity("1");
					wechatUser.setIsAdmin("0");
					wechatUser.setCommunityid(record.getId());
					wechatUser.setUserType(WechatUserCofig.COMMUNITY_WECHAT_USER);
					wechatUserDao.updateByPrimaryKeySelective(wechatUser);
					n = communitysDao.updateByPrimaryKeySelective(record);
					wechatUserService.setWechatUserRedis(wechatUser);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally {
			lock.unlock();
		}
		return n;
	}

	@Override
	public List<Communitys> selectList(Communitys record) {
		// TODO Auto-generated method stub
		return communitysDao.selectList(record);
	}

	@Override
	public int adoptCommunitys(User user,int id) {
		// TODO Auto-generated method stub
		try {
			lock.lock();
			Communitys record = communitysDao.selectByPrimaryKey(id);
			record.setFlowSts("1");
			record.setUpdatedDate(new Date());
			record.setUpdatedBy(user.getTh1()+"");
			communitysDao.updateByPrimaryKeySelective(record);
			WechatUser wechatUser = wechatUserDao.selectByPrimaryKey(record.getAdminId());
			wechatUser.setUpdatedDate(new Date());
			wechatUser.setUpdatedBy(record.getUpdatedBy());
			wechatUser.setIsCommunity("1");
			wechatUser.setIsAdmin("0");
			wechatUser.setCommunityid(record.getId());
			wechatUser.setUserType(WechatUserCofig.COMMUNITY_WECHAT_USER);
			wechatUser.setIsAddCommunityAplly(0);
			wechatUserService.setWechatUserRedis(wechatUser);
			userNewsService.insert(user, Integer.parseInt(record.getCreatedBy()), 4, 1, "", id);
			return wechatUserDao.updateByPrimaryKeySelective(wechatUser);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
		return 0;
	}

	@Override
	public int refuseCommunitys(User user,int id,String newsContent) {
		// TODO Auto-generated method stub
		Communitys record = communitysDao.selectByPrimaryKey(id);
		record.setFlowSts("2");
		record.setUpdatedDate(new Date());
		record.setUpdatedBy(user.getTh1()+"");
		WechatUser wechatUser = wechatUserDao.selectByPrimaryKey(record.getAdminId());
		wechatUser.setIsAddCommunityAplly(0);
		wechatUserService.setWechatUserRedis(wechatUser);
		userNewsService.insert(user, Integer.parseInt(record.getCreatedBy()), 4, 2, newsContent, id);
		return communitysDao.updateByPrimaryKeySelective(record);
	}
}