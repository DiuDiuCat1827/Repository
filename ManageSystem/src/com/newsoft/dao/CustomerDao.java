package com.newsoft.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.newsoft.domain.Customer;

public interface CustomerDao extends BaseDao<Customer>{
     //按照行业查询客户数量
	List<Object[]> getIndustryCount();

	List<Object[]> getResourceCount();
}
