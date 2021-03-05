package views;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import interfaces.AProduct;
import models.Client;
import models.Drink;
import models.Food;
import models.Order;
import models.Line;
import modelsrepository.Repository;
import modelsrepository.RepositoryClients;
import modelsrepository.RepositoryOrders;
import utilities.RepositoryUtils;

public class MenuViews {
	
	RepositoryClients RC=RepositoryClients.getInstance();
	RepositoryOrders RO=RepositoryOrders.getInstance();
	Repository R=Repository.getInstance();
	RepositoryUtils tmp=new RepositoryUtils();
	
	public void mainmenu() {
		System.out.println("Loading previus data...");
		RC=tmp.loadClients("clients.data");
		RO=tmp.loadOrders("orders.data");
		int selected=0;
		do {
		System.out.println("Welcome to...");
		System.out.println(" /\"      \\  /\"     \"| /\"       )(\"     _   \") /\"\"\\     (\"  _||_ \" | /\"      \\      /\"\"\\    (\\\"   \\|\"  \\(\"     _   \")");
		System.out.println("|:        |(: ______)(:   \\___/  )__/  \\\\__/ /    \\    |   (  ) : ||:        |    /    \\   |.\\\\   \\    |)__/  \\\\__/");
		System.out.println("|_____/   ) \\/    |   \\___  \\       \\\\_ /   /' /\\  \\   (:  |  | . )|_____/   )   /' /\\  \\  |: \\.   \\\\  |   \\\\_ /     ");
		System.out.println(" //      /  // ___)_   __/  \\\\      |.  |  //  __'  \\   \\\\ \\__/ //  //      /   //  __'  \\ |.  \\    \\. |   |.  |     ");
		System.out.println("|:  __   \\ (:      \"| /\" \\   :)     \\:  | /   /  \\\\  \\  /\\\\ __ //\\ |:  __   \\  /   /  \\\\  \\|    \\    \\ |   \\:  |     ");
		System.out.println("|__|  \\___) \\_______)(_______/       \\__|(___/    \\___)(__________)|__|  \\___)(___/    \\___)\\___|\\____\\)    \\__|     ");
		System.out.println("Please type a number acording below to work with...");
		System.out.println("1. Clients Manager");
		System.out.println("2. Orders Manager");
		System.out.println("3. List Menu");
		System.out.println("4. Exit App");
		selected=I_O_Utilities.getInt();
			switch(selected) {
			case 1:
				clientsManager();
				break;
			case 2:
				ordersManager();
				break;
			case 3:
				listMenu();
				break;
			case 4:
				System.out.println("Closing...");
				System.out.println("Saving clients...");
				tmp.saveClients("clients.data", RC);
				System.out.println("Saving orders...");
				tmp.saveOrders("orders.data", RO);
				System.out.println("Thanks for using our app");
				break;
			default:
			}
		}while(selected!=4);	
}

	//añadir producto->devuelve un producto
	//añadir pedido->devuelve un pedido
	public Order addOrderView(Client c, LocalDateTime id) {
		Order result=null;
		//pidiendo datos
		boolean exit=false;
		ArrayList<Line> order_lines=null;
		do {
			String choice = listMenu();

			System.out.println("Select a product from the list with the number of the product");
			System.out.println("It will be added to the order");
			System.out.println("NOTE: If you want to change ammount, please exit when you select the product instead of adding the same");
			int choice2 = I_O_Utilities.getInt();
			if(order_lines==null) {
				order_lines=new ArrayList<Line>();
			}

			switch (choice) {
			case "veganfood":
				Food vegan=(Food)R.getAllVeganFood().get(choice2);
				Line line=new Line(vegan,1);
				order_lines.add(line);
				System.out.println("Product was added");
				break;
			case "allfood":
				Food food=(Food)R.getAllFood().get(choice2);
				Line line1=new Line(food,1);
				order_lines.add(line1);
				System.out.println("Product was added");
				break;
			case "alcoholics":
				Drink alco=(Drink)R.getAllAlcoholicDrinks().get(choice2);
				Line line3=new Line(alco,1);
				order_lines.add(line3);
				System.out.println("Product was added");
				break;
			case "noalcoholics":
				Drink noalco=(Drink)R.getAllNoAlcoholicDrinks().get(choice2);
				Line line4=new Line(noalco,1);
				order_lines.add(line4);
				System.out.println("Product was added");
				break;
			case "alldrinks":
				Drink drink=(Drink)R.getAllDrinks().get(choice2);
				Line line5=new Line(drink,1);
				order_lines.add(line5);
				System.out.println("Product was added");
				break;
			case "celiacs":
				AProduct celiac=R.getAllCeliacProducts().get(choice2);
				Line line6=new Line(celiac,1);
				order_lines.add(line6);
				System.out.println("Product was added");
				break;
			case "allproducts":
				AProduct product=R.getAllProducts().get(choice2);
				Line line7=new Line(product,1);
				order_lines.add(line7);
				System.out.println("Product was added");
				break;
			default:
			}
			System.out.println("Would you like to add more LINES?");
			System.out.println("y=yes, another key to refuse");
			char choice3=I_O_Utilities.getChar(I_O_Utilities.getString());
			if(choice3=='Y' | choice3=='y') {
				exit=true;
			}
		} while (exit);
		for(int i=0;i<order_lines.size();i++) {
			System.out.println(order_lines.get(i).toString());
		}
		System.out.println("Now, ¿You want to change the amount of products?");
		System.out.println("y=yes, another key to refuse");
		char choice3=I_O_Utilities.getChar(I_O_Utilities.getString());
		if(choice3=='Y' | choice3=='y') {
			System.out.println("What line do u want to edit?");
			for(int i=0;i<order_lines.size();i++) {
				System.out.println(i+"."+order_lines.get(i).toString());
			}
		}else {
			
		}
		//llamar a edit line con el producto para modificar la cantidad
		//rellenar datos de la orden
		//devuelve el order creado
		return result;
	}
	
	public void clientsManager() {
		//nuevo pedido
		//listar pedidos
		//eliminar pedido
		//actualizar pedido
	}
	
	public void ordersManager() {
		int selected=0;
		do {
		System.out.println("1. Add new Order selecting client");
		System.out.println("2. Add product to an exist order");
		System.out.println("3. Edit existing Lines");
		System.out.println("4. Remove Lines");
		System.out.println("5. Set Address to an Order");
		System.out.println("6. Save paid Orders");
		System.out.println("7. Save NOT paid Orders");
		selected=I_O_Utilities.getInt();
			switch(selected) {
			case 1:
				//Client a=selectClient();
				addOrderView(null, null);
				break;
			case 2:
				ordersManager();
				break;
			case 3:
				listMenu();
				break;
			case 4:
				break;
			default:
			}
		}while(selected!=4);	
		
	}
	
	public String listMenu() {
		String result=null;
		System.out.println("¿Drink, food, from all for Celiac or from ALL?");
		System.out.println("1.Food");
		System.out.println("2.Drinks");
		System.out.println("3.All Celiac products");
		System.out.println("4.ALL products");
		int choice=I_O_Utilities.getInt();
		char choice_food=' ';
		char choice_drinks=' ';
		switch(choice) {
		case 1:
			System.out.println("¿Show only For Vegan food?");
			System.out.println("y=yes, another key to show all food");
			choice_food=I_O_Utilities.getChar(I_O_Utilities.getString());
			if(choice_food=='y' || choice_food=='Y') {
				for(int i=0,j=0;i<R.getAllVeganFood().size();i++,j++) {
					System.out.println(j+"."+R.getAllVeganFood().get(i).toString());
				}
				result="veganfood";
			}else {
				for(int i=0,j=0;i<R.getAllFood().size();i++,j++) {
					System.out.println(j+"."+R.getAllFood().get(i).toString());
				}
				result="allfood";
			}
			break;
		case 2:
			System.out.println("¿Show alcoholic, not alcoholic or all drinks?");
			System.out.println("a=alcoholic, n=not alcoholic, another key to show all drinks");
			choice_drinks=I_O_Utilities.getChar(I_O_Utilities.getString());
			if(choice_drinks=='a' || choice_drinks=='A') {
				for(int i=0,j=0;i<R.getAllAlcoholicDrinks().size();i++,j++) {
					System.out.println(j+"."+R.getAllAlcoholicDrinks().get(i).toString());
				}
				result="alcoholics";
			}else if(choice_drinks=='n' || choice_drinks=='N') {
				for(int i=0,j=0;i<R.getAllNoAlcoholicDrinks().size();i++,j++) {
					System.out.println(j+"."+R.getAllNoAlcoholicDrinks().get(i).toString());
				}
				result="noalcoholics";
			}else {
				for(int i=0,j=0;i<R.getAllDrinks().size();i++,j++) {
					System.out.println(j+"."+R.getAllDrinks().get(i).toString());
				}
				result="alldrinks";
			}
			break;
		case 3:
			for(int i=0,j=0;i<R.getAllCeliacProducts().size();i++,j++) {
				System.out.println(j+"."+R.getAllCeliacProducts().get(i).toString());
			}
			result="celiacs";
			break;
		case 4:
			for(int i=0,j=0;i<R.getAllProducts().size();i++,j++) {
				System.out.println(j+"."+R.getAllProducts().get(i).toString());
			}
			result="allproducts";
			break;
		default:
		}
		return result;
	}

}
