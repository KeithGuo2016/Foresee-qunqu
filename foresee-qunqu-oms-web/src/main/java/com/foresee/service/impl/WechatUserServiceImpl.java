package com.foresee.service.impl;

import java.util.Date;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.foresee.constant.UserRoleConfig;
import com.foresee.constant.WechatUserCofig;
import com.foresee.dao.AccountsMapper;
import com.foresee.dao.CommunitysMapper;
import com.foresee.dao.UserPowerMapper;
import com.foresee.dao.WechatUserMapper;
import com.foresee.exception.ResultException;
import com.foresee.model.Communitys;
import com.foresee.model.User;
import com.foresee.model.UserApply;
import com.foresee.model.UserNews;
import com.foresee.model.UserPower;
import com.foresee.model.WechatUser;
import com.foresee.model.WechatUserVo;
import com.foresee.service.UserNewsService;
import com.foresee.service.WechatUserService;
import com.foresee.util.MD5Util;
import com.foresee.util.RedisOperator;
import com.foresee.utils.AliyunSmsUtil;
import com.foresee.utils.DateUtils;
import com.foresee.utils.RandomUtil;
import com.foresee.utils.ResultCode;
import com.foresee.utils.StringUtil;
@Service
@Transactional(rollbackFor = Exception.class)
public class WechatUserServiceImpl implements WechatUserService{

	Lock lock = new ReentrantLock();
	
	@Autowired WechatUserMapper wechatUserDao;
	
	@Autowired AccountsMapper accountsDao;
	@Autowired CommunitysMapper communitysDao;
	
	@Autowired RedisOperator redisOperator;
	
	@Autowired UserPowerMapper userPowerDao;
	
	@Autowired CommunitysMapper communityDao;
	@Autowired
	AccountsMapper accountsMapper;
	@Autowired 
	UserNewsService  userNewsService; 
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return wechatUserDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(WechatUser record) {
		// TODO Auto-generated method stub
		return wechatUserDao.insert(record);
	}

	@Override
	public int insertSelective(WechatUser record) {
		// TODO Auto-generated method stub
		return wechatUserDao.insertSelective(record);
	}

	@Override
	public WechatUser selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return wechatUserDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(WechatUser record,User user,Integer id) {
		// TODO Auto-generated method stub
		setWechatUserRedis(record);
		setUserPower(user, id, record);
		return wechatUserDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(WechatUser record) {
		// TODO Auto-generated method stub
		setWechatUserRedis(record);
		return wechatUserDao.updateByPrimaryKey(record);
	}

	@Override
	public List<WechatUser> selectList(WechatUser record) {
		// TODO Auto-generated method stub
		return wechatUserDao.selectList(record);
	}

	
	/**
	 * 设置VIP
	 * @param user
	 * @param id
	 * @return
	 */
	@Override
	public boolean setVipWechatUser(User user, Integer id) {
		// TODO Auto-generated method stub
		WechatUser wechatUser = wechatUserDao.selectByPrimaryKey(id);
		/*if("1".equals(wechatUser.getIsCommunity())){
			throw new ResultException(ResultCode.IS_COMMUNITY_ERROR);
		}*/
		boolean rtn=false;
		try {
		wechatUser.setUpdatedDate(new Date());
		wechatUser.setUpdatedBy(user.getTh1()+"");
		wechatUser.setIsVip("1");
		wechatUser.setUserType(WechatUserCofig.VIP_WECHAT_USER);
		wechatUser.setUserType(getUserType(wechatUser));
		setWechatUserRedis(wechatUser);
		setUserPower(user, id, wechatUser);
		
		rtn= wechatUserDao.updateByPrimaryKeySelective(wechatUser)>0;
		UserNews userNews = new UserNews();
		userNews.setCreatedBy(user.getId()+"");
		userNews.setCreatedDate(new Date());
		userNews.setIsDeleted(0);
		userNews.setNewsContent("您在"+DateUtils.getDateToString(new Date(),"yyyy-MM-dd")+"日成为VIP用户");
		userNews.setNewsSts("0");
		userNews.setNewsTitle("成为VIP通知");
		userNews.setNewsType("1");
		userNews.setUserId(id);
		userNewsService.add(userNews);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ResultException(ResultCode.USERNEWS_UPDATE_ERROR);
		}
		return rtn;
	}
	
	
	
	
	/**
	 * 设置管理员
	 * @param user
	 * @param id
	 * @return
	 */
	@Override
	public boolean setAdminWechatUser(User user, Integer id) {
		// TODO Auto-generated method stub
		WechatUser wechatUser = wechatUserDao.selectByPrimaryKey(id);
		if("1".equals(wechatUser.getIsCommunity())){
			throw new ResultException(ResultCode.IS_COMMUNITY_ERROR);
		}
		boolean rtn=false;
		Communitys communitys = communitysDao.selectByPrimaryKey(wechatUser.getCommunityid());
		if(!"1".equals(communitys.getFlowSts())){
			throw new ResultException(ResultCode.COMMUNITYSID_CHECK_ERROR);
		}
		try {
		lock.lock();
		wechatUser.setUpdatedDate(new Date());
		wechatUser.setUpdatedBy(user.getTh1()+"");
		wechatUser.setIsAdmin("1");
		wechatUser.setUserType(WechatUserCofig.ADMIN_WECHAT_USER);
		wechatUser.setUserType(getUserType(wechatUser));
		setWechatUserRedis(wechatUser);
		setUserPower(user, id, wechatUser);
		rtn= wechatUserDao.updateByPrimaryKeySelective(wechatUser)>0;
		
		UserNews userNews = new UserNews();
		userNews.setCreatedBy(user.getId()+"");
		userNews.setCreatedDate(new Date());
		userNews.setIsDeleted(0);
		userNews.setNewsContent("您在"+DateUtils.getDateFromat(new Date(),"yyyy-MM-dd")+"日成为【"+communitys.getCommunityName()+"】社群管理员");
		userNews.setNewsSts("0");
		userNews.setNewsTitle("成为社群管理员通知");
		userNews.setNewsType("3");
		userNews.setUserId(id);
		userNewsService.add(userNews);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ResultException(ResultCode.USERNEWS_UPDATE_ERROR);
		}finally {
			lock.unlock();
		}
		return rtn;
	}
	/**
	 * 取消管理员
	 * @param user
	 * @param id
	 * @return
	 */
	@Override
	public boolean quxiaoAdminWechatUser(User user, Integer id) {
		
		WechatUser wechatUser = wechatUserDao.selectByPrimaryKey(id);
		if("1".equals(wechatUser.getIsCommunity())){
			throw new ResultException(ResultCode.IS_COMMUNITY_ERROR);
		}
		wechatUser.setUpdatedDate(new Date());
		wechatUser.setUpdatedBy(user.getTh1()+"");
		wechatUser.setIsAdmin("0");
		wechatUser.setUserType(WechatUserCofig.ORDINARY_WECHAT_USER);
		wechatUser.setUserType(getUserType(wechatUser));
		setWechatUserRedis(wechatUser);
		setUserPower(user, id, wechatUser);
		boolean returnValue = wechatUserDao.updateByPrimaryKeySelective(wechatUser)>0;
		User u =accountsMapper.selectByTh1(wechatUser.getId()+"");
		if(u != null) {
			accountsMapper.deleteByPrimaryKey(u.getId());
		}
		try {
		lock.lock();
		UserNews userNews = new UserNews();
		userNews.setCreatedBy(user.getId()+"");
		userNews.setCreatedDate(new Date());
		userNews.setIsDeleted(0);
		userNews.setNewsContent("您在"+DateUtils.getDateFromat(new Date(),"yyyy-MM-dd")+"日被取消【"+communitysDao.selectByPrimaryKey(wechatUser.getCommunityid()).getCommunityName()+"】社群的管理员职务");
		userNews.setNewsSts("0");
		userNews.setNewsTitle("取消社群管理员通知");
		userNews.setNewsType("3");
		userNews.setUserId(id);
		userNewsService.add(userNews);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ResultException(ResultCode.USERNEWS_UPDATE_ERROR);
		}finally {
			lock.unlock();
		}
		return returnValue;
	}

	/**
	 * 踢出社群
	 * @param user
	 * @param id
	 * @return
	 */
	@Override
	public boolean kickOutWechatUser(User user, Integer id) {
		// TODO Auto-generated method stub
		WechatUser wechatUser = wechatUserDao.selectByPrimaryKey(id);
		int communityid = wechatUser.getCommunityid();
		if("1".equals(wechatUser.getIsCommunity())){
			throw new ResultException(ResultCode.IS_COMMUNITY_ERROR);
		}
		wechatUser.setUpdatedDate(new Date());
		wechatUser.setUpdatedBy(user.getTh1()+"");
		if(!"1".equals(wechatUser.getIsVip())){
			wechatUser.setUserType(WechatUserCofig.TOURIST_WECHAT_USER);
		}
		wechatUser.setCommunityid(null);
		wechatUser.setIsAdmin("0");
		wechatUser.setUserType(getUserType(wechatUser));
		setWechatUserRedis(wechatUser);
		setUserPower(user, id, wechatUser);
		boolean returnValue = wechatUserDao.updateByPrimaryKey(wechatUser)>0;
		
		try {
			User u =accountsMapper.selectByTh1(wechatUser.getId()+"");
			if(u != null) {
				accountsMapper.deleteByPrimaryKey(u.getId());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			lock.lock();
			UserNews userNews = new UserNews();
			userNews.setCreatedBy(user.getId()+"");
			userNews.setCreatedDate(new Date());
			userNews.setIsDeleted(0);
			userNews.setNewsContent("您在"+DateUtils.getDateToString(new Date(),"yyyy-MM-dd")+"日被踢出【"+communitysDao.selectByPrimaryKey(communityid).getCommunityName()+"】社群");
			userNews.setNewsSts("0");
			userNews.setNewsTitle("踢出社群通知");
			userNews.setNewsType("2");
			userNews.setUserId(id);
			userNewsService.add(userNews);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				throw new ResultException(ResultCode.USERNEWS_UPDATE_ERROR);
			}finally {
				lock.unlock();
			}
		return returnValue;
	}

	/**
	 * 设置为社群普通用户
	 * @param user
	 * @param id
	 * @return
	 */
	@Override
	public boolean setOrdinaryWechatUser(User user,UserApply userApply) {
		// TODO Auto-generated method stub
		try {
			int id =userApply.getUserid();
			WechatUser wechatUser = wechatUserDao.selectByPrimaryKey(id);
			wechatUser.setUpdatedDate(new Date());
			wechatUser.setUpdatedBy(user.getTh1()+"");
			wechatUser.setCommunityid(userApply.getApplyTargetId());
			wechatUser.setUserType(WechatUserCofig.ORDINARY_WECHAT_USER);
			wechatUser.setUserType(getUserType(wechatUser));
			wechatUser.setIsAdmin("0");
			wechatUser.setIsCommunity("0");
			setWechatUserRedis(wechatUser);
			return wechatUserDao.updateByPrimaryKeySelective(wechatUser)>0;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	/**
	 * 社长、管理员、VIP、普通用户、游客
	 * @param wechatUser
	 * @return
	 */
	public String getUserType(WechatUser wechatUser){
		if("1".equals(wechatUser.getIsCommunity())){
			return WechatUserCofig.COMMUNITY_WECHAT_USER;
		}
		if("1".equals(wechatUser.getIsAdmin())){
			return WechatUserCofig.ADMIN_WECHAT_USER;
		}
		if("1".equals(wechatUser.getIsVip())){
			return WechatUserCofig.VIP_WECHAT_USER;
		}
		if(wechatUser.getCommunityid()!=null && wechatUser.getCommunityid()>0){
			return WechatUserCofig.ORDINARY_WECHAT_USER;
		}
		return WechatUserCofig.TOURIST_WECHAT_USER;
	}

	@Override
	public List<WechatUser> selectListCommunitysFollow(WechatUser record) {
		// TODO Auto-generated method stub
		return wechatUserDao.selectListCommunitysFollow(record);
	}

	@Override
	public boolean setUserCommunity(User user, Integer id) {
		// TODO Auto-generated method stub
		try {
			lock.lock();
			WechatUser wechatUser = wechatUserDao.selectByPrimaryKey(id);
			String phone = wechatUser.getPhone();
			String isCommunity = wechatUser.getIsCommunity();
			if("0".equals(isCommunity)){
				throw new ResultException(ResultCode.USER_COMMUNITY_ERROR);
			}
			if(!StringUtil.isNotNull(phone)){
				throw new ResultException(ResultCode.USER_PHONE_ERROR);
			}
			User query= new User();
			query.setUserphone(phone);
			List<User> list = accountsDao.selectListAccount(query);
			if(list.size()>0) {
				throw new ResultException(ResultCode.USER_PHONE_REPEAT_ERROR);
			}
			User account =  new User();
			account.setCreateid(user.getId());
			account.setCreatetime(new Date());
			String userpwd = RandomUtil.nextInt6();
			boolean sendSmsPassword = AliyunSmsUtil.sendSmsPassword(wechatUser.getPhone(), userpwd);
			String encrypt32 = "";
			if(sendSmsPassword){
				encrypt32 = MD5Util.encrypt32(userpwd);
			}else{
				encrypt32 = MD5Util.encrypt32(StringUtil.sub(phone,-6,phone.length()));
			}
			account.setTh1(id+"");
			account.setUserpwd(encrypt32);
			account.setUsername(wechatUser.getNickName());
			account.setUseraccount(phone);
			account.setUserphone(phone);
			account.setRoleid(UserRoleConfig.COMMUNITY_ROLE_USER);
			account.setIsenable(1);
			account.setChannelid(wechatUser.getCommunityid());
			accountsDao.insertSelective(account);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally {
			lock.unlock();
		}
	}

	@Override
	public boolean setUserAdmin(User user, Integer id) {
		try {
			lock.lock();
			WechatUser wechatUser = wechatUserDao.selectByPrimaryKey(id);
			String phone = wechatUser.getPhone();
			String isAdmin = wechatUser.getIsAdmin();
			if("0".equals(isAdmin)){
				throw new ResultException(ResultCode.USER_ADMIN_ERROR);
			}
			if(!StringUtil.isNotNull(phone)){
				throw new ResultException(ResultCode.USER_PHONE_ERROR);
			}
			User query= new User();
			query.setUserphone(phone);
			List<User> list = accountsDao.selectListAccount(query);
			if(list.size()>0) {
				throw new ResultException(ResultCode.USER_PHONE_REPEAT_ERROR);
			}
			User account =  new User();
			account.setCreateid(user.getId());
			account.setCreatetime(new Date());
			String userpwd = RandomUtil.nextInt6();
			String encrypt32 = MD5Util.encrypt32(userpwd);
			account.setTh1(id+"");
			account.setUserpwd(encrypt32);
			account.setUsername(wechatUser.getNickName());
			account.setUseraccount(wechatUser.getPhone());
			account.setUserphone(wechatUser.getPhone());
			account.setRoleid(UserRoleConfig.ADMIN_ROLE_USER);
			account.setIsenable(1);
			account.setChannelid(wechatUser.getCommunityid());
			accountsDao.insertSelective(account);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally {
			lock.unlock();
		}
	}

	/**
	 * 设置redis WechatUser信息
	 * @param record
	 */
	@Override
	public void setWechatUserRedis(WechatUser record) {
		// TODO Auto-generated method stub
		try {
			String key = WechatUserCofig.USER_REDIS_SESSION+":"+record.getOpenId();
			String str = redisOperator.get(key);
			WechatUserVo wechatUserVo = JSON.toJavaObject(JSON.parseObject(str), WechatUserVo.class);
			wechatUserVo.setIsVip(Integer.parseInt(record.getIsVip()));
			wechatUserVo.setIsAdmin(Integer.parseInt(record.getIsAdmin()));
			wechatUserVo.setIsDeleted(record.getIsDeleted()==1);
			wechatUserVo.setIsCommunity(Integer.parseInt(record.getIsCommunity()));
			if(record.getCommunityid()!=null){
				Communitys communitys = communityDao.selectByPrimaryKey(record.getCommunityid());
				if(communitys!=null){
					wechatUserVo.setCommunityid(record.getCommunityid());
					wechatUserVo.setCommunityIcon(communitys.getCommunityIcon());
					wechatUserVo.setCommunityName(communitys.getCommunityName());
				}
			}else{
				wechatUserVo.setCommunityid(0);
				wechatUserVo.setCommunityIcon("");
				wechatUserVo.setCommunityName("");
			}
			if(record.getIsAddCommunityAplly()!=null){
				wechatUserVo.setIsAddCommunityAplly(record.getIsAddCommunityAplly());
			}
			redisOperator.set(key, JSON.toJSONString(wechatUserVo),1000*60*30);
		} catch (Exception e) {
			// TODO: handle exception
			throw new ResultException(ResultCode.REDIS_UPDATE_ERROR);
		}
		
	}
	
	
	/**
	 * 设置UserPower信息
	 */
	public void setUserPower(User user, Integer id,WechatUser wechatUser) {
		// TODO Auto-generated method stub
		try {
			lock.lock();
			UserPower userPower = userPowerDao.getByUserId(id+"");
			if(userPower!=null){
				userPower.setIsVip(wechatUser.getIsVip());
				userPower.setIsAdmin(wechatUser.getIsAdmin());
				userPower.setIsCommunity(wechatUser.getIsCommunity());
				userPower.setUpdatedDate(new Date());
				userPower.setUpdatedBy(user.getId()+"");
				userPower.setIsDeleted(0);
				userPowerDao.update(userPower);
			}else{
				userPower = new UserPower();
				userPower.setUserid(id+"");
				userPower.setIsVip(wechatUser.getIsVip());
				userPower.setIsAdmin(wechatUser.getIsAdmin());
				userPower.setIsCommunity(wechatUser.getIsCommunity());
				userPower.setCreatedDate(new Date());
				userPower.setCreatedBy(user.getId()+"");
				userPower.setIsDeleted(0);
				userPowerDao.insert(userPower);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new ResultException(ResultCode.USERPOWER_UPDATE_ERROR);
		}finally {
			lock.unlock();
		}
		
	}
}