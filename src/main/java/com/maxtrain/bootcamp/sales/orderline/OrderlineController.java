package com.maxtrain.bootcamp.sales.orderline;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.maxtrain.bootcamp.sales.customer.Customer;
import com.maxtrain.bootcamp.sales.item.Item;
import com.maxtrain.bootcamp.sales.item.ItemRepository;
import com.maxtrain.bootcamp.sales.order.Order;
import com.maxtrain.bootcamp.sales.order.OrderRepository;




@CrossOrigin
@RestController
@RequestMapping("/api/orderlines")
public class OrderlineController {

		@Autowired
		private OrderlineRepository ordLineRepo;
		
		@Autowired
		private OrderRepository ordRepo;
		
		@Autowired
		private ItemRepository itemRepo;
		
		private boolean recalculateOrderTotal(int orderId) {
			//read the order to be recalculated
			Optional<Order> anOrder = ordRepo.findById(orderId);
			//if not found return false
			if(anOrder.isEmpty()) {
				return false;
			}
			//get the order
			Order order = anOrder.get();
			//get all orderLines attached to the order
			Iterable<Orderline> orderlines = ordLineRepo.findByOrderId(orderId);
			double total = 0;
			//for each loop!
			for(Orderline ol : orderlines) {
				//for each orderLine, multiply the quantity times the price
				// and add it to the total
				if(ol.getItem().getName() == null) {
					Item item = itemRepo.findById(ol.getItem().getId()).get();
					ol.setItem(item);
				}
				total += ol.getQuantity() * ol.getItem().getPrice();
			}
			//update the total in the order
			order.setTotal(total);
			ordRepo.save(order);
			
			return true;
			
		}
		
		@GetMapping
		public ResponseEntity<Iterable<Orderline>> getOrderlines(){
			Iterable<Orderline> orderlines = ordLineRepo.findAll();
			return new ResponseEntity<Iterable<Orderline>>(orderlines, HttpStatus.OK);
		}
		
		@GetMapping("{id}")
		public ResponseEntity<Orderline> getOrderline(@PathVariable int id){
			Optional<Orderline> orderline = ordLineRepo.findById(id);
			if(orderline.isEmpty()) {
				return new ResponseEntity(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Orderline>(orderline.get(), HttpStatus.OK);
		}
		@PostMapping
		public ResponseEntity<Orderline> postOrderline(@RequestBody Orderline orderline){
			Orderline newOrderline = ordLineRepo.save(orderline);
			Optional<Order> order = ordRepo.findById(orderline.getOrder().getId());
			if(!order.isEmpty()) {
				boolean success = recalculateOrderTotal(order.get().getId());
				if(!success) {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
		    return new ResponseEntity<Orderline>(newOrderline, HttpStatus.CREATED);
		}
		@SuppressWarnings("rawtypes")
		@PutMapping("{id}")
		public ResponseEntity putOrderline(@PathVariable int id, @RequestBody Orderline orderline){
			if(orderline.getId() != id) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			ordLineRepo.save(orderline);
			Optional<Order> order = ordRepo.findById(orderline.getOrder().getId());
			if(!order.isEmpty()) {
				boolean success = recalculateOrderTotal(order.get().getId());
				if(!success) {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		@SuppressWarnings("rawtypes")
		@DeleteMapping("{id}")
		public ResponseEntity<Orderline> deleteOrderline(@PathVariable int id){
			Optional<Orderline> orderline = ordLineRepo.findById(id);
			if(orderline.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			ordLineRepo.delete(orderline.get());
			Optional<Order> order = ordRepo.findById(orderline.get().getOrder().getId());
			if(!order.isEmpty()) {
				boolean success = recalculateOrderTotal(order.get().getId());
				if(!success) {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
			}
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
}
