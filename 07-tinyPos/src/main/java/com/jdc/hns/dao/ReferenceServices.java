package com.jdc.hns.dao;

import java.util.List;

public interface ReferenceServices<T> {

	T findById(int id);
	List<T> findByNameLike(String name);
}
