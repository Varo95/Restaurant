package com.restaurant.models;

import com.restaurant.interfaces.AProduct;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQueries;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
@Getter
@Setter
@Entity
@DiscriminatorValue("1")
public class Drink extends AProduct implements Serializable {

    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private boolean alcoholic;

    public Drink() {}

    public Drink(final String name, final double price, final boolean forCeliac, final boolean alcoholic) {
        super(name, price, forCeliac);
        this.alcoholic = alcoholic;
    }

    @Override
    public boolean equals(final Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (alcoholic ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
