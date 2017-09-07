package com.newsoft.dao.Impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.newsoft.dao.BaseDictDao;
import com.newsoft.domain.BaseDict;

public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {

	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		// TODO Auto-generated method stub
		// 创建离线查询对象
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(BaseDict.class);
		// 封装条件
		detachedCriteria.add(Restrictions.eq("dict_type_code", dict_type_code));
		// 执行查询
		List<BaseDict> list = (List<BaseDict>) getHibernateTemplate().findByCriteria(detachedCriteria);
		return list;
	}
    //写完最底层的xxxDaoImpl后，去Spring配置文件中配置
	//然后去Struts中配置Action
}
