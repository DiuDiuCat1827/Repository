package com.newsoft.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import com.newsoft.domain.Customer;
import com.newsoft.utils.PageBean;

public interface CustomerService {

	public void updateOrSave(Customer customer);

	public PageBean getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

	public Customer getById(Long cust_id);

	// 获得产业查询
	public List<Object[]> getIndustryCount();

	// 获得来源查询
	public List<Object[]> getResourceCount();

	// 删除
	public void delete(Long id);
}
