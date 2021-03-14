package views;

import controllers.MainMenuController;
import controllers.OrderMenuController;
import interfaces.AProduct;
import models.*;
import modelsrepository.Repository;
import modelsrepository.RepositoryClients;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class MenuViews {

    RepositoryClients RC = RepositoryClients.getInstance();
    Repository R = Repository.getInstance();
    MainMenuController MC = new MainMenuController();
    OrderMenuController OC = new OrderMenuController();

    /**
     * Este método no devuelve nada, pero es el menú principal de la aplicación con el que podemos acceder a los
     * diferentes submenús
     */
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

    /**
     * Este método añade una orden nueva a la lista de órdenes
     * @param c Cliente relacionado con el pedido
     * @param id fecha (cogerá la actual por "defecto").
     */
    public void addOrderView(Client c, LocalDate id) {
        Order result = null;
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
            } else {
                exit = false;
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
                System.out.println("Please type the line number to edit o type negative numbers to exit");
                int onselect = I_O_Utilities.getInt();
                boolean wrong_edit = false;
                do {
                    if (onselect > -1 && onselect < order_lines.size()) {
                        System.out.println(order_lines.get(onselect).toString());
                        System.out.println("Would you like to change the ammount or the product?");
                        System.out.println("a=ammount, p=product, another key will exit editing");
                        char edit_line = I_O_Utilities.getChar();
                        if (edit_line == 'A' | edit_line == 'a') {
                            System.out.println("Please type the new amount of the product");
                            int ammount = I_O_Utilities.getInt();
                            if (OC.editLine(order_lines.get(onselect), ammount, null)) {
                                System.out.println("Ammount succesfully changed");
                            } else {
                                System.out.println("Something happens, amount doesn't change");
                            }
                        } else if (edit_line == 'P' | edit_line == 'p') {
                            if (OC.editLine(order_lines.get(onselect), -1, selectProduct())) {
                                System.out.println("Product was changed!");
                            } else {
                                System.out.println("Product wasn't changed");
                            }
                        } else {
                            wrong_edit = true;
                        }
                    } else {
                        System.out.println("Error that number doesn't match with a line number");
                        System.out.println("Please enter a valid line o press n to exit edit line");
                        char edit_line_out = I_O_Utilities.getChar();
                        wrong_edit = !(edit_line_out == 'N' | edit_line_out == 'n');
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
        }
        double total_order = 0;
        for (Line order_line : order_lines) {
            total_order += (order_line.getProduct().getPrice() * order_line.getAmount());
        }
        System.out.println("Please select the client address to deliver the order");
        for (int i = 0; i < c.getAddress().size(); i++) {
            System.out.println(i + "." + c.getAddress().get(i));
        }
        int address_order_index = I_O_Utilities.getInt();
        String address_order = null;
        if (address_order_index > -1 && address_order_index < c.getAddress().size()) {
            address_order = c.getAddress().get(address_order_index);
        } else {
            System.out.println("Address not valid! Address is set to Unknow! Please edit the order before delivering");
            address_order = "Unknow";
        }
        System.out.println("Is the order already delivered?");
        System.out.println("Press y to yes, otherwhise, no");
        char delivered_order = I_O_Utilities.getChar();
        boolean order_delivered = false;
        if (delivered_order == 'Y' | delivered_order == 'y') {
            order_delivered = true;
        }
        System.out.println("Is the order already payed?");
        System.out.println("Press y to yes, otherwhise, no");
        char payed_order = I_O_Utilities.getChar();
        boolean order_payed = false;
        if (delivered_order == 'Y' | delivered_order == 'y') {
            order_payed = true;
        }
        System.out.println("How many points will add this order to the Client?");
        System.out.println("Please type the amount");
        int points = I_O_Utilities.getInt();
        c.addPoints(points);
        System.out.println("Points where added successfully");
        LocalDateTime order_date = id.atTime(ZonedDateTime.now().getHour(), ZonedDateTime.now().getMinute());
        result = new Order(c, order_lines, total_order, order_date, address_order, order_delivered, order_payed);
        System.out.println("Order with date and time at: " + order_date.toString());
        OC.addNewOrder(result);
        System.out.println("Order successfully added");
        System.out.println("Press any key to continue...");
        I_O_Utilities.getString();
    }

    /**
     * Este método es el submenú para manejar los clientes
     */
    public void clientsManager() {
        boolean mg_cli = false;
        do {
            System.out.println("1. List Clients");
            System.out.println("2. Add new Client");
            System.out.println("3. Update Client");
            System.out.println("4. Remove Client");
            System.out.println("5. Search Client");
            System.out.println("6. Exit from Client Manager");
            int choice = I_O_Utilities.getInt();
            switch (choice) {
                case 1:
                    System.out.println("Listing all clients");
                    listClients();
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 2:
                    System.out.println("Adding new client");
                    System.out.println("Please type the --DNI-- of the new client");
                    String dni = I_O_Utilities.getString();
                    System.out.println("Please type the --Name-- of the new client");
                    String name = I_O_Utilities.getString();
                    System.out.println("Please type the --Age-- of the new client");
                    int age = I_O_Utilities.getInt();
                    ArrayList<String> address = new ArrayList<String>();
                    System.out.println("How many address got the client? Please type a number");
                    int n_address = I_O_Utilities.getInt();
                    for (int i = 0; i < n_address; i++) {
                        System.out.println("Please type the --Address-- of the new client");
                        String tmp = I_O_Utilities.getString();
                        address.add(tmp);
                    }
                    System.out.println("Please type the --Points-- of the new client");
                    int points = I_O_Utilities.getInt();
                    Client c = new Client(dni, name, age, address, new ArrayList<Order>(), points);
                    if (MC.addClient(c)) {
                        System.out.println("Client " + c.toString() + " was added successfully");
                    }
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 3:
                    System.out.println("Editing Clients");
                    listClients();
                    System.out.println("Please type the number showed before the client to select it...");
                    System.out.println("Or type -1 to cancel edit client");
                    int selected1 = I_O_Utilities.getInt();
                    boolean exit1 = false;
                    do {
                        if (selected1 == -1) {
                            exit1 = true;
                        } else {
                            if (RC.getAllClients().get(selected1) != null) {
                                System.out.println(RC.getAllClients().get(selected1).toString());
                                System.out.println("1. Edit DNI");
                                System.out.println("2. Edit Name");
                                System.out.println("3. Edit Age");
                                System.out.println("4. Edit Address");
                                System.out.println("5. Edit Orders");
                                System.out.println("6. Edit Points");
                                System.out.println("7. Exit");
                                Client edited_client = null;
                                int edit = I_O_Utilities.getInt();
                                switch (edit) {
                                    case 1:
                                        System.out.println("Type the new DNI");
                                        String dni_edit = I_O_Utilities.getString();
                                        edited_client = new Client(dni_edit, null, -1, null, null, -1);
                                        MC.editClient(selected1, edited_client);
                                        System.out.println("Client updated successfully");
                                        System.out.println(RC.getAllClients().get(selected1).toString());
                                        break;
                                    case 2:
                                        System.out.println("Type the new Name");
                                        String name_edit = I_O_Utilities.getString();
                                        edited_client = new Client(null, name_edit, -1, null, null, -1);
                                        MC.editClient(selected1, edited_client);
                                        System.out.println("Client updated successfully");
                                        System.out.println(RC.getAllClients().get(selected1).toString());
                                        break;
                                    case 3:
                                        System.out.println("Type the new Age");
                                        int age_edit = I_O_Utilities.getInt();
                                        edited_client = new Client(null, null, age_edit, null, null, -1);
                                        MC.editClient(selected1, edited_client);
                                        System.out.println("Client updated successfully");
                                        System.out.println(RC.getAllClients().get(selected1).toString());
                                        break;
                                    case 4:
                                        System.out.println("Type the ammount of address you want to add");
                                        int add_ammount = I_O_Utilities.getInt();
                                        ArrayList<String> address_edit = new ArrayList<>();
                                        for (int i = 0; i < add_ammount; i++) {
                                            System.out.println("Type the new Address");
                                            String ad_line = I_O_Utilities.getString();
                                            address_edit.add(ad_line);
                                        }
                                        edited_client = new Client(null, null, -1, address_edit, null, -1);
                                        MC.editClient(selected1, edited_client);
                                        System.out.println("Client updated successfully");
                                        System.out.println(RC.getAllClients().get(selected1).toString());
                                        break;
                                    case 5:
                                        ordersManager(RC.getAllClients().get(selected1));
                                        System.out.println("Orders updated successfully");
                                        System.out.println(RC.getAllClients().get(selected1).toString());
                                        break;
                                    case 6:
                                        System.out.println("Type the ammount of points");
                                        int points_edit = I_O_Utilities.getInt();
                                        edited_client = new Client(null, null, -1, null, null, points_edit);
                                        MC.editClient(selected1, edited_client);
                                        System.out.println("Client updated successfully");
                                        System.out.println(RC.getAllClients().get(selected1).toString());
                                        break;
                                    case 7:
                                        exit1 = true;
                                        break;
                                    default:
                                }
                            } else {
                                System.out.println("Something happens, client cannot be edited");
                            }
                        }
                    } while (!exit1);
                    break;
                case 4:
                    System.out.println("Removing clients");
                    boolean exit2 = false;
                    do {
                        System.out.println("Client list");
                        listClients();
                        System.out.println("Please type the number showed before the client to select it...");
                        System.out.println("Or type -1 to cancel delete client");
                        int selected2 = I_O_Utilities.getInt();
                        if (selected2 <= -1) {
                            exit2 = true;
                        } else {
                            Client removed = RC.getAllClients().get(selected2);
                            if (MC.removeClient(selected2)) {
                                System.out.println("Client " + removed.toString() + "Deleted");
                            } else {
                                System.out.println("Something happens, client wasn't removed");
                            }
                        }
                    } while (!exit2);
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 5:
                    System.out.println("Searching clients by...");
                    System.out.println("1. By DNI");
                    System.out.println("2. By name");
                    System.out.println("3. Exit");
                    int number = I_O_Utilities.getInt();
                    if (number == 1) {
                        System.out.println("Please enter a DNI");
                        String dni_l = I_O_Utilities.getString();
                        if (RC.searchClientByDni(dni_l) != null) {
                            System.out.println(RC.searchClientByDni(dni_l).toString());
                        } else {
                            System.out.println("Client not found, please check out Client database");
                        }
                    } else if (number == 2) {
                        System.out.println("Please enter a name to search");
                        String name_l = I_O_Utilities.getString();
                        if (RC.searchClientByName(name_l) != null && RC.searchClientByName(name_l).size() >= 1) {
                            for (int i = 0; i < RC.searchClientByName(name_l).size(); i++) {
                                System.out.println(i + "." + RC.searchClientByName(name_l).get(i).toString());
                            }
                        } else {
                            System.out.println("Clients not found, please check out Client database");
                        }
                    }
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 6:
                    mg_cli = true;
                    break;
                default:
            }
        } while (!mg_cli);
    }

    /**
     * Este muestra el submenú de pedidos
     */
    public void ordersManager() {
        int selected = 0;
        do {
            System.out.println("0. List all orders");
            System.out.println("1. Add new Order selecting client");
            System.out.println("2. Edit orders from client");
            System.out.println("3. Save paid Orders");
            System.out.println("4. Save NOT paid Orders");
            System.out.println("5. Exit");
            selected = I_O_Utilities.getInt();
            switch (selected) {
                case 0:
                    for (int i = 0; i < OC.getAllOrders().size(); i++) {
                        System.out.println(i + "." + OC.getAllOrders().get(i).toString());
                    }
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 1:
                    Client a = selectClient();
                    addOrderView(a, LocalDate.now());
                    break;
                case 2:
                    Client b = selectClient();
                    ordersManager(b);
                    break;
                case 3:
                    if (OC.savePaid()) {
                        System.out.println("Orders saved properly");
                    } else {
                        System.out.println("Orders not saved. Somethign happens");
                    }
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 4:
                    if (OC.saveNotPaid()) {
                        System.out.println("Orders saved properly");
                    } else {
                        System.out.println("Orders not saved. Somethign happens");
                    }
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                default:
            }
        } while (selected != 5);

    }

    /**
     * Lista la carta de las comidas y bebidas que hay
     * @return Un string dependiendo de que tipo ha seleccionado el producto
     */
    public String listMenu() {
        String result = null;
        System.out.println("¿Drink, food, from all for Celiac or from ALL?");
        System.out.println("1.Food");
        System.out.println("2.Drinks");
        System.out.println("3.All Celiac products");
        System.out.println("4.ALL products");
        int choice = I_O_Utilities.getInt();
        char choice_food = ' ';
        char choice_drinks = ' ';
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

    /**
     * Selecciona un producto de la carta
     * @return un producto seleccionado
     */
    public AProduct selectProduct() {
        AProduct result = null;
        String choice = listMenu();
        System.out.println("Select a product from the list with the number of the product");
        System.out.println("It will be added to the order");
        System.out.println("NOTE: If you want to change ammount, please exit when you select the product instead of adding the same");
        int choice2 = I_O_Utilities.getInt();
        switch (choice) {
            case "veganfood":
                result = R.getAllVeganFood().get(choice2);
                System.out.println("Product was added");
                break;
            case "allfood":
                result = R.getAllFood().get(choice2);
                System.out.println("Product was added");
                break;
            case "alcoholics":
                result = R.getAllAlcoholicDrinks().get(choice2);
                System.out.println("Product was added");
                break;
            case "noalcoholics":
                result = R.getAllNoAlcoholicDrinks().get(choice2);
                System.out.println("Product was added");
                break;
            case "alldrinks":
                result = R.getAllDrinks().get(choice2);
                System.out.println("Product was added");
                break;
            case "celiacs":
                result = R.getAllCeliacProducts().get(choice2);
                System.out.println("Product was added");
                break;
            case "allproducts":
                result = R.getAllProducts().get(choice2);
                System.out.println("Product was added");
                break;
            default:
        }
        return result;
    }

    /**
     * En este menú se selecciona un cliente.
     * Cuando no se selcciona ninguno o no se encuentra alguno disponible, se intruduce un cliente por defecto
     * @return cliente seleccionado
     */
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
                        System.out.println("Or press -1 to exit");
                        int s_client = I_O_Utilities.getInt();
                        if (s_client > -1 && s_client < searched.size()) {
                            result = searched.get(s_client);
                            System.out.println("Client " + s_client + "." + searched.get(s_client).getName() + " -- " + searched.get(s_client).getDni() + " was selected");
                            case1_exit = true;
                            exit = true;
                        } else if (s_client <= -1) {
                            result = new Client("0000000Z", "Unknow", -1, new ArrayList<String>() {{
                                add("Unknow");
                            }}, new ArrayList<>(), 0);
                            System.out.println("A generic client was selected");
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
                            System.out.println("Or press -1 to exit");
                            int s_client = I_O_Utilities.getInt();
                            if (s_client > -1 && s_client < searched.size()) {
                                result = searched.get(s_client);
                                System.out.println("Client " + s_client + "." + searched.get(s_client).getName() + " -- " + searched.get(s_client).getDni() + " was selected");
                                case2_exit = true;
                                exit = true;
                            } else if (s_client <= -1) {
                                result = new Client("0000000Z", "Unknow", -1, new ArrayList<String>() {{
                                    add("Unknow");
                                }}, new ArrayList<>(), 0);
                                System.out.println("A generic client was selected");
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
                        System.out.println("Type exit or EXIT for exit");
                        String dni = I_O_Utilities.getString();
                        if (dni.length() == 0) {
                            System.out.println("I can't search a void DNI");
                            System.out.println("Please try again");
                            case3_exit = false;
                        } else if(dni.equals("EXIT") | dni.equals("exit")) {
                            result = new Client("0000000Z", "Unknow", -1, new ArrayList<String>() {{
                                add("Unknow");
                            }}, new ArrayList<>(), 0);
                            System.out.println("A generic client was selected");
                            case3_exit = true;
                            exit = true;
                        }else{
                            result = RC.searchClientByDni(dni);
                            System.out.println("Client " + result.toString() + " was selected");
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

    /**
     * Este método sirve para mostrar TODOS los clientes por pantalla
     */
    public void listClients() {
        for (int i = 0; i < RC.getAllClients().size(); i++) {
            System.out.println(i + ". " + RC.getAllClients().get(i).toString());
        }
    }

    /**
     * Este método sirve para manejar los pedidos de un cliente
     * @param c Cliente del que manejaremos sus pedidos
     */
    public void ordersManager(Client c) {
        int selected = 0;
        if (c != null && c.getName() != null && c.getDni() != null && c.getOrders() != null)
            do {
                System.out.println("Orders from " + c.getName() + " with DNI->" + c.getDni());
                System.out.println("1. List all orders");
                System.out.println("2. Remove orders");
                System.out.println("3. Update orders");
                System.out.println("4. Exit");
                ArrayList<Order> clients_orders = OC.getOrders_byClient(c);
                int choice = I_O_Utilities.getInt();
                switch (choice) {
                    case 1:
                        for (int i = 0; i < clients_orders.size(); i++) {
                            System.out.println(i + "." + clients_orders.get(i).toString());
                        }
                        System.out.println("Press any key to continue...");
                        I_O_Utilities.getString();
                        break;
                    case 2:
                        System.out.println("Please enter the number before the order to delete it");
                        int deleted = I_O_Utilities.getInt();
                        Order removed = null;
                        if (deleted < clients_orders.size()) {
                            removed = clients_orders.remove(deleted);
                            System.out.println("Order " + removed.toString() + " was removed successfully");
                        }
                        System.out.println("Press any key to continue...");
                        I_O_Utilities.getString();
                        break;
                    case 3:
                        System.out.println("Please enter the number before the order to edit it");
                        int edited = I_O_Utilities.getInt();
                        Order edited_order = null;
                        if (edited < clients_orders.size()) {
                            edited_order = clients_orders.get(edited);
                            orderEditor(edited_order);
                        }
                        System.out.println("Press any key to continue...");
                        I_O_Utilities.getString();
                        break;
                    case 4:
                        selected = 4;
                        break;
                    default:
                }
            } while (selected != 4);
    }

    /**
     * Este método edita un pedido el cual recibe como parámetro
     * @param o Pedido a modificar
     */
    public void orderEditor(Order o) {
        int option = 0;
        do {
            System.out.println(o.toString());
            System.out.println("1. Change client");
            System.out.println("2. Change products");
            System.out.println("3. Change total");
            System.out.println("4. Change date");
            System.out.println("5. Change address");
            System.out.println("6. Change delivered status");
            System.out.println("7. Change payed status");
            System.out.println("8. Exit");
            option = I_O_Utilities.getInt();
            switch (option) {
                case 1:
                    System.out.println("Please search and select a new client to the order");
                    Client c = selectClient();
                    o.setClient(c);
                    System.out.println("Client from order successfully changed!");
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 2:
                    boolean exit = false;
                    do {
                        for (int i = 0; i < o.getProducts().size(); i++) {
                            System.out.println(i + "." + o.getProducts().get(i).toString());
                        }
                        System.out.println("Please select the line u want to edit");
                        System.out.println("Or type -1 to exit");
                        int line_selected = I_O_Utilities.getInt();
                        if (line_selected > -1 && line_selected < o.getProducts().size()) {
                            Line toedit = o.getProducts().get(line_selected);
                            System.out.println(toedit.toString());
                            System.out.println("Would you like to change the ammount or the product?");
                            System.out.println("a=ammount, p=product, another key will exit editing");
                            char edit_line = I_O_Utilities.getChar();
                            if (edit_line == 'A' | edit_line == 'a') {
                                System.out.println("Please type the new amount of the product");
                                int ammount = I_O_Utilities.getInt();
                                if (OC.editLine(toedit, ammount, null)) {
                                    System.out.println("Ammount succesfully changed");
                                } else {
                                    System.out.println("Something happens, amount doesn't change");
                                }
                            } else if (edit_line == 'P' | edit_line == 'p') {
                                if (OC.editLine(toedit, -1, selectProduct())) {
                                    System.out.println("Product was changed!");
                                } else {
                                    System.out.println("Product wasn't changed");
                                }
                            }
                        } else if (line_selected <= -1) {
                            exit = true;
                        }
                    } while (!exit);
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 3:
                    System.out.println("The total price of the order is " + o.getTotal() + " $");
                    System.out.println("You want to edit it? Press Y to edit");
                    char choice3 = I_O_Utilities.getChar();
                    if (choice3 == 'Y' | choice3 == 'y') {
                        System.out.println("Put the expected price below: ");
                        double cant = I_O_Utilities.getFloat();
                        o.setTotal(cant);
                        System.out.println("Total was changed to: " + o.getTotal());
                    } else {
                        System.out.println("The total order doesn't change");
                    }
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 4:
                    boolean exit_case4 = false;
                    do {
                        System.out.println("Please enter the new date of the order ");
                        System.out.println("For example: 2011-12-03T10:15:30+01:00[Europe/Paris]");
                        String new_date = I_O_Utilities.getString();
                        try {
                            o.setDate(LocalDateTime.parse(new_date, DateTimeFormatter.ISO_ZONED_DATE_TIME));
                            exit_case4 = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Please type again a date");
                            exit_case4 = false;
                        }
                    } while (exit_case4);
                    System.out.println("Date successfully changed!");
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 5:
                    System.out.println("Type the new address");
                    String new_address = I_O_Utilities.getString();
                    o.setAddress(new_address);
                    System.out.println("Address changed successfully");
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 6:
                    if (o.isPayed()) {
                        o.setPayed(false);
                        System.out.println("Order marked as not payed");
                    } else {
                        o.setPayed(true);
                        System.out.println("Order marked as payed");
                    }
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                case 7:
                    if (o.isDelivered()) {
                        o.setDelivered(false);
                        System.out.println("Order marked as not delivered");
                    } else {
                        o.setDelivered(true);
                        System.out.println("Order marked as delivered");
                    }
                    System.out.println("Press any key to continue...");
                    I_O_Utilities.getString();
                    break;
                default:
            }
        } while (option != 8);
    }

}
