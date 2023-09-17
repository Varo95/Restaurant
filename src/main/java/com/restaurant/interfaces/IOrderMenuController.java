package com.restaurant.interfaces;

import com.restaurant.models.Line;
import com.restaurant.models.Order;

import java.util.ArrayList;

public interface IOrderMenuController {
    boolean addProduct(Order o);

    boolean editLine(Line e, int amount, AProduct product);

    boolean removeLine(ArrayList<Line> lines, int n);

    boolean setAddress(Order o,String a);

    boolean savePaid();

    boolean saveNotPaid();
}
