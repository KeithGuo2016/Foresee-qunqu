package com.foresee.baseService.impl;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.foresee.baseService.BasicsSvc;
import com.foresee.utils.MyMapper;
import com.foresee.utils.QueryCondition;
import com.foresee.utils.QueryParameter;
import com.foresee.utils.StringUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;

@Service("basicsSvc")
@Lazy(true)
@Transactional
public class BasicsSvcImpl<T extends Serializable> implements BasicsSvc<T> {
	@Autowired
	protected MyMapper<T> mapper;

	private Class<T> entityClass;

	public Class<T> getEntityClass() {
		return entityClass;
	}

	public void setEntityClass(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/*
	 * 初始化 获取T的Class
	 */
	public BasicsSvcImpl() {
		Type type = this.getClass().getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			// 参数化类型
			ParameterizedType type1 = (ParameterizedType) type;
			// 返回表示此类型实际类型参数的 Type 对象的数组
			Type[] actualTypeArguments = type1.getActualTypeArguments();
			setEntityClass((Class<T>) actualTypeArguments[0]);
		} else {
			this.entityClass = (Class<T>) type;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // 设置事务 在同一个事务中执行
	public void insert(T t) {
		mapper.insertSelective(t);
		

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // 设置事务 在同一个事务中执行
	public void insertList(List<T> recordList) {
		mapper.insertList(recordList);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // 设置事务 在同一个事务中执行
	public void update(T t) {
		mapper.updateByPrimaryKeySelective(t);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // 设置事务 在同一个事务中执行
	public void delete(String id) {
		mapper.deleteByPrimaryKey(id);

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // 设置事务 在同一个事务中执行
	public void deleteList(String ids) {
		Example ex = new Example(this.getEntityClass());
		Example.Criteria criteria = ex.createCriteria();
		if (!StringUtil.notBlank(ids)) {
			List<String> lis = Arrays.asList(ids.split(","));
			criteria.andIn("id", lis);
		}
		mapper.deleteByExample(ex);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED) // 设置事务 在同一个事务中执行
	public void delete(T t) {
		mapper.delete(t);

	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public T selectByid(String id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public List<T> selectList(T t) {

		return mapper.select(t);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public List<T> selectAll() {
		return mapper.selectAll();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public T selectOne(T t) {

		return mapper.selectOne(t);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public int selectCount(T t) {
		return mapper.selectCount(t);
	}
	
	public int selectCount(QueryParameter qp) {
		Example ex = new Example(this.getEntityClass());
		this.setCriteriaValue(qp, ex);
		return mapper.selectByExample(ex).size();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public List<T> pageList(QueryParameter qp, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		Example ex = new Example(this.getEntityClass());
		this.setCriteriaValue(qp, ex);
		return mapper.selectByExample(ex);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public PageInfo<T> pageInfoList(QueryParameter qp, int page, int pageSize) {
		PageHelper.startPage(page, pageSize);
		Example ex = new Example(this.getEntityClass());
		this.setCriteriaValue(qp, ex);
		List<T> list =mapper.selectByExample(ex);
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		return pageInfo;
	}
	
	@Transactional(propagation = Propagation.SUPPORTS) // 设置事务
	public List<T> selectList(QueryParameter qp) {
		
		Example ex = new Example(this.getEntityClass());
		this.setCriteriaValue(qp, ex);
		return mapper.selectByExample(ex);
	}
	/**
	 * 设置查询条件和排序
	 * 
	 * @param qp
	 * @param ex
	 */
	protected void setCriteriaValue(QueryParameter qp, Example ex) {
		if (qp == null)
			return;
		

		// 设置排序
		if (StringUtil.notBlank(qp.getSortField())) {
			ex.setOrderByClause(qp.getSortField()+" "+qp.getSortOrder());
		}
		//设置条件拼接
		if(qp.getParams()==null||qp.getParams().size()==0)
			return;
		Example.Criteria criteria = ex.createCriteria();
		//循环参数属性
		for (String param : qp.getParamNames()) {
			//条件拼接值
			QueryCondition qc = qp.getCondition(param);
			//条件传入值：参数
			if (StringUtil.notBlank(qp.getParam(param))) {
				//=条件拼接
				if(qc==null) {
					criteria.andEqualTo(param, qp.getParam(param));
					continue;
				}
				switch (qc) {
				case equal:
					criteria.andEqualTo(param, qp.getParam(param));
					break;
				case not_equal:
					criteria.andNotEqualTo(param, qp.getParam(param));
					break;
				case in:
					criteria.andIn(param, Arrays.asList(qp.getParam(param).toString().split(",")));
					break;
				case not_in:
					criteria.andNotIn(param, Arrays.asList(qp.getParam(param).toString().split(",")));
					break;
				case large:
					criteria.andGreaterThan(param, qp.getParam(param));
					break;
				case large_equal:
					criteria.andGreaterThanOrEqualTo(param, qp.getParam(param));
					break;
				case small:
					criteria.andLessThan(param, qp.getParam(param));
					break;
				case small_equal:
					criteria.andLessThanOrEqualTo(param, qp.getParam(param));
					break;
				case like_anywhere:
					criteria.andLike(param, "%"+qp.getParam(param)+"%");
					break;
				case like_start:
					criteria.andLike(param, qp.getParam(param)+"%");
					break;
				case like_end:
					criteria.andLike(param, "%"+qp.getParam(param));
					break;
				case between:
					List<String> list = Arrays.asList(qp.getParam(param).toString().split(","));
					criteria.andBetween(param, list.get(0), list.get(1));
					break;
				case not_between:
					List<String> lis = Arrays.asList(qp.getParam(param).toString().split(","));
					criteria.andNotBetween(param, lis.get(0), lis.get(1));
					break;
				}
				
			}else {
				if(qc==null) 
					continue;
				switch (qc) {
				case is_null:
					criteria.andIsNull(param);
					break;
				case not_null:
					criteria.andIsNotNull(param);
					break;
				}
			}
				
		}
	}

}
