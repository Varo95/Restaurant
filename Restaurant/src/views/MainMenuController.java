package views;

import java.time.LocalDateTime;
import java.util.ArrayList;

import controllers.RepositoryOrders;
import interfaces.IMainMenuController;
import models.Client;
import models.Order;

public class MainMenuController implements IMainMenuController {
	
	RepositoryOrders orders = RepositoryOrders.getInstance();
	
	@Override
	public boolean newOrder(Client c, LocalDateTime id) {
		boolean result=false;
		if(c!=null && id!=null && c.getDni()!=null) {
			//buscar cliente y si no lo encuentra, crear uno nuevo
		}
		return result;
	}

	@Override
	public boolean changeOrder(Client c) {
		boolean result=false;
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
