package com.jdc.hns.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.hns.dao.ProductDao;

@TestMethodOrder(OrderAnnotation.class)
public class ProductTest {

	private static ProductDao dao;

	@BeforeAll
	static void init() {
		dao = new ProductDao();
	}

	// @ParameterizedTest
	@DisplayName("1.select by product id")
	@ValueSource(ints = { 1, 2, 3 })
	@Order(1)
	void test1(int id) {
		var p = dao.findById(id);
		assertNotNull(p);
	}

	@ParameterizedTest
	@DisplayName("2.select by product name like")
	// @ValueSource(strings = "C")
	@CsvSource(value = { "A", "B", "C", "D", "E" })

	@Order(2)
	void test2(String name) {
		var list = dao.findByNameLike(name);
		// assertEquals(4, list.size());
		System.out.println(list.size());
	}

	// @Test
	@DisplayName("3.select by dynamic search")
	@Order(3)
	void test3() {
		var list = dao.findBy(0, 0, 10000, "y", "d");
		assertEquals(1, list.size());
	}

}