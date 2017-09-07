package com.newsoft.dao.Impl;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.newsoft.dao.BaseDao;
import com.newsoft.domain.Customer;

public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {

	private Class clazz;
	
	public BaseDaoImpl() {
		// TODO Auto-generated constructor stub
		//获得带有泛型类型的父类,但是调用这个方法的都是继承BaseImpl的子类，所以获得的是BaseDaoImpl
	  ParameterizedType ptClass = (ParameterizedType) this.getClass().getGenericSuperclass();
	  //获得这个类的泛型，返回一个泛型数组，[0]表示当泛型只有一个的时候
	  clazz = (Class) ptClass.getActualTypeArguments()[0];
	}

	@Override
	public void delete(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void delete(Serializable id) {
		// TODO Auto-generated method stub
		T t=this.getById(id);
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().update(t);
	}

	@Override
	public T getById(Serializable id) {
		// TODO Auto-generated method stub
		return (T) this.getHibernateTemplate().get(clazz, id);
	}

	@Override
	public Integer getTotalCount(DetachedCriteria detachedCriteria) {
		// TODO Auto-generated method stub
		detachedCriteria.setProjection(Projections.rowCount());
		// 获得条数
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		// 清空之前的聚合函数,以便于不影响后面的使用
		detachedCriteria.setProjection(null);
		// 判断条数合法性
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		} else {
			return null;
		}
	}

	@Override
	public List<T> getPageList(DetachedCriteria detachedCriteria, Integer start, Integer pageSize) {
		// TODO Auto-generated method stub
		List<T> list =  
				(List<T>) this.getHibernateTemplate().findByCriteria(detachedCriteria, start,pageSize);
			return list;
	}

	@Override
	public void updateOrSave(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(t);
	}


}
