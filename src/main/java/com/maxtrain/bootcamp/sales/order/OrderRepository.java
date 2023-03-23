package com.maxtrain.bootcamp.sales.order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer>{
	Iterable<Order> findByStatus(String status);
}
