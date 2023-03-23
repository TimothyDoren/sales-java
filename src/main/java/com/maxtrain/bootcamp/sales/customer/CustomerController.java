package com.maxtrain.bootcamp.sales.customer;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {

		@Autowired
		private CustomerRepository custRepo;
		
		@GetMapping
		public ResponseEntity<Iterable<Customer>> getCustomers(){
			Iterable<Customer> customers = custRepo.findAll();
			return new ResponseEntity<Iterable<Customer>>(customers, HttpStatus.OK);
		}
		@GetMapping("{id}")
		public ResponseEntity<Customer> getCustomer(@PathVariable int id){
			Optional<Customer> customer = custRepo.findById(id);
			if(customer.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
		}
		@GetMapping("code/{code}")
		public ResponseEntity<Customer> getCustomerByCode(@PathVariable String code){
			Optional<Customer> customer = custRepo.findByCode(code);
			if(customer.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Customer>(customer.get(), HttpStatus.OK);
		}
		@PostMapping
		public ResponseEntity<Customer> postCustomer(@RequestBody Customer customer){
			Customer savedCustomer = custRepo.save(customer);
		    return new ResponseEntity<Customer>(savedCustomer, HttpStatus.CREATED);
		}
		@SuppressWarnings("rawtypes")
		@PutMapping("{id}")
		public ResponseEntity putCustomer(@PathVariable int id, @RequestBody Customer customer){
			if(customer.getId() != id) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			custRepo.save(customer);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		@SuppressWarnings("rawtypes")
		@DeleteMapping("{id}")
		public ResponseEntity<Customer> deleteCustomer(@PathVariable int id){
			Optional<Customer> customer = custRepo.findById(id);
			if(customer.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			custRepo.delete(customer.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
}
	