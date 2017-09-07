package com.newsoft.dao.Impl;

import com.newsoft.dao.SaleVisitDao;
import com.newsoft.domain.SaleVisit;

public class SaleVisitDaoImpl extends BaseDaoImpl<SaleVisit> implements SaleVisitDao {

	@Override
	public void save(SaleVisit saleVisit) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(saleVisit);
	}
}
