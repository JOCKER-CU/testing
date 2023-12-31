package com.jdc.mkt.repo;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.jdc.mkt.entity.Course;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class CourseRepo {

	@PersistenceContext
	private EntityManager em;
	// private BasicDataSource s;
//	private LocalContainerEntityManagerFactoryBean l;
//	HibernatePersistenceProvider h;
//	JpaTransactionManager jpa;

	@Transactional
	public Course create(Course c) {
		em.persist(c);
		return c;

	}
}
