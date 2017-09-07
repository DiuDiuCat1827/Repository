package com.newsoft.service.Impl;

import java.util.List;

import com.newsoft.dao.BaseDictDao;
import com.newsoft.domain.BaseDict;
import com.newsoft.service.BaseDictService;

public class BaseDictServiceImpl implements BaseDictService {

	private BaseDictDao baseDictDao;
	public void setBaseDictDao(BaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> getListByTypeCode(String dict_type_code) {
		// TODO Auto-generated method stub
		return baseDictDao.getListByTypeCode(dict_type_code);
	}

}
