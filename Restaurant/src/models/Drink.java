package models;

import java.util.ArrayList;

import interfaces.AProduct;

public class Drink extends AProduct{

	private boolean alcoholic;
	public Drink() {
		//super();
	}
	
	public Drink(String name, double price, boolean forCeliac, boolean alcoholic, ArrayList<Integer> bundlePack) {
		super(name, price, forCeliac, bundlePack);
		this.alcoholic = alcoholic;
	}

	public boolean isAlcoholic() {
		return alcoholic;
	}
	
	public ArrayList<Integer> getBundlePack() {
		return bundlePack;
	}
}
