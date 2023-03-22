package com.maxtrain.bootcamp.sales.customer;

import jakarta.persistence.*;


@Entity
@Table(name="Customers", uniqueConstraints=@UniqueConstraint(name="UIDX_Code", columnNames= {"code"})) //table name
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=30, nullable=false)
	private String code;
	@Column(length=30, nullable=false)
	private String name;
	@Column(columnDefinition="decimal(9,2) NOT NULL DEFAULT 0")
	private double sales;
	private boolean active;
	
	public Customer() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getSales() {
		return sales;
	}

	public void setSales(double sales) {
		this.sales = sales;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
}
