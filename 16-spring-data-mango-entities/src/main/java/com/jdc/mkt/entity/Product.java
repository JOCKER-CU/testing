package com.jdc.mkt.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int price;

	@ManyToOne
	private Category category;

	public Product(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	@PrePersist
	void beforePersist() {
		System.out.println("Before persist");
	}
	
	@PostPersist
	void afterPersist() {
		System.out.println("After Persist");
	}
	
	@PrePersist
	void beforeRemove() {
		System.out.println("Before Remove");
	}

	@PreUpdate
	void beforeUpdate() {
		System.out.println("Before update");
	}
}
