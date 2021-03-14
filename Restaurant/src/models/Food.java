package models;

import interfaces.AProduct;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Food extends AProduct implements Serializable {

    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private boolean forVegan;

    public Food(int id, String name, double price, boolean forCeliac, boolean forVegan, ArrayList<Integer> bundlePack) {
        super(id, name, price, forCeliac, bundlePack);
        this.forVegan = forVegan;
    }

    public Food() {
    }

    public boolean isForVegan() {
        return forVegan;
    }

    public ArrayList<Integer> getBundlePack() {
        return bundlePack;
    }

    @Override
    public boolean getIsForCeliac() {
        return super.isForCeliac();
    }

    @Override
    public String toString() {
        return name + " -- " + price + " $";
    }

}
