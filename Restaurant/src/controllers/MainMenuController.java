package controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import interfaces.IMainMenuController;
import models.Client;
import models.Order;
import modelsrepository.RepositoryOrders;
import views.MenuViews;

public class MainMenuController implements IMainMenuController {
	
	RepositoryOrders orders = RepositoryOrders.getInstance();
	MenuViews MV = new MenuViews();
	
	@Override
	public boolean newOrder(Client c, LocalDateTime id) {
		boolean result=false;
		Order tmp = MV.addOrderView(c, id);
		orders.getAllOrders().add(tmp);
		result=true;
		return result;
	}

	@Override
	public boolean changeOrder(Client c) {
		boolean result=false;
		orders.getAllOrders().get(1).setClient(c);
		return result;
	}

	@Override
	public boolean changeOrder(LocalDateTime d) {
		boolean result=false;
		return result;
	}

	@Override
	public boolean changeOrder(Client c, LocalDateTime d) {
		boolean result=false;
		return result;
	}

	@Override
	public boolean deleteOrder(Client c) {
		boolean result=false;
		return result;
	}

	@Override
	public boolean deleteOrder(LocalDateTime d) {
		boolean result=false;
		return result;
	}

	@Override
	public boolean deleteOrder(Client c, LocalDateTime d) {
		boolean result=false;
		return result;
	}

	@Override
	public double cashToDay() {
		double result=-1;
		return result;
	}

	@Override
	public double cashThisMonth() {
		double result=-1;
		return result;
	}

	@Override
	public double cashTotal() {
		double result=-1;
		return result;
	}

	@Override
	public ArrayList<Order> viewOrdersNotPayed() {
		ArrayList<Order> result=null;
		return result;
	}

	@Override
	public ArrayList<Order> viewOrdersPendingDelivered() {
		ArrayList<Order> result=null;
		return result;
	}

	@Override
	public void saveAllAndClose() {
		
	}
	
	

}
