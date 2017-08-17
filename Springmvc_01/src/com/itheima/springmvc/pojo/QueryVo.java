package com.itheima.springmvc.pojo;

import java.util.List;

public class QueryVo {

	
	//商品
	private Items items;

	Integer[] ids;
	
	List<Items> itemList;
	
	
	public List<Items> getItemList() {
		return itemList;
	}

	public void setItemList(List<Items> itemList) {
		this.itemList = itemList;
	}

	public QueryVo() {
	}

	public Integer[] getIds() {
		return ids;
	}

	public void setIds(Integer[] ids) {
		this.ids = ids;
	}

	public Items getItems() {
		return items;
	}

	public void setItems(Items items) {
		this.items = items;
	}
	
}
