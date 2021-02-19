package controllers;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import models.Order;

public class RepositoryOrders {
	
	private ArrayList<Order> orders;
	
	public static RepositoryOrders instance;

	public static RepositoryOrders getInstance() {
		if(instance==null) {
			instance = new RepositoryOrders();
		}
		return instance;
	}
	
	private RepositoryOrders() {
		this.orders=new ArrayList<Order>();
	}
	public RepositoryOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public ArrayList<Order> getAllOrders() {
		return orders;
	}

	public ArrayList<Order> getOrdersByClient(String dni) {
		ArrayList<Order> result = null;
		if (dni != null && !dni.isEmpty()) {
			result = new ArrayList<Order>();
			for (int i = 0; i < orders.size(); i++) {
				if (orders.get(i).getClient().getDni().equals(dni)) {
					result.addAll(orders.get(i).getClient().getOrders());
				}
			}
		}
		return result;
	}

	public ArrayList<Order> getOrdersByDate(LocalDate ini, LocalDate end) {
		ArrayList<Order> result = null;
		if (ini != null && end != null) {
			result = new ArrayList<Order>();
			LocalDateTime ini1=ini.atStartOfDay();
			LocalDateTime end1=end.atStartOfDay();
			for (int i = 0; i < orders.size(); i++) {
				if (orders.get(i).getDate().isBefore(ini1) && orders.get(i).getDate().isAfter(end1)) {
					result.add(orders.get(i));
				}
			}
		}
		return result;
	}

	public ArrayList<Order> getOrdersNoDelivered() {
		ArrayList<Order> result = null;
			result = new ArrayList<Order>();
			for (int i = 0; i < orders.size(); i++) {
				if(!orders.get(i).isDelivered()) {
					result.add(orders.get(i));
				}
			}
		
		return result;
	}
	public ArrayList<Order> getOrdersNoPayed() {
		ArrayList<Order> result = null;
			result = new ArrayList<Order>();
			for (int i = 0; i < orders.size(); i++) {
				if(!orders.get(i).isPayed()) {
					result.add(orders.get(i));
		
				}
			}
		
		return result;
	}
	public double getAllInput(){
		double result = 0;
		for (int i = 0; i < orders.size(); i++) {
			if(orders.get(i).isPayed()) {
				result=result+orders.get(i).getTotal();
			}
		}
		return result;
	}
	public double getInputByDate(LocalDate ini, LocalDate end){
		double result = 0;
		LocalDateTime ini1=ini.atStartOfDay();
		LocalDateTime end1=end.atStartOfDay();
		for (int i = 0; i < orders.size(); i++) {
			if (orders.get(i).getDate().isBefore(ini1) && orders.get(i).getDate().isAfter(end1)) {
				result=result+orders.get(i).getTotal();
			}
		}
		return result;
	}
}
