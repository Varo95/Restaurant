package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import interfaces.AProduct;
import models.Order;
import modelsrepository.RepositoryClients;
import modelsrepository.RepositoryOrders;
import models.Drink;
import models.Food;

public class RepositoryUtils {

	public RepositoryClients loadClients(String path) {
		RepositoryClients result = null;
		File file = new File("clients.data");
		if (file.exists() && file.isFile() && path != null && !path.isEmpty()) {
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
				result=true;
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
		File file = new File("orders.data");
		if (file.exists() && file.isFile() && path != null && !path.isEmpty()) {
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
				result=true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public boolean saveOrders(String path, ArrayList<Order> orders){
		boolean result=false;
		if (path != null && !path.isEmpty()) {
			FileOutputStream f;
			try {
				f = new FileOutputStream(path);
				ObjectOutputStream of = new ObjectOutputStream(f);
				of.writeObject(orders);
				result=true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public ArrayList<AProduct> loadMenu(){
		ArrayList<AProduct> result=new ArrayList<>();
		ArrayList<Integer> bp=new ArrayList<>();
		//int id, String name, double cantidad, boolean for celiac, boolean for alcoholic, ArrayList<Integer>
		Drink a=new Drink(1,"Coca-Cola",1.5,true,false,bp);
		Drink b=new Drink(2,"Casera",1.5,true,false,bp);
		Drink c=new Drink(3,"Pepsi",1.5,true,false,bp);
		Drink d=new Drink(4,"Sake",1.0,true,true,bp);
		Drink e=new Drink(5,"Estrella Galicia",1.80,false,true,bp);
		Drink f=new Drink(6,"Fanta Naranja",2.00,false,false,bp);
		Drink g=new Drink(7,"Fanta Limon", 2.00, false, false, bp);
		Drink h=new Drink(8, "Nestea", 2.30, false, false, bp);
		Drink i=new Drink(9, "Tonica Schweeppes", 2.10, false, false, bp);
		Drink j=new Drink(10, "Tinto de verano", 3.00, false, false, bp);
		Drink k=new Drink(11, "Cruzcampo", 2.00, false, false, bp);
		Drink l=new Drink(12, "Zumo", 1.50, false, false, bp);
		Drink m=new Drink(13, "Mojito", 4.50, false, true, bp);
		Drink n=new Drink(14, "Barcelo Cola", 5.00, false, true, bp);
		Drink o=new Drink(15, "Batido chocolate", 2.00, false, false, bp);
		Drink p=new Drink(16, "Batido vainilla", 2.00, false, false, bp);
		Drink q=new Drink(17, "Batido fresa", 2.00, false, false, bp);
		Drink r=new Drink(18, "Agua 1L", 2.00, false, false, bp);
		Drink s=new Drink(19, "Agua 1.5L", 2.50, false, false, bp);
		Drink t=new Drink(20, "Aquarious", 2.00, false, false, bp);
		//int id, String name, doouble cantidad, boolean forceliac, boolean vegan, ArrayList<Integer>
		Food aa=new Food(1,"Kebab",3.00,false,false,bp);
		Food bb=new Food(2,"Durum",3.00,false,false,bp);
		Food cc=new Food(3,"Hamburguesa Vegana",6.00,true,true,bp);
		Food dd=new Food(4,"Hamburguesa Casera(Vacuno)",5.0,true,false,bp);
		Food ee=new Food(5,"Hamburguesa Casera(Cerdo)",5.0,true,false,bp);
		Food ff=new Food(6,"Cachopo",3.5,false,false,bp);
		Food gg=new Food(7,"Tortilla de patatas",4.0,true,true,bp);
		Food hh=new Food(8,"Arroz con tomate",3.5,true,true,bp);
		Food ii=new Food(9,"Arroz negro",6.0,true,false,bp);
		Food jj=new Food(10,"Pizza Jamon y Queso",7.8,false,false,bp);
		Food kk=new Food(11,"Cocido",6.7,true,false,bp);
		Food ll=new Food(12,"Patatas Bravas",3.5,true,true,bp);
		Food mm=new Food(13,"Gazpacho",2.5,true,true,bp);
		Food nn=new Food(14,"Croquetas de jamon",2.0,false,false,bp);
		Food oo=new Food(15,"Salmorejo",3.0,true,true,bp);
		Food pp=new Food(16,"Perrito caliente",2.5,false,false,bp);
		Food qq=new Food(17,"Ensalada de pollo",3.0,true,false,bp);
		Food rr=new Food(18,"Ensalada de pasta",3.0,true,true,bp);
		Food ss=new Food(19,"Brochetas de verduras",2.0,true,true,bp);
		Food tt=new Food(20,"Croquetas de calabacín",4.0,true,true,bp);
		//Anadiendo Drink
		result.addAll(Arrays.asList(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t));
		//Ananiendo Food
		result.addAll(Arrays.asList(aa, bb, cc, dd, ee, ff, gg, hh, ii, jj, kk, ll, mm, nn, oo, pp, qq, rr, ss, tt));
		return result;
	}
}
