package models;

import interfaces.AProduct;

import java.io.Serial;
import java.io.Serializable;

public class Line implements Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private AProduct product;
    private int amount;

    public Line() {
        super();
    }

    public Line(AProduct product, int cantidad) {
        super();
        this.product = product;
        this.amount = cantidad;
    }

    public AProduct getProduct() {
        return product;
    }

    public void setProduct(AProduct product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product: " + product + " x" + amount + " -- " + product.getPrice() * amount + " €";
    }

}