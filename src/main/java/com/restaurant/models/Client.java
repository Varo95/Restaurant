package com.restaurant.models;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
@Entity
@Table(name = "clients")
@NamedQueries({
        @NamedQuery(name = "Client.findAll", query = "SELECT p FROM Client p"),
        @NamedQuery(name = "Client.findByName", query = "SELECT p FROM Client p WHERE p.name = :name"),
        @NamedQuery(name = "Client.findByDNI", query = "SELECT p FROM Client p WHERE p.dni = :dni")
})
public class Client extends Person implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    @ElementCollection
    @CollectionTable(name = "address")
    private List<String> address;
    @OneToMany(mappedBy = "client")
    private List<Order> orders;
    private int points;

    public Client(final String dni, final String name, final int age, final List<String> address, final int points){
        super(dni, name, age);
        this.address = address;
        this.points = points;
    }

    public Client(){
        super();
        this.address = new CopyOnWriteArrayList<>();
        this.orders = new CopyOnWriteArrayList<>();
    }

    @Override
    public boolean equals(final Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        int result = address != null ? address.hashCode() : 0;
        result = 31 * result + (orders != null ? orders.hashCode() : 0);
        result = 31 * result + points;
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Points = " + points;
    }
}