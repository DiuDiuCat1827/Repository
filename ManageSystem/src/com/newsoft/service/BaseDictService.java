package com.newsoft.service;

import java.util.List;

import com.newsoft.domain.BaseDict;

public interface BaseDictService {

	List<BaseDict> getListByTypeCode(String dict_type_code);

}
