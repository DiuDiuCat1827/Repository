package com.newsoft.service;

import org.hibernate.criterion.DetachedCriteria;

import com.newsoft.dao.SaleVisitDao;
import com.newsoft.domain.Customer;
import com.newsoft.domain.SaleVisit;
import com.newsoft.utils.PageBean;

public interface SaleVisitService {
	public void updateOrSave(SaleVisit saleVisit);
	//客户拜访记录的分页列表
	public PageBean getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);
	//保存
	public void save(SaleVisit saleVisit);
	//查询
	public SaleVisit getById(String saleVisit_id);
    //更新
	public void update(SaleVisit t);
	//删除
	public void delete(String id);
}
