package com.foresee.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.constant.UserRoleConfig;
import com.foresee.dao.AccountsMapper;
import com.foresee.dao.MagazineArticlesMapper;
import com.foresee.dao.MagazinesMapper;
import com.foresee.dao.UserApplyMapper;
import com.foresee.exception.ResultException;
import com.foresee.model.MagazineArticles;
import com.foresee.model.Magazines;
import com.foresee.model.User;
import com.foresee.model.UserApply;
import com.foresee.service.MagazinesService;
import com.foresee.service.UserNewsService;
import com.foresee.utils.ResultCode;
import com.foresee.utils.StringUtil;
@Service
@Transactional(rollbackFor = Exception.class)
public class MagazinesServiceImpl implements MagazinesService{

	@Autowired MagazinesMapper magazinesDao;
	@Autowired UserApplyMapper userApplyDao;
	@Autowired MagazineArticlesMapper magazineArticlesDao;
	@Autowired AccountsMapper accountsDao;
	@Autowired UserNewsService userNewsService;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return magazinesDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Magazines record) {
		// TODO Auto-generated method stub
		return magazinesDao.insert(record);
	}

	@Override
	public int insertSelective(Magazines record) {
		// TODO Auto-generated method stub
		int n = magazinesDao.insertSelective(record);
		if(n>0){
			String articlesIds = record.getArticlesIds();
			if(StringUtil.isNotNull(articlesIds)){
				String magazineTitles = record.getMagazineTitles();
				List<MagazineArticles> list = new ArrayList<MagazineArticles>();
				MagazineArticles magazineArticles = new MagazineArticles();
				String[] split = articlesIds.split(",");
				String[] split2 = magazineTitles.split("~");
				for(int i=0;i<split.length;i++){
					magazineArticles = new MagazineArticles();
					if(StringUtil.isNotNull(split[i])){
						magazineArticles.setArticlesId(Integer.parseInt(split[i]));
						magazineArticles.setMagazinesId(record.getId());
						magazineArticles.setCreatedDate(new Date());
						magazineArticles.setCreatedBy(record.getCreatedBy());
						magazineArticles.setMagazineTitle(split2[i]);
						magazineArticles.setIsDeleted(0);
						list.add(magazineArticles);
					}
				}
				if(list.size()>0){
					magazineArticlesDao.insertSelectiveList(list);
				}
			}else{
				throw new ResultException(ResultCode.MAGAZINES_ARTICLES_ERROR);
			}
		}
		return n;
	}

	@Override
	public Magazines selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return magazinesDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Magazines record) {
		// TODO Auto-generated method stub
		String articlesIds = record.getArticlesIds();
		String magazineTitles = record.getMagazineTitles();
		magazinesDao.updateByPrimaryKeySelective(record);
		record = magazinesDao.selectByPrimaryKey(record.getId());
		magazineArticlesDao.deleteByMagazinesId(record.getId());
		List<MagazineArticles> list = new ArrayList<MagazineArticles>();
		MagazineArticles magazineArticles = new MagazineArticles();
		String[] split = articlesIds.split(",");
		String[] split2 = magazineTitles.split("~");
		for(int i=0;i<split.length;i++){
			magazineArticles = new MagazineArticles();
			if(StringUtil.isNotNull(split[i])){
				magazineArticles.setArticlesId(Integer.parseInt(split[i]));
				magazineArticles.setMagazinesId(record.getId());
				magazineArticles.setCreatedDate(new Date());
				magazineArticles.setCreatedBy(record.getCreatedBy());
				magazineArticles.setMagazineTitle(split2[i]);
				list.add(magazineArticles);
			}
		}
		if(list.size()>0){
			magazineArticlesDao.insertSelectiveList(list);
		}
		return 1;
	}

	@Override
	public int updateByPrimaryKey(Magazines record) {
		// TODO Auto-generated method stub
		return magazinesDao.updateByPrimaryKey(record);
	}
	@Override
	public int updateByPrimaryKeySelectiveIsDeleted(Magazines record) {
		// TODO Auto-generated method stub
		return magazinesDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Magazines> selectList(Magazines record) {
		// TODO Auto-generated method stub
		return magazinesDao.selectList(record);
	}

	/*
	 *  审核社刊
	 * (non-Javadoc)
	 * @see com.foresee.service.MagazinesService#applyMagazines(com.foresee.model.User, int, java.lang.String)
	 */
	@Override
	public boolean applyMagazines(User user, int id, String flowSts) {
		// TODO Auto-generated method stub
		Magazines magazines = magazinesDao.selectByPrimaryKey(id);
		magazines.setFlowSts(flowSts);
		magazines.setUpdatedDate(new Date());
		magazines.setUpdatedBy(user.getTh1()+"");
		return magazinesDao.updateByPrimaryKeySelective(magazines)>0;
	}

	@Override
	public boolean applyMagazines(int id, User user) {
		// TODO Auto-generated method stub
		Magazines magazines = magazinesDao.selectByPrimaryKey(id);
		if("1".equals(magazines.getFlowSts()) || "3".equals(magazines.getFlowSts()) || magazines.getIsDeleted()==1 ){
			throw new ResultException(ResultCode.MAGAZINES_APPLY_ERROR);
		}
		
		UserApply userApply = new UserApply();
		userApply.setUserid(user.getId());
		userApply.setApplyType("6");
		userApply.setApplySts("0");
		userApply.setPhone(user.getUserphone());
		userApply.setCreatedDate(new Date());
		userApply.setCreatedBy(user.getTh1()+"");
		userApply.setApplyTargetId(id);
		userApplyDao.insertSelective(userApply);
		magazines.setFlowSts("3");
		magazines.setUpdatedDate(new Date());
		magazines.setUpdatedBy(user.getTh1()+"");
		return magazinesDao.updateByPrimaryKeySelective(magazines)>0;
	}

	@Override
	public void ckeckMagazinesUser(User user, int id) {
		// TODO Auto-generated method stub
		Magazines magazines = magazinesDao.selectByPrimaryKey(id);
		User magazinesUser = accountsDao.selectByTh1(magazines.getCreatedBy());//社刊的创建者
		if((int)user.getId()!= (int)magazinesUser.getId()){
			int magazinesRoleid = magazinesUser.getRoleid();
			int roleid = user.getRoleid();
			if(roleid != UserRoleConfig.SUPER_ADMINISTRATOR_USER){
				if(magazinesRoleid == roleid){
					throw new ResultException(ResultCode.PERMISSION_ERROR);
				}
				//超级管理员 - 直接拒绝修改
				if(magazinesRoleid == UserRoleConfig.SUPER_ADMINISTRATOR_USER){
					throw new ResultException(ResultCode.PERMISSION_ERROR);
				}
				
				//社长  只有超级管理员 可以修改
				if(magazinesRoleid == UserRoleConfig.COMMUNITY_ROLE_USER){
					if(roleid != UserRoleConfig.SUPER_ADMINISTRATOR_USER){
						throw new ResultException(ResultCode.PERMISSION_ERROR);
					}
				}
			}
		}
	}
}