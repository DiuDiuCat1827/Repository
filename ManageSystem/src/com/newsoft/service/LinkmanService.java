package com.newsoft.service;

import org.hibernate.criterion.DetachedCriteria;

import com.newsoft.domain.Customer;
import com.newsoft.domain.LinkMan;
import com.newsoft.utils.PageBean;

public interface LinkmanService {

	public void updateOrSave(LinkMan linkMan);

	public PageBean getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize);

	public LinkMan getById(Long lkm_id);
	
	public void delete(Long lkm_id);

}
