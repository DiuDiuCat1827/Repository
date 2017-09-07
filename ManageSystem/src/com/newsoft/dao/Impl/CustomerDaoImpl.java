package com.newsoft.dao.Impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.newsoft.dao.CustomerDao;
import com.newsoft.domain.Customer;

public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao {

	@Override
	@SuppressWarnings("all")
	public List<Object[]> getIndustryCount() {
		//原生sql查询
		
		List<Object[]> list=getHibernateTemplate().execute(new HibernateCallback<List>() {

		    //原生sql语句 矩形编辑快捷键 shift+alt+a
			String sql=" select bd.dict_item_name,  count(*) total   "+
					   " from customer c,base_dict bd                "+
					   " where c.cust_industry=bd.dict_id            "+
					   " GROUP BY c.cust_industry                    ";
			@Override
			public List doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				//执行sql语句并返回一个query对象
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return list;
	}

	@Override
	@SuppressWarnings("all")
	public List<Object[]> getResourceCount() {
		//原生sql查询
		
		List<Object[]> list=getHibernateTemplate().execute(new HibernateCallback<List>() {

		    //原生sql语句 矩形编辑快捷键 shift+alt+a
			String sql=" select bd.dict_item_name,  count(*) total   "+
					   " from customer c,base_dict bd                "+
					   " where c.cust_source=bd.dict_id            "+
					   " GROUP BY c.cust_source                    ";
			@Override
			public List doInHibernate(Session session) throws HibernateException {
				// TODO Auto-generated method stub
				//执行sql语句并返回一个query对象
				SQLQuery query = session.createSQLQuery(sql);
				return query.list();
			}
		});
		return list;
	}
	
}
