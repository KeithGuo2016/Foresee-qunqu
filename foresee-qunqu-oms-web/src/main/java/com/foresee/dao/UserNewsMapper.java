package com.foresee.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.foresee.model.UserNews;
/**
 * @version 1.0
 * @author wrh
 */
@Repository
public interface UserNewsMapper {
    
    UserNews getById(Integer id);

    List<UserNews> listPage(UserNews userNews);
    
    int insert(UserNews userNews);
    
    int update(UserNews userNews);
    
    int deleteById(Integer id);
}