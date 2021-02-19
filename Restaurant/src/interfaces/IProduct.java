package interfaces;

import java.util.ArrayList;

public interface IProduct {
	public ArrayList<Integer> getBundlePack();

	public String getName();

	public double getPrice();

	public boolean getIsForCeliac();
}
