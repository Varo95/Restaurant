package models;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private Client client;
    private ArrayList<Line> products;
    private double total;
    private LocalDateTime date;
    private String address;
    private boolean delivered;
    private boolean payed;

    public Order(Client client, ArrayList<Line> products, double total, LocalDateTime date, String address, boolean delivered, boolean payed) {
        this.client = client;
        this.products = products;
        this.total = total;
        this.date = date;
        this.address = address;
        this.delivered = delivered;
        this.payed = payed;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ArrayList<Line> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Line> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public boolean isPayed() {
        return payed;
    }

    public void setPayed(boolean payed) {
        this.payed = payed;
    }

    @Override
    public String toString() {
        /*"<-Index"+"\n"+
                "---Client Info---"+"\n"+
                "Dni= " + dni + "\n" +
                "Name= " + name + "\n" +
                "Age= " + age + "\n";*/
        return "<-Index---------------"+"\n"+
                "---Order Info---" +"\n"+
                "Client= "+client.toString()+"\n" +
                "Products= " + products +"\n"+
                "Total= " + total +"\n"+
                "Date= " + date +"\n"+
                "Address= " + address +"\n"+
                "Delivered= " + delivered +"\n"+
                "Payed=" + payed + "\n"+
                "------------------------------";
    }


}
