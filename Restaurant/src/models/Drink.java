package models;

import interfaces.AProduct;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class Drink extends AProduct implements Serializable {

    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private boolean alcoholic;

    public Drink() {}

    public Drink(int id, String name, double price, boolean forCeliac, boolean alcoholic, ArrayList<Integer> bundlePack) {
        super(id, name, price, forCeliac, bundlePack);
        this.alcoholic = alcoholic;
    }

    public boolean isAlcoholic() {
        return alcoholic;
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
