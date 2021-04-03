package com.sbjenkins.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id ;
	String brand;
	String serviceTag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getServiceTag() {
		return serviceTag;
	}
	public void setServiceTag(String serviceTag) {
		this.serviceTag = serviceTag;
	}
	public Product(int id, String brand, String serviceTag) {
		super();
		this.id = id;
		this.brand = brand;
		this.serviceTag = serviceTag;
	}
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", brand=" + brand + ", serviceTag=" + serviceTag + "]";
	}
	
	
}
