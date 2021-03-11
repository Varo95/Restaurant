package interfaces;

import java.time.LocalDateTime;
import java.util.ArrayList;

import models.Client;
import models.Order;

public interface IMainMenuController {
	public boolean newOrder(Client c, Order o);

	public boolean changeOrder(Client c, int id);

	public boolean changeOrder(LocalDateTime d, int id);

	public boolean changeOrder(Client c, LocalDateTime d, int id);

	public boolean deleteOrder(Client c, int id);

	public boolean deleteOrder(LocalDateTime d, int id);

	public boolean deleteOrder(Client c, LocalDateTime d, int id);

	public double cashToDay();

	public double cashThisMonth();

	public double cashTotal();

	public ArrayList<Order> viewOrdersNotPayed();

	public ArrayList<Order> viewOrdersPendingDelivered();

	public void saveAllAndClose();
}
