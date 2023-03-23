package com.maxtrain.bootcamp.sales.item;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.maxtrain.bootcamp.sales.customer.Customer;

@CrossOrigin
@RestController
@RequestMapping("api/items")
public class ItemController {
	
		@Autowired
		private ItemRepository itemRepo;
		
		@GetMapping
		public ResponseEntity<Iterable<Item>> getItems(){
			Iterable<Item> items = itemRepo.findAll();
			return new ResponseEntity<Iterable<Item>>(items, HttpStatus.OK);
		}
		@GetMapping("{id}")
		public ResponseEntity<Item> getItem(@PathVariable int id){
			Optional<Item> item = itemRepo.findById(id);
			if(item.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Item>(item.get(), HttpStatus.OK);
		}
		@PostMapping
		public ResponseEntity<Item> postItem(@RequestBody Item item){
			Item savedItem = itemRepo.save(item);
			return new ResponseEntity<Item>(savedItem, HttpStatus.OK);
		}
		@SuppressWarnings("rawtypes")
		@PutMapping("{id}")
		public ResponseEntity putItem(@PathVariable int id, @RequestBody Item item){
			if(item.getId() != id) {
				return new ResponseEntity(HttpStatus.BAD_REQUEST);
			}
			itemRepo.save(item);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		@SuppressWarnings("rawtypes")
		@DeleteMapping("{id}")
		public ResponseEntity<Item> deleteItem(@PathVariable int id){
			Optional<Item> item = itemRepo.findById(id);
			if(item.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			itemRepo.delete(item.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
}
