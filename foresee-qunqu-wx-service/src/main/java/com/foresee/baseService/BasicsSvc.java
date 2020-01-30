package com.foresee.baseService;

import java.io.Serializable;
import java.util.List;

import com.foresee.utils.QueryParameter;
import com.github.pagehelper.PageInfo;



public interface BasicsSvc<T extends Serializable>  {
	/**
	 * 新增
	 * @param u
	 */
	void insert(T u);
	/**
	 * 批量新增
	 * @param recordList
	 */
	void insertList(List<T> recordList);
	/**
	 * 修改数据
	 * @param u
	 */
	void update(T u);
	/**
	 * 根据ID删除数据
	 * @param id
	 */
	void delete(String id);
	/**
	 * 删除对象
	 * @param t
	 */
	void delete(T t);
	/**
	 * 批量删除
	 * @param ids 主键组，“,”分割
	 */
	void deleteList(String ids);
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return
	 */
	T selectByid(String id);
	/**
	 * 根据对象查询数据列表
	 * @param t
	 * @return
	 */
	List<T> selectList(T t);
	/**
	 * 根据查询参数分页查询，用于移动设备的数据返回 没有总条数和总页码
	 * @param qp 查询参数对象
	 * @param page 页码
	 * @param pageSize 每页条数
	 * @return
	 */
	List<T> pageList(QueryParameter qp,int page, int pageSize);
	/**
	 * 根据查询参数分页查询，数据返回 有总条数和总页码 PageInfo对象可以获取
	 * @param qp
	 * @param page
	 * @param pageSize
	 * @return
	 */
	PageInfo<T> pageInfoList(QueryParameter qp,int page, int pageSize);
	/**
	 * 根据对象查询所有
	 * @param t
	 * @return
	 */
	List<T> selectAll();
	
	
	
	/**
	 * 根据对象参数中的值 查询，返回一个对象
	 * @param t
	 * @return
	 */
	T selectOne(T t);
	/**
	 * 查询数量
	 * @param t
	 * @return
	 */
	int selectCount(T t);
	/**
	 * 根据查询条件查询 数据库条数
	 * @param qp
	 * @return
	 */
	int selectCount(QueryParameter qp);
	/**
	 * 根据查询条件查询
	 * @param qp
	 * @return
	 */
	List<T> selectList(QueryParameter qp);

}
