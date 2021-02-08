package models;

import java.util.Arrays;

public class Client extends Person{
	
	private String[] address;
	private int[] orders;
	private int points;
	
	@Override
	public String toString() {
		return super.toString()+"Client: Address="+Arrays.toString(address) +"\n"+
				"Orders="+Arrays.toString(orders)+"\n"+
				"Points="+points+"\n";
	}
	
	

}
