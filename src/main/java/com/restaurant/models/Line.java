package com.restaurant.models;

import com.restaurant.interfaces.AProduct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name= "line")
public class Line implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private AProduct product;
    private int amount;
    @ManyToOne
    private Order order;

    public Line() {
        super();
    }

    public Line(final AProduct product, final int amount) {
        super();
        this.product = product;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product: " + product + " x" + amount + " -- " + product.getPrice() * amount + " €";
    }

}