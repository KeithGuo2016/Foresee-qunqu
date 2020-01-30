package com.foresee.service;

import java.util.List;

import com.foresee.model.Magazines;
import com.foresee.model.User;

public interface MagazinesService {
    int deleteByPrimaryKey(Integer id);

    int insert(Magazines record);

    int insertSelective(Magazines record);

    Magazines selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Magazines record);

    int updateByPrimaryKey(Magazines record);
    
    List<Magazines> selectList(Magazines record);
    
    boolean applyMagazines(User user, int id, String flowSts);
    
    int updateByPrimaryKeySelectiveIsDeleted(Magazines record);
    
    boolean applyMagazines(int id,User user); 
    
    void ckeckMagazinesUser(User user, int id);
}