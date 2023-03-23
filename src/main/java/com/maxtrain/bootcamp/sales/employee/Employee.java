package com.maxtrain.bootcamp.sales.employee;

import jakarta.persistence.*;

@Entity
@Table(name="Employees", uniqueConstraints=@UniqueConstraint(name="UDIX_Code", columnNames= {"email"}))
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(length=40, nullable=false)
	private String email;
	@Column(length=40, nullable=false)
	private String password;
	@Column(length=40, nullable=false)
	private String name;
	
	//methods
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}