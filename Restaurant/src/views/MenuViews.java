package views;

import controllers.MainMenuController;
import controllers.OrderMenuController;
import interfaces.AProduct;
import models.*;
import modelsrepository.Repository;
import modelsrepository.RepositoryClients;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MenuViews {

    RepositoryClients RC = RepositoryClients.getInstance();
    Repository R = Repository.getInstance();
    MainMenuController MC= new MainMenuController();
    OrderMenuController OC= new OrderMenuController();

    public void mainmenu() {
        System.out.println("Loading previus data...");
        int selected = 0;
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
            selected = I_O_Utilities.getInt();
            switch (selected) {
                case 1:
                    clientsManager();
                    break;
                case 2:
                    ordersManager();
                    break;
                case 3:
                    listMenu();
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 4:
                    System.out.println("Closing...");
                    MC.saveAllAndClose();
                    System.out.println("Thanks for using our app");
                    break;
                default:
            }
        } while (selected != 4);
    }

    public Order addOrderView(Client c, LocalDate id) {
        Order result = null;
        //pidiendo datos
        boolean exit = false;
        ArrayList<Line> order_lines = null;
        do {
            String choice = listMenu();
            System.out.println("Select a product from the list with the number of the product");
            System.out.println("It will be added to the order");
            System.out.println("NOTE: If you want to change ammount, please exit when you select the product instead of adding the same");
            int choice2 = I_O_Utilities.getInt();
            if (order_lines == null) {
                order_lines = new ArrayList<Line>();
            }
            switch (choice) {
                case "veganfood":
                    Food vegan = (Food) R.getAllVeganFood().get(choice2);
                    Line line = new Line(vegan, 1);
                    order_lines.add(line);
                    System.out.println("Product was added");
                    break;
                case "allfood":
                    Food food = (Food) R.getAllFood().get(choice2);
                    Line line1 = new Line(food, 1);
                    order_lines.add(line1);
                    System.out.println("Product was added");
                    break;
                case "alcoholics":
                    Drink alco = (Drink) R.getAllAlcoholicDrinks().get(choice2);
                    Line line3 = new Line(alco, 1);
                    order_lines.add(line3);
                    System.out.println("Product was added");
                    break;
                case "noalcoholics":
                    Drink noalco = (Drink) R.getAllNoAlcoholicDrinks().get(choice2);
                    Line line4 = new Line(noalco, 1);
                    order_lines.add(line4);
                    System.out.println("Product was added");
                    break;
                case "alldrinks":
                    Drink drink = (Drink) R.getAllDrinks().get(choice2);
                    Line line5 = new Line(drink, 1);
                    order_lines.add(line5);
                    System.out.println("Product was added");
                    break;
                case "celiacs":
                    AProduct celiac = R.getAllCeliacProducts().get(choice2);
                    Line line6 = new Line(celiac, 1);
                    order_lines.add(line6);
                    System.out.println("Product was added");
                    break;
                case "allproducts":
                    AProduct product = R.getAllProducts().get(choice2);
                    Line line7 = new Line(product, 1);
                    order_lines.add(line7);
                    System.out.println("Product was added");
                    break;
                default:
            }
            System.out.println("Would you like to add more LINES?");
            System.out.println("y=yes, another key to refuse");
            char choice3 = I_O_Utilities.getChar();
            if (choice3 == 'Y' | choice3 == 'y') {
                exit = true;
            }
        } while (exit);
        for (Line order_line : order_lines) {
            System.out.println(order_line.toString());
        }
        System.out.println("Now, ¿You want to change the amount of products?");
        System.out.println("y=yes, another key to refuse");
        char choice3 = I_O_Utilities.getChar();
        if (choice3 == 'Y' | choice3 == 'y') {
            boolean exit_edit_line = false;
            do {
                System.out.println("What line do u want to edit?");
                for (int i = 0; i < order_lines.size(); i++) {
                    System.out.println(i + "." + order_lines.get(i).toString());
                }
                System.out.println("Please type the line number to edit");
                int onselect = I_O_Utilities.getInt();
                boolean wrong_edit = false;
                do {
                    if (order_lines.contains(order_lines.get(onselect))) {
                        System.out.println(order_lines.get(onselect).toString());
                        System.out.println("Would you like to change the ammount or the product?");
                        System.out.println("a=ammount, p=product, another key will exit editing");
                        char edit_line = I_O_Utilities.getChar();
                        if (edit_line == 'A' | edit_line == 'a') {
                            //edit line ammount
                            System.out.println("Please type the new amount of the product");
                            int ammount=I_O_Utilities.getInt();
                            //OC.editLine()
                        } else if (edit_line == 'P' | edit_line == 'p') {
                            //edit line product

                            //OC.editLine()
                        } else {
                            wrong_edit = true;
                        }
                    } else {
                        System.out.println("Error that number doesn't match with a line number");
                        System.out.println("Please enter a valid line o press n to exit edit line");
                        char edit_line_out = I_O_Utilities.getChar();
                        if (edit_line_out == 'N' | edit_line_out == 'n') {
                            wrong_edit = false;
                        } else {
                            wrong_edit = true;
                        }
                    }
                } while (wrong_edit);
                System.out.println("Would you like to edit more lines?");
                System.out.println("y=yes, another key to refuse");
                char choice_3 = I_O_Utilities.getChar();
                if (choice_3 == 'Y' | choice_3 == 'y') {
                    exit_edit_line = false;
                } else {
                    exit_edit_line = true;
                }
            } while (!exit_edit_line);
        } else {

        }

        //rellenar datos de la orden
        //devuelve el order creado
        return result;
    }

    public void clientsManager() {
        boolean mg_cli = false;
        do {
            System.out.println("1. List Clients");
            System.out.println("2. Add new Client");
            System.out.println("3. Update Client");
            System.out.println("4. Remove Client");
            System.out.println("5. Search Client");
            System.out.println("6. Exit from Client Manager");
            int choice=I_O_Utilities.getInt();
            switch (choice){
                case 1:
                    System.out.println("Listing all clients");
                    listClients();
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 2:
                    System.out.println("Adding new client");
                    System.out.println("Please type the --DNI-- of the new client");
                    String dni=I_O_Utilities.getString();
                    System.out.println("Please type the --Name-- of the new client");
                    String name=I_O_Utilities.getString();
                    System.out.println("Please type the --Age-- of the new client");
                    int age=I_O_Utilities.getInt();
                    ArrayList<String> address=new ArrayList<String>();
                    System.out.println("How many address got the client? Please type a number");
                    int n_address=I_O_Utilities.getInt();
                    for(int i=0;i<n_address;i++){
                        System.out.println("Please type the --Address-- of the new client");
                        String tmp=I_O_Utilities.getString();
                        address.add(tmp);
                    }
                    System.out.println("Please type the --Points-- of the new client");
                    int points=I_O_Utilities.getInt();
                    Client c=new Client(dni,name,age,address,new ArrayList<Order>(),points);
                    if(MC.addClient(c)){
                    System.out.println("Client "+c.toString()+" was added successfully");}
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 3:
                    System.out.println("Editing Clients");
                    listClients();
                    System.out.println("Please type the number showed before the client to select it...");
                    System.out.println("Or type -1 to cancel edit client");
                    int selected1=I_O_Utilities.getInt();
                    boolean exit1=false;
                    do{
                    if(selected1==-1){
                        exit1=true;
                    }else{
                        if (RC.getAllClients().get(selected1)!=null) {
                            System.out.println(RC.getAllClients().get(selected1).toString());
                            System.out.println("1. Edit DNI");
                            System.out.println("2. Edit Name");
                            System.out.println("3. Edit Age");
                            System.out.println("4. Edit Address");
                            System.out.println("5. Edit Orders");
                            System.out.println("6. Edit Points");
                            System.out.println("7. Exit");
                            Client edited_client=null;
                            int edit=I_O_Utilities.getInt();
                            switch (edit){
                                case 1:
                                    System.out.println("Type the new DNI");
                                    String dni_edit=I_O_Utilities.getString();
                                    edited_client=new Client(dni_edit,null,-1,null,null,-1);
                                    MC.editClient(selected1,edited_client);
                                    System.out.println("Client updated successfully");
                                    System.out.println(RC.getAllClients().get(selected1).toString());
                                    break;
                                case 2:
                                    System.out.println("Type the new Name");
                                    String name_edit=I_O_Utilities.getString();
                                    edited_client=new Client(null,name_edit,-1,null,null,-1);
                                    MC.editClient(selected1,edited_client);
                                    System.out.println("Client updated successfully");
                                    System.out.println(RC.getAllClients().get(selected1).toString());
                                    break;
                                case 3:
                                    System.out.println("Type the new Age");
                                    int age_edit=I_O_Utilities.getInt();
                                    edited_client=new Client(null,null,age_edit,null,null,-1);
                                    MC.editClient(selected1,edited_client);
                                    System.out.println("Client updated successfully");
                                    System.out.println(RC.getAllClients().get(selected1).toString());
                                    break;
                                case 4:
                                    System.out.println("Type the ammount of address you want to add");
                                    int add_ammount=I_O_Utilities.getInt();
                                    ArrayList<String> address_edit=new ArrayList<>();
                                    for(int i=0;i<add_ammount;i++){
                                        System.out.println("Type the new Address");
                                        String ad_line=I_O_Utilities.getString();
                                        address_edit.add(ad_line);
                                    }
                                    edited_client=new Client(null,null,-1,address_edit,null,-1);
                                    MC.editClient(selected1,edited_client);
                                    System.out.println("Client updated successfully");
                                    System.out.println(RC.getAllClients().get(selected1).toString());
                                    break;
                                case 5:
                                    ordersManager(RC.getAllClients().get(selected1));
                                    break;
                                case 6:
                                    System.out.println("Type the ammount of points");
                                    int points_edit=I_O_Utilities.getInt();
                                    edited_client=new Client(null,null,-1,null,null,points_edit);
                                    MC.editClient(selected1,edited_client);
                                    System.out.println("Client updated successfully");
                                    System.out.println(RC.getAllClients().get(selected1).toString());
                                    break;
                                case 7: exit1=true; break;
                                default:
                            }
                        } else {
                            System.out.println("Something happens, client cannot be edited");
                        }
                    }
                    }while(!exit1);
                    break;
                case 4:
                    System.out.println("Removing clients");
                    boolean exit2=false;
                    do{
                        System.out.println("Client list");
                        listClients();
                        System.out.println("Please type the number showed before the client to select it...");
                        System.out.println("Or type -1 to cancel delete client");
                        int selected2=I_O_Utilities.getInt();
                        if(selected2<=-1){
                            exit2=true;
                        }else{
                            Client removed=RC.getAllClients().get(selected2);
                            if (MC.removeClient(selected2)) {
                                System.out.println("Client " + removed.toString() + "Deleted");
                            } else {
                                System.out.println("Something happens, client wasn't removed");
                            }
                        }
                    }while(!exit2);
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 5:
                    break;
                case 6: mg_cli=true; break;
                default:
            }
        } while (!mg_cli);
    }

    public void ordersManager() {
        int selected = 0;
        do {
            System.out.println("1. Add new Order selecting client");
            System.out.println("2. Edit orders from client");
            System.out.println("3. Remove orders from client");
            System.out.println("4. Save paid Orders");
            System.out.println("5. Save NOT paid Orders");
            System.out.println("6. Exit");
            selected = I_O_Utilities.getInt();
            switch (selected) {
                case 1:
                    Client a = selectClient();
                    addOrderView(a, LocalDate.now());
                    break;
                case 2:
                    Client b= selectClient();
                    ordersManager(b);
                    break;
                case 3:
                    listMenu();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
            }
        } while (selected != 6);

    }

    public String listMenu() {
        String result = null;
        System.out.println("¿Drink, food, from all for Celiac or from ALL?");
        System.out.println("1.Food");
        System.out.println("2.Drinks");
        System.out.println("3.All Celiac products");
        System.out.println("4.ALL products");
        int choice = I_O_Utilities.getInt();
        char choice_food = ' ';
        char choice_drinks = ' ';
        switch (choice) {
            case 1:
                System.out.println("¿Show only For Vegan food?");
                System.out.println("y=yes, another key to show all food");
                choice_food = I_O_Utilities.getChar();
                if (choice_food == 'y' || choice_food == 'Y') {
                    for (int i = 0, j = 0; i < R.getAllVeganFood().size(); i++, j++) {
                        System.out.println(j + "." + R.getAllVeganFood().get(i).toString());
                    }
                    result = "veganfood";
                } else {
                    for (int i = 0, j = 0; i < R.getAllFood().size(); i++, j++) {
                        System.out.println(j + "." + R.getAllFood().get(i).toString());
                    }
                    result = "allfood";
                }
                break;
            case 2:
                System.out.println("¿Show alcoholic, not alcoholic or all drinks?");
                System.out.println("a=alcoholic, n=not alcoholic, another key to show all drinks");
                choice_drinks = I_O_Utilities.getChar();
                if (choice_drinks == 'a' || choice_drinks == 'A') {
                    for (int i = 0, j = 0; i < R.getAllAlcoholicDrinks().size(); i++, j++) {
                        System.out.println(j + "." + R.getAllAlcoholicDrinks().get(i).toString());
                    }
                    result = "alcoholics";
                } else if (choice_drinks == 'n' || choice_drinks == 'N') {
                    for (int i = 0, j = 0; i < R.getAllNoAlcoholicDrinks().size(); i++, j++) {
                        System.out.println(j + "." + R.getAllNoAlcoholicDrinks().get(i).toString());
                    }
                    result = "noalcoholics";
                } else {
                    for (int i = 0, j = 0; i < R.getAllDrinks().size(); i++, j++) {
                        System.out.println(j + "." + R.getAllDrinks().get(i).toString());
                    }
                    result = "alldrinks";
                }
                break;
            case 3:
                for (int i = 0, j = 0; i < R.getAllCeliacProducts().size(); i++, j++) {
                    System.out.println(j + "." + R.getAllCeliacProducts().get(i).toString());
                }
                result = "celiacs";
                break;
            case 4:
                for (int i = 0, j = 0; i < R.getAllProducts().size(); i++, j++) {
                    System.out.println(j + "." + R.getAllProducts().get(i).toString());
                }
                result = "allproducts";
                break;
            default:
        }
        return result;
    }

    public Client selectClient() {
        Client result = null;
        boolean exit = false;
        do {
            System.out.println("Please select a criteria to find a user:");
            System.out.println("1.Show all Clients");
            System.out.println("2.Search client by name");
            System.out.println("3.Search client by DNI");
            int choice = I_O_Utilities.getInt();
            switch (choice) {
                case 1:
                    boolean case1_exit = false;
                    do {
                        ArrayList<Client> searched = RC.getAllClients();
                        for (int i = 0; i < searched.size(); i++) {
                            System.out.println(i + "." + searched.get(i).getName() + " -- " + searched.get(i).getDni() + " -- " + searched.get(i).getPoints());
                        }
                        System.out.println("Please type the number showed before the client name to select it");
                        int s_client = I_O_Utilities.getInt();
                        if (s_client>-1 && s_client <searched.size()) {
                            result = searched.get(s_client);
                            System.out.println("Client " + s_client + "." + searched.get(s_client).getName() + " -- " + searched.get(s_client).getDni() + " was selected");
                            case1_exit = true;
                            exit = true;
                        } else {
                            System.out.println("Number doesn't match with a number of list please retry again");
                            case1_exit = false;
                        }
                    } while (!case1_exit);
                    break;
                case 2:
                    boolean case2_exit = false;
                    do {
                        System.out.println("Please type the name of the client");
                        String name = I_O_Utilities.getString();
                        if (name.length() == 0) {
                            System.out.println("I can't search a void name");
                            System.out.println("Please try again");
                            case2_exit = false;
                        } else {
                            ArrayList<Client> searched = RC.searchClientByName(name);
                            for (int i = 0; i < searched.size(); i++) {
                                System.out.println(i + "." + searched.get(i).getName() + " -- " + searched.get(i).getDni() + " -- " + searched.get(i).getPoints());
                            }
                            System.out.println("Please type the number showed before the client name to select it");
                            int s_client = I_O_Utilities.getInt();
                            if (s_client>-1 && s_client <searched.size()) {
                                result = searched.get(s_client);
                                System.out.println("Client " + s_client + "." + searched.get(s_client).getName() + " -- " + searched.get(s_client).getDni() + " was selected");
                                case2_exit = true;
                                exit = true;
                            } else {
                                System.out.println("Number doesn't match with a number of list please retry again");
                                case2_exit = false;
                            }
                        }
                    } while (!case2_exit);
                    break;
                case 3:
                    boolean case3_exit = false;
                    do {
                        System.out.println("Please type the DNI of the client");
                        String dni = I_O_Utilities.getString();
                        if (dni.length() == 0) {
                            System.out.println("I can't search a void DNI");
                            System.out.println("Please try again");
                            case3_exit = false;
                        } else {
                            result = RC.searchClientByDni(dni);
                            System.out.println("Client "+result.toString()+" was selected");
                            case3_exit = true;
                            exit = true;
                        }
                    } while (!case3_exit);
                    break;
                default:
                    System.out.println("Please try again with another option");
                    exit = false;
            }
        } while (!exit);
        return result;
    }

    public void listClients(){
        for(int i=0;i<RC.getAllClients().size();i++){
            System.out.println(i+". "+RC.getAllClients().get(i).toString());
        }
    }

    public void ordersManager(Client c){
        int selected=0;
        do{
            System.out.println("Orders from "+c.getName()+" with DNI->"+c.getDni());
            System.out.println("1. List all orders");
            System.out.println("2. Remove orders");
            System.out.println("3. Update orders");
            System.out.println("4. Search orders by date");
            System.out.println("5. Exit");
            ArrayList<Order> clients_orders=OC.getOrders_byClient(c);
            int choice=I_O_Utilities.getInt();
            switch (choice){
                case 1:
                    for(int i=0;i<clients_orders.size();i++){
                        System.out.println(i+"."+clients_orders.get(i).toString());
                    }
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 2:
                    System.out.println("Please enter the number before the client to delete it");
                    int deleted=I_O_Utilities.getInt();
                    Order removed=null;
                    if(deleted<clients_orders.size()){
                        removed=clients_orders.remove(deleted);
                        System.out.println("Order "+removed.toString()+" was removed successfully");
                    }
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    selected=5;
                    break;
            }

        }while(selected!=5);
    }

}
