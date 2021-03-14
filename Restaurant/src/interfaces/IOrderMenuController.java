package interfaces;

import models.Line;
import models.Order;

import java.util.ArrayList;

public interface IOrderMenuController {
    public boolean addProduct(Order o);

    public boolean editLine(Line e, int amount, AProduct product);

    public boolean removeLine(ArrayList<Line> lines, int n);

    public boolean setAddress(Order o,String a);

    public boolean savePaid();

    public boolean saveNotPaid();
}
