package com.foresee.service;

import java.util.List;

import com.foresee.model.User;
import com.foresee.model.UserNews;

/**
 * @version 1.0
 * @author wrh
 */
public interface UserNewsService {
	
	public int add(UserNews userNews);

	public int update(UserNews userNews);
    
	public int delete(Integer id);

	public UserNews getById(Integer id);

	public List<UserNews> listPage(UserNews userNews);
	
	public void insert(User user,int userid,int type,int isCheck,String newsContent,int targetId);
	

}
