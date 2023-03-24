package com.maxtrain.bootcamp.sales.orderline;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.maxtrain.bootcamp.sales.item.Item;
import com.maxtrain.bootcamp.sales.order.Order;

import jakarta.persistence.*;


@Entity
@Table(name="orderlines")
public class Orderline {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		private int quantity;
		
		
		@JsonBackReference
		@ManyToOne(optional=false)
		@JoinColumn(name="orderId")
		private Order order;
		
		@ManyToOne(optional=false)
		@JoinColumn(name="itemId")
		private Item item;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public Order getOrder() {
			return order;
		}

		public void setOrder(Order order) {
			this.order = order;
		}

		public Item getItem() {
			return item;
		}

		public void setItem(Item item) {
			this.item = item;
		}
		
		
}
