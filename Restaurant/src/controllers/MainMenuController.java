package controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;



import interfaces.IMainMenuController;
import models.Client;
import models.Order;
import modelsrepository.RepositoryClients;
import modelsrepository.RepositoryOrders;
import utilities.RepositoryUtils;

public class MainMenuController implements IMainMenuController {

    RepositoryOrders orders = RepositoryOrders.getInstance();
    RepositoryClients clients = RepositoryClients.getInstance();

    public boolean addClient(Client c){
        boolean result=false;
        if(c!=null){
            clients.addClient(c);
            result=true;
        }
        return result;
    }

    public boolean editClient(int id, Client c){
        boolean result=false;
        if(id>-1 && c!=null && clients.getAllClients().get(id)!=null){
            if(c.getAddress()!=null) {
                clients.getAllClients().get(id).setAddress(c.getAddress());
                result=true;
            }else if(c.getOrders()!=null){
                clients.getAllClients().get(id).setOrders(c.getOrders());
                result=true;
            }else if(c.getPoints()>-1){
                clients.getAllClients().get(id).setPoints(c.getPoints());
                result=true;
            }else if(c.getName()!=null){
                clients.getAllClients().get(id).setName(c.getName());
                result=true;
            }else if(c.getDni()!=null){
                clients.getAllClients().get(id).setDni(c.getDni());
                result=true;
            }else if(c.getAge()>-1){
                clients.getAllClients().get(id).setAge(c.getAge());
                result=true;
            }
        }
        return result;
    }

    public boolean removeClient(int id){
        boolean result=false;
        if(id>-1 && clients.getAllClients().get(id)!=null){
            clients.getAllClients().remove(id);
            result=true;
        }
        return result;
    }

    @Override
    public boolean newOrder(Client c, Order o) {
        boolean result=false;
        if(o!=null && o.getClient()==null){
            o.setClient(c);
            o.setDate(LocalDateTime.now());
            orders.getAllOrders().add(o);
            result=true;
        }
        return result;
    }

    @Override
    public boolean changeOrder(Client c, int id) {
        boolean result=false;
        if (c!=null && id>-1 && orders.getAllOrders().get(id)!=null) {

            orders.getAllOrders().get(id).setClient(c);
            result=true;
        }
        return result;
    }

    @Override
    public boolean changeOrder(LocalDateTime d, int id) {
        boolean result=false;
        if (d!=null && id>-1) {
            orders.getAllOrders().get(id).setDate(d);
            result=true;
        }
        return result;
    }

    @Override
    public boolean changeOrder(Client c, LocalDateTime d, int id) {
        boolean result=false;
        if(c!=null && d!= null && id>-1) {
            orders.getAllOrders().get(id).setClient(c);
            orders.getAllOrders().get(id).setDate(d);
            result=true;
        }
        return result;
    }

    @Override
    public boolean deleteOrder(Client c, int id) {
        boolean result=false;
        if(c!= null) {
            for(int i=0; i<orders.getAllOrders().size() && !result;i++) {
                if(orders.getAllOrders().get(i).getClient().equals(c)) {
                    orders.getAllOrders().remove(i);
                    result=true;
                }
            }
        }
        return result;
    }

    @Override
    public boolean deleteOrder(LocalDateTime d, int id) {
        boolean result=false;
        if(d!= null) {
            for(int i=0; i<orders.getAllOrders().size() && !result;i++) {
                if(orders.getAllOrders().get(i).getDate().equals(d)) {
                    orders.getAllOrders().remove(i);
                    result=true;
                }
            }
        }
        return result;
    }

    @Override
    public boolean deleteOrder(Client c, LocalDateTime d, int id) {
        boolean result=false;
        if(c!=null && d!= null) {
            for(int i=0;i<orders.getAllOrders().size() && !result;i++){
                if(orders.getAllOrders().get(i).getClient().equals(c) && orders.getAllOrders().get(i).getDate().equals(d)){
                    orders.getAllOrders().remove(i);
                    result=true;
                }
            }
        }
        return result;
    }

    @Override
    public double cashToDay() {
        double result=0;
        int day=Calendar.DAY_OF_WEEK_IN_MONTH;
        for(int i=0;i<orders.getAllOrders().size();i++) {
            int day1= orders.getAllOrders().get(i).getDate().getDayOfMonth();
            boolean isPayed = orders.getAllOrders().get(i).isPayed();
            if(day == day1 && isPayed) {
                result+= orders.getAllOrders().get(i).getTotal();
            }
        }
        return result;
    }

    @Override
    public double cashThisMonth() {
        double result=0;
        int moth=Calendar.MONTH + 1;
        for(int i=0;i<orders.getAllOrders().size();i++) {
            int moth1= orders.getAllOrders().get(i).getDate().getMonthValue();
            boolean isPayed = orders.getAllOrders().get(i).isPayed();
            if(moth == moth1 && isPayed) {
                result+= orders.getAllOrders().get(i).getTotal();
            }
        }
        return result;
    }

    @Override
    public double cashTotal() {
        return orders.getAllInput();
    }

    @Override
    public ArrayList<Order> viewOrdersNotPayed() {
        ArrayList<Order> result= new ArrayList<Order>();
        for(int i=0;i<orders.getAllOrders().size();i++) {
            boolean isPayed= orders.getAllOrders().get(i).isPayed();
            if(!isPayed) {
                result.add(orders.getAllOrders().get(i));
            }
        }
        return result;
    }

    @Override
    public ArrayList<Order> viewOrdersPendingDelivered() {
        ArrayList<Order> result=new ArrayList<Order>();
        for(int i=0;i<orders.getAllOrders().size();i++) {
            boolean delivered= orders.getAllOrders().get(i).isDelivered();
            if(!delivered) {
                result.add(orders.getAllOrders().get(i));
            }
        }
        return result;
    }

    @Override
    public void saveAllAndClose() {
        RepositoryUtils tmp = new RepositoryUtils();
        tmp.saveClients("clients.data", clients);
        tmp.saveOrders("orders.data", orders);
    }


}