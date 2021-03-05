package models;

import java.util.ArrayList;

import interfaces.AProduct;

public class Food extends AProduct {

	private boolean forVegan;
	
	public Food(int id,String name, double price, boolean forCeliac, boolean forVegan, ArrayList<Integer> bundlePack) {
		super(id,name, price, forCeliac, bundlePack);
		this.forVegan = forVegan;
	}
	public Food() {
		
		
	}
	public Food(boolean forVegan) {
		this.forVegan = forVegan;
		
	}

	public boolean isForVegan() {
		return forVegan;
	}
	
	public ArrayList<Integer> getBundlePack() {
		return bundlePack;
	}
	
	public boolean setBundlePack(int id) {
		boolean result=false;
		if(id!=0) {
			
		}
		return result;
	}
	@Override
	public boolean getIsForCeliac() {
		return super.isForCeliac();
	}
	@Override
	public String toString() {
		return name+" -- "+price+" €";
	}
	
}
