package com.newsoft.service.Impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.newsoft.dao.SaleVisitDao;
import com.newsoft.domain.Customer;
import com.newsoft.domain.SaleVisit;
import com.newsoft.service.SaleVisitService;
import com.newsoft.utils.PageBean;

public class SaleVisitServiceImpl implements SaleVisitService {

	private SaleVisitDao saleVisitDao;

	@Override
	public void save(SaleVisit saleVisit) {
		// TODO Auto-generated method stub
		saleVisitDao.save(saleVisit);
	}

	@Override
	public PageBean getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
		// 1 调用Dao查询总记录数
		Integer totalCount = saleVisitDao.getTotalCount(detachedCriteria);
		// 2 创建PageBean对象
		PageBean pageBean = new PageBean(currentPage, pageSize, totalCount);
		// 3.调用dao查询分页列表数据
		List<SaleVisit> list = saleVisitDao.getPageList(detachedCriteria, pageBean.getStart(), pageBean.getPageSize());
		// 4.列表数据放入pageBean中并返回
		pageBean.setList(list);
		return pageBean;
	}

	public SaleVisitDao getSaleVisitDao() {
		return saleVisitDao;
	}

	public void setSaleVisitDao(SaleVisitDao saleVisitDao) {
		this.saleVisitDao = saleVisitDao;
	}

	@Override
	public void update(SaleVisit saleVisit) {
		// TODO Auto-generated method stub
       saleVisitDao.update(saleVisit);
	}

	@Override
	public SaleVisit getById(String saleVisit_id) {
		// TODO Auto-generated method stub
		return saleVisitDao.getById(saleVisit_id);
	}

	@Override
	public void updateOrSave(SaleVisit saleVisit) {
		// TODO Auto-generated method stub
		saleVisitDao.updateOrSave(saleVisit);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		saleVisitDao.delete(id);
	}

}
