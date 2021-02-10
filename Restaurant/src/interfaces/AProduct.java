package interfaces;

public abstract class AProduct {

	protected int id;
	protected String name;
	protected double price;
	protected boolean forCeliac;
	
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
