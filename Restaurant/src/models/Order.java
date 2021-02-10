package models;

import java.time.LocalDateTime;

import interfaces.AProduct;

public class Order {

	private Client client;
	private AProduct[] products;
	private double total;
	private LocalDateTime date;
	private String address;
	private boolean delivered;
	private boolean payed;
	
	
}
