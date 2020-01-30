package com.foresee.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.constant.UserRoleConfig;
import com.foresee.dao.ContributesMapper;
import com.foresee.dao.UserApplyMapper;
import com.foresee.exception.ResultException;
import com.foresee.model.Contributes;
import com.foresee.model.ContributesWithBLOBs;
import com.foresee.model.User;
import com.foresee.model.UserApply;
import com.foresee.service.ContributesService;
import com.foresee.service.UserNewsService;
import com.foresee.utils.ResultCode;
@Service
@Transactional(rollbackFor = Exception.class)
public class ContributesServiceImpl implements ContributesService{

	@Autowired ContributesMapper contributesDao;
	@Autowired UserApplyMapper userApplyDao;
	@Autowired UserNewsService userNewsService;
	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return contributesDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ContributesWithBLOBs record) {
		// TODO Auto-generated method stub
		return contributesDao.insert(record);
	}

	@Override
	public int insertSelective(ContributesWithBLOBs record,User account) {
		// TODO Auto-generated method stub
		int roleid = account.getRoleid();
		int r = -1;
		if(UserRoleConfig.COMMUNITY_ROLE_USER != roleid && UserRoleConfig.ADMIN_ROLE_USER != roleid && UserRoleConfig.SUPER_ADMINISTRATOR_USER != roleid){
			throw new ResultException(ResultCode.CONTRIBUTES_ADMIN_TYPE_ERROR);
		}
		//征稿类型（1收费 0免费）
		if("1".equals(record.getContributeType())){
			//社群管理员：只可以发起群内、无赏金的征稿需求
			if(UserRoleConfig.COMMUNITY_ROLE_USER != roleid && UserRoleConfig.SUPER_ADMINISTRATOR_USER != roleid){
				throw new ResultException(ResultCode.CONTRIBUTES_TYPE_1_ERROR);
			}
		}
		if("0".equals(record.getContributeRange()) && UserRoleConfig.SUPER_ADMINISTRATOR_USER != roleid) {
			record.setFlowSts("0");
			record.setUpdatedDate(new Date());
			record.setUpdatedBy(account.getTh1()+"");
			r =contributesDao.insertSelective(record);
			ContributesWithBLOBs srecord = new ContributesWithBLOBs();
			srecord.setContributeTitle(record.getContributeTitle());
			srecord.setContributeIcon(record.getContributeIcon());
			srecord.setFlowSts("0");
			List<ContributesWithBLOBs> list =contributesDao.selectList(record);
			if(list.size()>0) {
				int id = list.get(0).getId();
				UserApply userApply = new UserApply();
				userApply.setUserid(Integer.parseInt(account.getTh1()));
				userApply.setApplyType("5");
				userApply.setApplySts("0");
				userApply.setPhone(account.getUserphone());
				userApply.setCreatedDate(new Date());
				userApply.setCreatedBy(account.getTh1());
				userApply.setApplyTargetId(id);
				userApplyDao.insertSelective(userApply);
			}else {
				throw new ResultException(ResultCode.ERROR);
			}
			
		}else{
			record.setFlowSts("1");
			record.setUpdatedDate(new Date());
			record.setUpdatedBy(account.getTh1()+"");
			r =contributesDao.insertSelective(record);
			
			
		}
		
		return r;
	}

	@Override
	public ContributesWithBLOBs selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return contributesDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ContributesWithBLOBs record) {
		// TODO Auto-generated method stub
		return contributesDao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(ContributesWithBLOBs record) {
		// TODO Auto-generated method stub
		return contributesDao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(Contributes record) {
		// TODO Auto-generated method stub
		return contributesDao.updateByPrimaryKey(record);
	}

	@Override
	public List<ContributesWithBLOBs> selectList(ContributesWithBLOBs record) {
		// TODO Auto-generated method stub
		return contributesDao.selectList(record);
	}

	/*
	 * 审核征稿
	 * (non-Javadoc)
	 * @see com.foresee.service.ContributesService#applyContributes(com.foresee.model.User, int, java.lang.String)
	 */
	@Override
	public boolean applyContributes(User user, int id, String flowSts) {
		// TODO Auto-generated method stub
		ContributesWithBLOBs contributesWithBLOBs = contributesDao.selectByPrimaryKey(id);
		contributesWithBLOBs.setFlowSts(flowSts);
		contributesWithBLOBs.setUpdatedDate(new Date());
		contributesWithBLOBs.setUpdatedBy(user.getTh1()+"");
		return contributesDao.updateByPrimaryKeySelective(contributesWithBLOBs)>0;
	}

	/**
	 * 发起审核
	 */
	@Override
	public boolean applyContributes(Integer id,User account) {
		// TODO Auto-generated method stub
		try {
			ContributesWithBLOBs contributesWithBLOBs = contributesDao.selectByPrimaryKey(id);
			if("1".equals(contributesWithBLOBs.getFlowSts()) || "3".equals(contributesWithBLOBs.getFlowSts()) || contributesWithBLOBs.getIsDeleted()==1 ){
				throw new ResultException(ResultCode.CONTRIBUTES_APPLY_ERROR);
			}
			contributesWithBLOBs.setFlowSts("3");
			contributesWithBLOBs.setUpdatedDate(new Date());
			contributesWithBLOBs.setUpdatedBy(account.getTh1()+"");
			contributesDao.updateByPrimaryKeySelective(contributesWithBLOBs);
			
			UserApply userApply = new UserApply();
			userApply.setUserid(account.getId());
			userApply.setApplyType("5");
			userApply.setApplySts("0");
			userApply.setPhone(account.getUserphone());
			userApply.setCreatedDate(new Date());
			userApply.setCreatedBy(account.getTh1()+"");
			userApply.setApplyTargetId(id);
			userApplyDao.insertSelective(userApply);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
			
		}
	}
}