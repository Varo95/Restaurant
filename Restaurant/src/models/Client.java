package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Client extends Person implements Serializable {

    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private ArrayList<String> address;
    private ArrayList<Order> orders;
    private int points;

    public Client(String dni, String name, int age, ArrayList<String> address, ArrayList<Order> orders, int points) {
        super(dni, name, age);
        this.address = address;
        this.orders = orders;
        this.points = points;
    }

    public ArrayList<String> getAddress() {
        return address;
    }

    public void setAddress(ArrayList<String> address) {
        this.address = address;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    public int getPoints() {
        return points;
    }

    public void addPoints(int points) {
        this.points += points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return super.toString() + "---Order info---"+"\n"
                +"Addresses= "+ address.toString() + "\n" +
                "Orders= " + orders + "\n" +
                "Points= " + points +"\n"+
                "------------------------------";
    }
}