package models;

import interfaces.AProduct;

public class Line {

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

    public int getCantidad() {
        return amount;
    }

    public void setCantidad(int cantidad) {
        this.amount = cantidad;
    }

    @Override
    public String toString() {
        return "Product: " + product + " x" + amount + " -- " + product.getPrice() * amount + " €";
    }

}