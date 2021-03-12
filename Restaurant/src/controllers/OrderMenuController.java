package controllers;

import java.util.ArrayList;

import interfaces.AProduct;
import interfaces.IOrderMenuController;
import models.Client;
import models.Line;
import models.Order;
import modelsrepository.RepositoryOrders;

public class OrderMenuController implements IOrderMenuController {

	RepositoryOrders RO=RepositoryOrders.getInstance();
	private ArrayList<Order> orders = RO.getAllOrders();

	@Override
	public boolean addProduct(Order o) {
		boolean result = false;
		AProduct aliment = null;
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
	public Line editLine(Line e, int ammount, AProduct product) {
		Line result = null;
		if (ammount>=1 | product!=null) {
			result=new Line();
			//eliminar producto
			// llamar a vista para cambiar product 
			//orders.get(index).getProducts().get(n).setProduct();
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

	public ArrayList<Order> getOrders_byClient(Client c){
		ArrayList<Order> result=null;
		if(c!=null && c.getDni()!=null && !c.getDni().isEmpty()){
			result=RO.getOrdersByClient(c.getDni());
		}
		return result;
	}

}
