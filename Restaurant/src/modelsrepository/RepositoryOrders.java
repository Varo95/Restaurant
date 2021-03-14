package modelsrepository;
import java.io.Serial;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.io.Serializable;
import models.Order;
import utilities.RepositoryUtils;

public class RepositoryOrders implements Serializable {

	@Serial
	private static final long serialVersionUID = 6529685098267757690L;
	private ArrayList<Order> orders;
	
	private static RepositoryOrders instance;

	public static RepositoryOrders getInstance() {
		RepositoryUtils u = new RepositoryUtils();
		if (u.loadOrders("orders.data") != null) {
			instance = u.loadOrders("orders.data");
		} else {
			instance = new RepositoryOrders();
		}
		return instance;
	}
	
	private RepositoryOrders() {
		this.orders=new ArrayList<Order>();
	}
/**
 * 
 * @return Todas las ordenes
 */
	public ArrayList<Order> getAllOrders() {
		return orders;
	}
/**
 * Función que devuelve las ordenes echas por un cliente la cual buscamos por un dni
 * @param dni del cliente
 * @return Orden encontrada
 */
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
/**
 * Función que devulve una orden comprendida entre una fecha inicial y una fecha final
 * @param ini fecha inicial
 * @param end fecha final
 * @return Orden encontrada
 */
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
/**
 * Función que devuelve las ordenes no enviadas
 * @return Ordenes encontradas
 */
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
	/**
	 * Función que devuelve las ordenes no pagadas
	 * @return Ordenes encontradas 
	 */
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
	/**
	 * Función que devuelve todas las ganancias
	 * @return Ganancias totales
	 */
	public double getAllInput(){
		double result = 0;
		for (Order order : orders) {
			if (order.isPayed()) {
				result = result + order.getTotal();
			}
		}
		return result;
	}
/**
 * Funcion que devuelve las ganancia comprendidas entre una fecha inicial y otra final
 * @param ini fecha inicial
 * @param end fecha final
 * @return
 */
	public double getInputByDate(LocalDate ini, LocalDate end){
		double result = 0;
		LocalDateTime ini1=ini.atStartOfDay();
		LocalDateTime end1=end.atStartOfDay();
		for (Order order : orders) {
			if (order.getDate().isBefore(ini1) && order.getDate().isAfter(end1)) {
				result = result + order.getTotal();
			}
		}
		return result;
	}
}
