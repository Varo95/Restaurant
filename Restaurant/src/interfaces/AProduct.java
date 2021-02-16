package interfaces;

import java.util.ArrayList;

public abstract class AProduct {

	protected  int id;
	protected String name;
	protected double price;
	protected boolean forCeliac;
	protected ArrayList<Integer> bundlePack;
	
	public AProduct() {
		
	}
	public AProduct(int id,String name, double price, boolean forCeliac, ArrayList<Integer> bundlePack) {
		this.id=id;
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
