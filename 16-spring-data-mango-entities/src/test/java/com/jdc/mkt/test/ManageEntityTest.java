package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Product;

public class ManageEntityTest extends EntityFactory {

	// @Test
	void testUpdate() {
		var em = emf.createEntityManager();
		var p = em.find(Product.class, 1);
		em.getTransaction().begin();
		p.setName("Other");
		em.persist(p);
		p.setName("hello");
		em.getTransaction().commit();
	}

	@Test
	void testUpdate1() {
		var em = emf.createEntityManager();
		var p = em.find(Product.class, 1);
		em.getTransaction().begin();
		p.setName("Other");
		em.persist(p);
		em.getTransaction().commit();
		assertTrue(em.contains(p));
		em.detach(p);
		assertFalse(em.contains(p));
	}

}
