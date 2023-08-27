package com.jdc.hns.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.hns.dao.CategoryDao;
import com.jdc.hns.dto.Category;

public class CategoryTest {
	
	private static CategoryDao dao;
	
	
	@BeforeAll
	static void init() {
		dao = new CategoryDao();
		
	}
	
	
	
	@ParameterizedTest
	@DisplayName("1. insert query")
	@ValueSource(strings = "cate")
	@Order(1)
	void test(String name) {
		var cat = new Category();
		cat.setName(name);
		int res = dao.insert(cat);
		assertEquals(17, res);
		
	}
	
	//@ParameterizedTest
	@DisplayName("2. update query")
	@ValueSource(strings = "access")
	@Order(2)
	void test2(String name) {
		var cat = new Category();
		cat.setName(name);
		int res = dao.update(cat);
		assertEquals(1, res);
		
	}
	
	//@ParameterizedTest
	@DisplayName("3. delete query")
	@ValueSource(ints = 8)
	@Order(3)
	void test3(int id) {
		int res = dao.delete(id);
		assertEquals(1, res);
		
	}
	
	//@ParameterizedTest
	@DisplayName("4. delete query")
	@ValueSource(ints = 8)
	@Order(3)
	void test4(int id) {
		int res = dao.delete(id);
		assertEquals(1, res);
		
	}

}
