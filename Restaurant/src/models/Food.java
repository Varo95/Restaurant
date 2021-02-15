package models;

import java.util.ArrayList;

import interfaces.AProduct;

public class Food extends AProduct {

	private boolean forVegan;
	
	public Food(String name, double price, boolean forCeliac, ArrayList<Integer> bundlePack, boolean forVegan) {
		super(name, price, forCeliac, bundlePack);
		this.forVegan = forVegan;
	}

	public boolean isForVegan() {
		return forVegan;
	}
	
	public ArrayList<Integer> getBundlePack() {
		return bundlePack;
	}
	
}
