package com.newsoft.dao;

import java.util.List;

import com.newsoft.domain.BaseDict;

public interface BaseDictDao {

	//根据类型获得数据字典列表
	List<BaseDict> getListByTypeCode(String dict_type_code);

}
