package com.maxtrain.bootcamp.sales.order;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.maxtrain.bootcamp.sales.customer.Customer;
import com.maxtrain.bootcamp.sales.orderline.Orderline;

import jakarta.persistence.*;
@Entity
@Table(name="orders")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(columnDefinition="date NOT NULL")
	private LocalDate date;
	@Column(length=50, nullable=false)
	private String description;
	@Column(length=30, nullable=false)
	private String status;
	@Column(columnDefinition="decimal(9,2) NOT NULL DEFAULT 0")
	private double total;

	@ManyToOne(optional=false)
	@JoinColumn(name="customerId", columnDefinition="int")
	private Customer customer;

	@JsonManagedReference
	@OneToMany(mappedBy="order")
	private List<Orderline> orderlines;
	
	public List<Orderline> getOrderlines(){
		return orderlines;
	}
	
	public void setOrderlines (List<Orderline> orderlines) {
		this.orderlines = orderlines;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
