package com.restaurant.interfaces;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER, name = "isDrink")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "products")
@NamedQueries({
        @NamedQuery(name = "AProduct.findAll", query = "SELECT p FROM AProduct p"),
        @NamedQuery(name = "AProduct.findByName", query = "SELECT p FROM AProduct p WHERE p.name = :name")
})
@Entity
public abstract class AProduct implements IProduct, Serializable {

    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private double price;
    private boolean forCeliac;

    protected AProduct() {}

    protected AProduct(final String name, final double price, final boolean forCeliac) {
        this.name = name;
        this.price = price;
        this.forCeliac = forCeliac;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AProduct aProduct = (AProduct) o;
        return id == aProduct.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
