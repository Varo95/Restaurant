package controllers;

import interfaces.IMainMenuController;
import models.Client;
import models.Order;
import modelsrepository.RepositoryClients;
import modelsrepository.RepositoryOrders;
import utilities.RepositoryUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;

public class MainMenuController implements IMainMenuController {

    RepositoryOrders orders = RepositoryOrders.getInstance();
    RepositoryClients clients = RepositoryClients.getInstance();
    /**
     * Añadir un cliente.
     * 
     * @param c	Cliente a añadir.
     * @return True si se ha añadido, false si no.
     * 
     */
    public boolean addClient(Client c) {
        boolean result = false;
        if (c != null) {
            clients.addClient(c);
            result = true;
        }
        return result;
    }
    /**
     * Editar cliente.
     * @param id El identificador del cliente.
     * @param c El cliente a editar.
     * @return True si se ha editado, false si no.
     */
    public boolean editClient(int id, Client c) {
        boolean result = false;
        if (id > -1 && c != null && clients.getAllClients().get(id) != null) {
            if (c.getAddress() != null) {
                clients.getAllClients().get(id).setAddress(c.getAddress());
                result = true;
            } else if (c.getOrders() != null) {
                clients.getAllClients().get(id).setOrders(c.getOrders());
                result = true;
            } else if (c.getPoints() > -1) {
                clients.getAllClients().get(id).setPoints(c.getPoints());
                result = true;
            } else if (c.getName() != null) {
                clients.getAllClients().get(id).setName(c.getName());
                result = true;
            } else if (c.getDni() != null) {
                clients.getAllClients().get(id).setDni(c.getDni());
                result = true;
            } else if (c.getAge() > -1) {
                clients.getAllClients().get(id).setAge(c.getAge());
                result = true;
            }
        }
        return result;
    }
    /**
     * Borrar cliente.
     * @param id El identificador del cliente.
     * @return True si se ha eliminado, false si no.
     */
    public boolean removeClient(int id) {
        boolean result = false;
        if (id > -1 && clients.getAllClients().get(id) != null) {
            clients.getAllClients().remove(id);
            result = true;
        }
        return result;
    }
    
   

    @Override
    /**
     * Nueva orden.
     * @param c Recibe un cliente.
     * @param o Recibe una orden.
     * @return True si se ha creado la orden , false si no.
     */
    public boolean newOrder(Client c, Order o) {
        boolean result = false;
        if (o != null && o.getClient() == null) {
            o.setClient(c);
            o.setDate(LocalDateTime.now());
            orders.getAllOrders().add(o);
            result = true;
        }
        return result;
    }

    @Override
    /**
     * Cambiar orden.
     * @param c Recibe un cliente.
     * @param id Redibe un identificador de order.
     * @return True si se ha cambiado la orden, false si no.
     */
    public boolean changeOrder(Client c, int id) {
        boolean result = false;
        if (c != null && id > -1 && orders.getAllOrders().get(id) != null) {

            orders.getAllOrders().get(id).setClient(c);
            result = true;
        }
        return result;
    }

    @Override
    /**
     * Cambiar orden.
     * @param d Recibe la hora local.
     * @param id Recibe un identificador de orders.
     * @return True si se ha cambiado la orden, false si no.
     */
    public boolean changeOrder(LocalDateTime d, int id) {
        boolean result = false;
        if (d != null && id > -1) {
            orders.getAllOrders().get(id).setDate(d);
            result = true;
        }
        return result;
    }

    @Override
    /**
     * Cambiar orden.
     * @param d Recibe la hora local.
     * @param id Recibe un identificador de orders.
     * @param c Recibe un cliente.
     * @return True si se ha cambiado la orden, false si no.
     */
    public boolean changeOrder(Client c, LocalDateTime d, int id) {
        boolean result = false;
        if (c != null && d != null && id > -1) {
            orders.getAllOrders().get(id).setClient(c);
            orders.getAllOrders().get(id).setDate(d);
            result = true;
        }
        return result;
    }

    @Override
    /**
     * Borrar orden.
     *@param c Recibe un cliente.
     *@param id Recibe un identificador de orders.
     *@return True si se ha eliminador la orden, false si no. 
     */
    public boolean deleteOrder(Client c, int id) {
        boolean result = false;
        if (c != null) {
            for (int i = 0; i < orders.getAllOrders().size() && !result; i++) {
                if (orders.getAllOrders().get(i).getClient().equals(c)) {
                    orders.getAllOrders().remove(i);
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    /**
     * Borrar orden.
     * @param id Recibe un identificador de orders.
     * @param d Recibe la hora local.
     * @return True si se ha eliminador la orden, false si no. 
     */
    public boolean deleteOrder(LocalDateTime d, int id) {
        boolean result = false;
        if (d != null) {
            for (int i = 0; i < orders.getAllOrders().size() && !result; i++) {
                if (orders.getAllOrders().get(i).getDate().equals(d)) {
                    orders.getAllOrders().remove(i);
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    /**
     * Borrar orden.
     * @param id Recibe un identificador de orders.
     * @param d Recibe la hora local.
     * @param c Recibe un cliente.
     * @return True si se ha eliminador la orden, false si no.
     */
    public boolean deleteOrder(Client c, LocalDateTime d, int id) {
        boolean result = false;
        if (c != null && d != null) {
            for (int i = 0; i < orders.getAllOrders().size() && !result; i++) {
                if (orders.getAllOrders().get(i).getClient().equals(c) && orders.getAllOrders().get(i).getDate().equals(d)) {
                    orders.getAllOrders().remove(i);
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    /**
     * Dinero recaudado en el dia.
     * @return Devuelbe un el dinero recaudado en el dia.
     */
    public double cashToDay() {
        double result = 0;
        int day = Calendar.DAY_OF_WEEK_IN_MONTH;
        for (int i = 0; i < orders.getAllOrders().size(); i++) {
            int day1 = orders.getAllOrders().get(i).getDate().getDayOfMonth();
            boolean isPayed = orders.getAllOrders().get(i).isPayed();
            if (day == day1 && isPayed) {
                result += orders.getAllOrders().get(i).getTotal();
            }
        }
        return result;
    }

    @Override
    /**
     * Dinero recaudado en el mes
     * @return Devuelbe un el dinero recaudado en el mes.
     */
    public double cashThisMonth() {
        double result = 0;
        int moth = Calendar.MONTH + 1;
        for (int i = 0; i < orders.getAllOrders().size(); i++) {
            int moth1 = orders.getAllOrders().get(i).getDate().getMonthValue();
            boolean isPayed = orders.getAllOrders().get(i).isPayed();
            if (moth == moth1 && isPayed) {
                result += orders.getAllOrders().get(i).getTotal();
            }
        }
        return result;
    }
    /*
     * Dinero total recaudado.
     * @return Dinero total recaudado
     */
    @Override

    public double cashTotal() {
        return orders.getAllInput();
    }

    /**
     * Array de los pedidos no pagados.
     * @return Array de los pedidos.
     */
    @Override
    public ArrayList<Order> viewOrdersNotPayed() {
        ArrayList<Order> result = new ArrayList<Order>();
        for (int i = 0; i < orders.getAllOrders().size(); i++) {
            boolean isPayed = orders.getAllOrders().get(i).isPayed();
            if (!isPayed) {
                result.add(orders.getAllOrders().get(i));
            }
        }
        return result;
    }

    /**
     * Array de los pedidos pendientes de entregar.
     * @return Array de los pedidos.
     */
    @Override
    public ArrayList<Order> viewOrdersPendingDelivered() {
        ArrayList<Order> result = new ArrayList<Order>();
        for (int i = 0; i < orders.getAllOrders().size(); i++) {
            boolean delivered = orders.getAllOrders().get(i).isDelivered();
            if (!delivered) {
                result.add(orders.getAllOrders().get(i));
            }
        }
        return result;
    }
    /**
     * Este metodo guarda todo antes de salir de la aplicacion,
     */
    @Override
    public void saveAllAndClose() {
        RepositoryUtils tmp = new RepositoryUtils();
        tmp.saveClients("clients.data", clients);
        tmp.saveOrders("orders.data", orders);
    }


}