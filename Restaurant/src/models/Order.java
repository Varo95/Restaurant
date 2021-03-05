package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

import interfaces.AProduct;

public class Order {

	private Client client;
	private ArrayList<Line> products;
	private double total;
	private LocalDateTime date;
	private String address;
	private boolean delivered;
	private boolean payed;
	
	
	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	public ArrayList<Line> getProducts() {
		return products;
	}


	public void setProducts(ArrayList<Line> products) {
		this.products = products;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public LocalDateTime getDate() {
		return date;
	}


	public void setDate(LocalDateTime date) {
		this.date = date;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public boolean isDelivered() {
		return delivered;
	}


	public void setDelivered(boolean delivered) {
		this.delivered = delivered;
	}


	public boolean isPayed() {
		return payed;
	}


	public void setPayed(boolean payed) {
		this.payed = payed;
	}


	@Override
	public String toString() {
		return "Order [client=" + client + ", products="+ products + ", total=" + total + ", date="
				+ date + ", address=" + address + ", delivered=" + delivered + ", payed=" + payed + "]";
	}
	
	
	
}
