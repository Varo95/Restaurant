package models;

import java.util.ArrayList;
import java.util.Iterator;

public class Client extends Person implements Iterable{
	
	private ArrayList<String> address;
	private ArrayList<Integer> orders;
	private int points;
		
	public ArrayList<String> getAddress() {
		return address;
	}

	public void setAddress(ArrayList<String> address) {
		this.address = address;
	}

	public ArrayList<Integer> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Integer> orders) {
		this.orders = orders;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public String toString() {
		return super.toString()+"Client: Address="+address+"\n"+
				"Orders="+orders+"\n"+
				"Points="+points+"\n";
	}

	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
