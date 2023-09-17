package com.restaurant.interfaces;

import com.restaurant.models.Client;
import com.restaurant.models.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;

public interface IMainMenuController {
    boolean newOrder(Client c, Order o);

    boolean changeOrder(Client c, int id);

    boolean changeOrder(LocalDateTime d, int id);

    boolean changeOrder(Client c, LocalDateTime d, int id);

    boolean deleteOrder(Client c, int id);

    boolean deleteOrder(LocalDateTime d, int id);

    boolean deleteOrder(Client c, LocalDateTime d, int id);

    double cashToDay();

    double cashThisMonth();

    double cashTotal();

    ArrayList<Order> viewOrdersNotPayed();

    ArrayList<Order> viewOrdersPendingDelivered();

    void saveAllAndClose();
}
