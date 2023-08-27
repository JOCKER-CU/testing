package com.jdc.hns.dao;

public interface UpdateServices<T> {

	int insert(T t);
	int update(T t);
	int delete(int id);
	
}