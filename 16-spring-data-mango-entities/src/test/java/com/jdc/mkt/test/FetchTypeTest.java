package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.jdc.mkt.entity.Product;

@TestMethodOrder(OrderAnnotation.class)
public class FetchTypeTest extends EntityFactory {

	@Test
	@Order(1)
	void testFetchWithFind() {
		var em = emf.createEntityManager();
		var p = em.find(Product.class, 6);
		assertNull(p);
	}

	@Test
	@Order(2)
	void testFetchWithGetReference() {
		var em = emf.createEntityManager();
		var p = em.getReference(Product.class, 6);
		assertNotNull(p);
		assertThrows(EntityNotFoundException.class, () -> p.setName("Other"));
	}

	@Test
	void testFetchWithLazyMode() {
		var em = emf.createEntityManager();
		// var p = em.find(Product.class, 1);
		var p = em.getReference(Product.class, 1);
		p.setName("ss");
		assertTrue(em.contains(p));
		em.detach(p);
		p.setName("hello");
	}
}
