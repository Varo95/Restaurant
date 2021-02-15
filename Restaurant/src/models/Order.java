package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

import interfaces.AProduct;

public class Order {

	private Client client;
	private ArrayList<AProduct> products;
	private double total;
	private LocalDateTime date;
	private String address;
	private boolean delivered;
	private boolean payed;
	
	
	@Override
	public String toString() {
		return "Order [client=" + client + ", products="+ products + ", total=" + total + ", date="
				+ date + ", address=" + address + ", delivered=" + delivered + ", payed=" + payed + "]";
	}
	
	
	
}
