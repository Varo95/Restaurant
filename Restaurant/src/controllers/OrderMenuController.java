package controllers;

import java.util.ArrayList;

import interfaces.AProduct;
import interfaces.IOrderMenuController;
import models.Client;
import models.Line;
import models.Order;
import modelsrepository.RepositoryOrders;
import utilities.RepositoryUtils;

public class OrderMenuController implements IOrderMenuController {

    RepositoryOrders RO = RepositoryOrders.getInstance();
    private ArrayList<Order> orders = RO.getAllOrders();
    RepositoryUtils RU=new RepositoryUtils();

    public boolean addNewOrder(Order o){
        boolean result=false;
        if(o!=null && o.getClient()!=null && !o.getClient().toString().isEmpty()){
            orders.add(o);
            result=true;
        }
        return result;
    }

    @Override
    public boolean addProduct(Order o) {
        boolean result = false;
        AProduct aliment = null;
        for (int i = 0; i < orders.size(); i++) {
            Line tmp = orders.get(i).getProducts().get(i);
            if (tmp.getProduct() != aliment) {
                ArrayList<Line> tmp2 = new ArrayList<Line>();
                tmp2.add(new Line(aliment, 1));
                orders.get(i).setProducts(tmp2);
                result = true;
            } else if (tmp.getProduct() == aliment) {
                tmp.setAmount(tmp.getAmount() + 1);
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean editLine(Line e, int amount, AProduct product) {
        boolean result = false;
        if (amount >= 1 | product != null) {
            if (product!=null) {
                e.setProduct(product);
                result = true;
            }else if(amount>1) {
                e.setAmount(amount);
                result=true;
            }
        }
        return result;
    }

    @Override
    public boolean removeLine(ArrayList<Line> lines, int n) {
        boolean result = false;
        if(lines!=null && n>-1){
            if(n<lines.size()){
                lines.remove(n);
                result=true;
            }
        }
        return result;

    }

    @Override
    public boolean setAddress(Order o,String a) {
        boolean result=false;
        if (o != null && a!=null && !a.isEmpty()) {
            o.setAddress(a);
            result=true;
        }
        return result;
    }

    /**
     * Guarda un pedido pagado en la clase orders
     */
    @Override
    public boolean savePaid() {
        boolean result = false;
        ArrayList<Order> saved=new ArrayList<>();
        for(Order o:orders){
            if(o.isPayed()){
                saved.add(o);
            }
        }
        if(RU.saveOrders("PayedOrders.data",saved)) result = true;
        return result;
    }

    /**
     * Guarda un pedido no pagado en la clase orders
     */
    @Override
    public boolean saveNotPaid() {
        boolean result = false;
        ArrayList<Order> saved=new ArrayList<>();
        for(Order o:orders){
            if(!o.isPayed()){
                saved.add(o);
            }
        }
        if(RU.saveOrders("NotPayedOrders.data",saved)) result = true;
        return result;
    }

    public ArrayList<Order> getOrders_byClient(Client c) {
        ArrayList<Order> result = null;
        if (c != null && c.getDni() != null && !c.getDni().isEmpty()) {
            result = RO.getOrdersByClient(c.getDni());
        }
        return result;
    }

}
