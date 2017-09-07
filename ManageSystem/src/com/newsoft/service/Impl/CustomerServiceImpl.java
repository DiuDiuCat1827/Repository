package com.newsoft.service.Impl;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.newsoft.dao.CustomerDao;
import com.newsoft.domain.Customer;
import com.newsoft.service.CustomerService;
import com.newsoft.utils.PageBean;

@Transactional
public class CustomerServiceImpl implements CustomerService {

	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void updateOrSave(Customer customer) {
		// TODO Auto-generated method stub
		customerDao.updateOrSave(customer);
	}
	
	@Override
	public PageBean getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		// 1.查总记录数
		Integer totalCount = customerDao.getTotalCount(detachedCriteria);
		// 2.创建PageBean对象
		PageBean pageBean = new PageBean(currentPage, pageSize,totalCount);
		// 3.调用dao查询分页列表数据
		List<Customer> list = customerDao.getPageList(detachedCriteria, pageBean.getStart(), pageBean.getPageSize());
		// 4.列表数据放入pageBean中并返回
		pageBean.setList(list);
		return pageBean;
	}

	@Override 
	public Customer getById(Long cust_id) {
		// TODO Auto-generated method stub 
		return customerDao.getById(cust_id);
	}

	@Override
	public List<Object[]> getIndustryCount() {
		// TODO Auto-generated method stub
		return customerDao.getIndustryCount();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		customerDao.delete(id);
	}

	@Override
	public List<Object[]> getResourceCount() {
		// TODO Auto-generated method stub
		return customerDao.getResourceCount();
	}



}
