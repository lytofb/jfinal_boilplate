package com.nnit.bean;

import java.util.HashMap;
import java.util.TreeMap;

public class DatatableServerSideBean {

	private TreeMap<Integer, HashMap<String, Object>> columns = new TreeMap<Integer, HashMap<String,Object>>();
	private TreeMap<Integer, HashMap<String, Object>> order = new TreeMap<Integer, HashMap<String,Object>>();
	private TreeMap<String, Object> search = new TreeMap<String, Object>();
	public TreeMap<Integer, HashMap<String, Object>> getColumns() {
		return columns;
	}
	public void setColumns(TreeMap<Integer, HashMap<String, Object>> columns) {
		this.columns = columns;
	}
	public TreeMap<Integer, HashMap<String, Object>> getOrder() {
		return order;
	}
	public void setOrder(TreeMap<Integer, HashMap<String, Object>> order) {
		this.order = order;
	}
	public TreeMap<String, Object> getSearch() {
		return search;
	}
	public void setSearch(TreeMap<String, Object> search) {
		this.search = search;
	}

	
}
