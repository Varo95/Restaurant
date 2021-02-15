package interfaces;

import java.util.ArrayList;

public abstract class AProduct {

	protected static int id;
	protected String name;
	protected double price;
	protected boolean forCeliac;
	protected ArrayList<Integer> bundlePack;
	
	public AProduct(String name, double price, boolean forCeliac, ArrayList<Integer> bundlePack) {
		this.name = name;
		this.price = price;
		this.forCeliac = forCeliac;
		this.bundlePack = bundlePack;
	}

	public String getName() {
		return name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public boolean isForCeliac() {
		return forCeliac;
	}
	
}
