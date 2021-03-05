package interfaces;

import models.Order;

public interface IOrderMenuController {
	public boolean addProduct(Order o);

	public boolean editLine(Order o, int n, boolean cant);

	public boolean removeLine(Order o, int n);

	public boolean setAddress(String a);

	public boolean savePaid();

	public boolean saveNotPaid();
}
