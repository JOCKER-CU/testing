package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

@TestMethodOrder(OrderAnnotation.class)
public class ManipulateEntityTest extends EntityFactory {

//	@Test
	void insertCategory() {
		var cat = new Category("Snacks");
		var em = emf.createEntityManager();

		em.getTransaction().begin();
		em.persist(cat);
		em.getTransaction().commit();
	}

	@ParameterizedTest
	@CsvSource("SunFlower seeds, 2000, Snacks")
	void insert(String pName, int price, String cName) {
		// to be transient state
		var product = new Product(pName, price);
		var category = new Category(cName);
		product.setCategory(category);

		var em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(category);
		em.persist(product);
		em.getTransaction().commit();
	}

	@ParameterizedTest
	@ValueSource(ints = 1)
	void test2(int id) {
		var em = emf.createEntityManager();
		// var product = em.find(Product.class, id);
		var product = em.getReference(Product.class, id);
		assertEquals("Lemons", product.getName());
	}

}
