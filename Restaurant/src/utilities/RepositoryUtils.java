package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import interfaces.AProduct;
import modelsrepository.RepositoryClients;
import modelsrepository.RepositoryOrders;
import models.Client;
import models.Drink;
import models.Food;

public class RepositoryUtils {

	public RepositoryClients loadClients(String path) {
		RepositoryClients result = null;
		if (path != null && !path.isEmpty()) {
			FileInputStream f;
			try {
				f = new FileInputStream(path);
				ObjectInputStream of = new ObjectInputStream(f);
				result = (RepositoryClients) of.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean saveClients(String path, RepositoryClients clients) {
		boolean result = false;
		if (path != null && !path.isEmpty()) {
			FileOutputStream f;
			try {
				f = new FileOutputStream(path);
				ObjectOutputStream of = new ObjectOutputStream(f);
				of.writeObject(clients);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public RepositoryOrders loadOrders(String path) {
		RepositoryOrders result = null;
		if (path != null && !path.isEmpty()) {
			FileInputStream f;
			try {
				f = new FileInputStream(path);
				ObjectInputStream of = new ObjectInputStream(f);
				result = (RepositoryOrders) of.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return result;

	}

	public boolean saveOrders(String path, RepositoryOrders orders) {
		boolean result = false;
		if (path != null && !path.isEmpty()) {
			FileOutputStream f;
			try {
				f = new FileOutputStream(path);
				ObjectOutputStream of = new ObjectOutputStream(f);
				of.writeObject(orders);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public ArrayList<AProduct> getMenu(){
		ArrayList<AProduct> result=new ArrayList<>();
		ArrayList<Integer> bp=new ArrayList();
		//int id, String name, double cantidad, boolean for celiac, boolean for alcoholic, ArrayList<Integer>
		Drink a=new Drink(1,"Coca-Cola",1.5,true,false,bp);
		Drink b=new Drink(2,"Casera",1.5,true,false,bp);
		Drink c=new Drink(3,"Pepsi",1.5,true,false,bp);
		Drink d=new Drink(4,"Sake",1.0,true,true,bp);
		Drink e=new Drink(5,"Estrella Galicia",1.80,false,true,bp);
		Drink f=new Drink(6,"Fanta",2.00,false,false,bp);
		//int id, String name, doouble cantidad, boolean forceliac, boolean vegan, ArrayList<Integer>
		Food aa=new Food(1,"Kebab",3.00,false,false,bp);
		Food bb=new Food(2,"Durum",3.00,false,false,bp);
		Food cc=new Food(3,"Hamburguesa Vegana",6.00,true,true,bp);
		Food dd=new Food(4,"Hamburguesa Casera(Vacuno)",5.0,true,false,bp);
		Food ee=new Food(5,"Hamburguesa Casera(Cerdo)",5.0,true,false,bp);
		result.add(a);
		result.add(b);
		result.add(c);
		result.add(d);
		result.add(e);
		result.add(f);
		result.add(aa);
		result.add(bb);
		result.add(cc);
		result.add(dd);
		result.add(ee);
		return result;
	}
}
