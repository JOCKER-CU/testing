package com.jdc.mkt.test;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

public class FlushAndRefresh  extends EntityFactory{

	@Test
	void testPersist() {
		Category c = new Category("test");
		Product p = new Product("Test_product", 200);
		c.addProduct(p);
		
		var em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(c);
		em.flush();
		em.refresh(p);
		em.getTransaction().commit();
	}
}
