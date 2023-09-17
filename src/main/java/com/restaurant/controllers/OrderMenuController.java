package com.restaurant.controllers;

import java.util.ArrayList;

import com.restaurant.interfaces.AProduct;
import com.restaurant.interfaces.IOrderMenuController;
import com.restaurant.models.Client;
import com.restaurant.models.Line;
import com.restaurant.models.Order;
import com.restaurant.repositorys.RepositoryOrders;
import com.restaurant.utilities.RepositoryUtils;

public class OrderMenuController implements IOrderMenuController {

    RepositoryOrders RO = RepositoryOrders.getInstance();
    private ArrayList<Order> orders = RO.getAllOrders();
    RepositoryUtils RU=new RepositoryUtils();

    /**
     * @return La lista de todos los pedidos.
     */
    public ArrayList<Order> getAllOrders(){
        return this.orders;
    }

    /**
     * Añade un nuevo pedido a la lista de pedidos
     * @param o pedido a añadir
     * @return true si ha podido, false en caso contrario.
     */
    public boolean addNewOrder(Order o){
        boolean result=false;
        if(o!=null && o.getClient()!=null && !o.getClient().toString().isEmpty()){
            orders.add(o);
            result=true;
        }
        return result;
    }

    /**
     * Añade un producto a un pedido
     * @param o pedido a añadir el producto
     * @return true si se ha podido, false en caso contrario
     */
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

    /**
     * Edita una línea, la cantidad o el producto
     * @param e linea a editar
     * @param amount cantidad a poner
     * @param product producto que queremos colocar
     * @return true si se ha podido editar la línea, false en caso contrario.
     */
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

    /**
     * Elimina una línea de los productos de un pedido
     * @param lines array de lineas
     * @param n numero de la línea a eliminar
     * @return true si se ha podido cambiar la linea, false si no.
     */
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

    /**
     * Setea la dirección de un pedido a partir de una cadena de texto
     * @param o pedido a modificar la direccion
     * @param a cadena de texto a reemplazar
     * @return true si ha podido, false en caso contrario
     */
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

    /**
     * Este método sirve para devolver los pedidos de un cliente
     * @param c Cliente a obtener los pedidos
     * @return los pedidos del cliente
     */
    public ArrayList<Order> getOrders_byClient(Client c) {
        ArrayList<Order> result = null;
        if (c != null && c.getDni() != null && !c.getDni().isEmpty()) {
            result = RO.getOrdersByClient(c.getDni());
        }
        return result;
    }

}
