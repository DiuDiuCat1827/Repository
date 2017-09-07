package com.newsoft.service.Impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.newsoft.dao.CustomerDao;
import com.newsoft.dao.LinkmanDao;
import com.newsoft.domain.Customer;
import com.newsoft.domain.LinkMan;
import com.newsoft.service.LinkmanService;
import com.newsoft.utils.PageBean;

public class LinkmanServiceImpl implements LinkmanService{

	private LinkmanDao linkmanDao;
	public void setLinkmanDao(LinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}
	
	@Override
	public void updateOrSave(LinkMan linkMan) {
		// TODO Auto-generated method stub
		linkmanDao.updateOrSave(linkMan);
	}

	@Override
	public PageBean getPageBean(DetachedCriteria detachedCriteria, Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		// 1.查总记录数
				Integer totalCount = linkmanDao.getTotalCount(detachedCriteria);
				// 2.创建PageBean对象
				PageBean pageBean = new PageBean(currentPage, pageSize,totalCount);
				// 3.调用dao查询分页列表数据
				List<LinkMan> list = linkmanDao.getPageList(detachedCriteria, pageBean.getStart(), pageBean.getPageSize());
				// 4.列表数据放入pageBean中并返回
				pageBean.setList(list);
				return pageBean;
	}

	@Override
	public LinkMan getById(Long lkm_id) {
		// TODO Auto-generated method stub
		return linkmanDao.getById(lkm_id);
	}

	@Override
	public void delete(Long lkm_id) {
		// TODO Auto-generated method stub
		linkmanDao.delete(lkm_id);
	}

}
