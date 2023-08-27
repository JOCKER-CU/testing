package com.jdc.hns.dto;

public class Product {

	private int id;
	private String name;
	private int detailPrice;
	private int wholeSalePrice;
	private Category category;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getDetailPrice() {
		return detailPrice;
	}
	public void setDetailPrice(int detailPrice) {
		this.detailPrice = detailPrice;
	}
	public int getWholeSalePrice() {
		return wholeSalePrice;
	}
	public void setWholeSalePrice(int wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
}