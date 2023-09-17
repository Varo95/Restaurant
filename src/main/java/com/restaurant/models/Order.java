package com.restaurant.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
@NamedQueries({
        @NamedQuery(name = "Order.getBetweenDate", query = "SELECT o FROM Order o WHERE o.date BETWEEN :start AND :end"),
})
public class Order implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    private Client client;
    @OneToMany(mappedBy = "order")
    private List<Line> products;
    @Transient
    private double total;
    private LocalDateTime date;
    private String address;
    private boolean delivered;
    private boolean payed;
    public Order(){

    }

    @Override
    public String toString() {
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
