package controllers;

import java.util.ArrayList;

import interfaces.AProduct;
import interfaces.IOrderMenuController;
import models.Line;
import models.Order;
import modelsrepository.Repository;
import modelsrepository.RepositoryOrders;

public class OrderMenuController implements IOrderMenuController {

	RepositoryOrders RO=RepositoryOrders.getInstance();
	private ArrayList<Order> orders = RO.getAllOrders();

	@Override
	public boolean addProduct(Order o) {
		boolean result = false;
		AProduct aliment = null;
		// llamar a añadir producto en vista
		for (int i = 0; i < orders.size(); i++) {
			Line tmp = orders.get(i).getProducts().get(i);
			if (tmp.getProduct() != aliment) {
				ArrayList<Line> tmp2 = new ArrayList<Line>();
				tmp2.add(new Line(aliment, 1));
				orders.get(i).setProducts(tmp2);
				result = true;
			} else if (tmp.getProduct() == aliment) {
				tmp.setCantidad(tmp.getCantidad() + 1);
				result = true;
			}
		}
		return result;
	}
	@Override
	//Este metodo edita una linea
	public boolean editLine(Order o, int n, boolean cant) {
		boolean result = false;
		int index = orders.indexOf(o);
		if (!cant) {
			// llamar a vista para cambiar product 
			//orders.get(index).getProducts().get(n).setProduct();
		} else {
			//orders.get(index).getProducts().get(n).setCantidad();
		}
		return result;
	}
	@Override
	public boolean removeLine(Order o, int n) {
		boolean result = false;
		if (o != null && n >= 0) {
			int index = orders.indexOf(o);

			orders.get(index).getProducts().remove(n);

			result = true;
		}
		return result;

	}

	@Override
	public boolean setAddress(String a) {
		Order order = null;
		if (a != null) {
			// aqui se añade lo que se escriba en la vista
			order.setAddress(a);
		}
		return false;
	}

	/**
	 * Guarda un pedido pagado en la clase orders
	 */
	@Override
	public boolean savePaid() {
		boolean result = false;
		Order order = null;
		//
		if (order != null) {
			order.setPayed(true);
			orders.add(order);
			result = true;
		}
		return result;
	}

	/**
	 * Guarda un pedido no pagado en la clase orders
	 */
	@Override
	public boolean saveNotPaid() {
		boolean result = false;
		Order order = null;
		// llamar a la vista para que le pida datos al usuario
		if (order != null) {
			order.setPayed(false);
			orders.add(order);
			result = true;
		}
		return result;
	}

}
