package interfaces;

import models.Line;
import models.Order;

public interface IOrderMenuController {
    public boolean addProduct(Order o);

    public Line editLine(Line e, int ammount, AProduct product);

    public boolean removeLine(Order o, int n);

    public boolean setAddress(String a);

    public boolean savePaid();

    public boolean saveNotPaid();
}
