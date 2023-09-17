package com.restaurant.controllers;

import com.restaurant.Main;
import com.restaurant.interfaces.AProduct;
import com.restaurant.interfaces.IMainMenuController;
import com.restaurant.models.Client;
import com.restaurant.models.Drink;
import com.restaurant.models.Food;
import com.restaurant.models.Order;
import com.restaurant.repositorys.Repository;
import com.restaurant.repositorys.RepositoryClients;
import com.restaurant.repositorys.RepositoryOrders;
import com.restaurant.utilities.ClientsCellFactory;
import com.restaurant.utilities.RepositoryUtils;
import com.restaurant.views.Tools;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainMenuController implements IMainMenuController {

    private static final Repository repository = Repository.getInstance();

    RepositoryOrders orders = RepositoryOrders.getInstance();
    RepositoryClients clients = RepositoryClients.getInstance();

    @FXML
    private Button btnAddProduct, btnAddClient;
    @FXML
    private TextField tfSearch;
    @FXML
    private ListView<Client> lvClients;
    @FXML
    private Tab tabFood, tabClients, tabIndividual, tabTables;
    @FXML
    private GridPane productsContent;

    @FXML
    protected void initialize() {
        this.addButtonsIcons();
        this.loadFoodAndDrinks();
        this.loadClients();
        this.btnAddProduct.setOnAction(event-> Main.loadScene(Optional.empty(),"views/product", Main.bundle.getString("app.product.new"), false, true));
        this.btnAddClient.setOnAction(event-> Main.loadScene(Optional.empty(),"views/client", Main.bundle.getString("app.client.new"), false, true));
        this.lvClients.setCellFactory(new ClientsCellFactory<>());
    }

    private void addButtonsIcons() {
        this.tabFood.setGraphic(Tools.getIcon("foodAndDrink"));
        this.tabClients.setGraphic(Tools.getIcon("clients"));
        this.tabIndividual.setGraphic(Tools.getIcon("individual"));
        this.tabTables.setGraphic(Tools.getIcon("tables"));
        //btn_statistics.setGraphic(Tools.getIcon("statistics"));
    }

    public void loadFoodAndDrinks() {
        this.productsContent.getChildren().removeIf(Objects::nonNull);
        int columnCount = 0;
        int rowCount = 0;
        final List<AProduct> products = repository.getAll(AProduct.class);
        for (final AProduct a : products) {
            final GridPane gridForProduct = new GridPane();
            gridForProduct.setVgap(15);
            if (columnCount > 3) {
                columnCount = 0;
                rowCount++;
            }
            final Button itemButton = this.prepareProductButton(a);
            final HBox icons = this.prepareProductIcons(a);
            gridForProduct.addRow(0, itemButton);
            gridForProduct.addRow(1, icons);
            for (final Node n : gridForProduct.getChildren()) {
                GridPane.setHalignment(n, HPos.CENTER);
            }
            gridForProduct.setAlignment(Pos.CENTER);
            this.productsContent.add(gridForProduct, columnCount++, rowCount);
        }
    }

    private Button prepareProductButton(final AProduct product) {
        final boolean isFood = product instanceof Food;
        final String item = product.getName() + "\n" +
                Main.bundle.getString("app.general.price") + " : " +
                product.getPrice() + " €";
        final Button result = new Button(item);
        result.setGraphic(Tools.getIcon(isFood ? "food" : "drink"));
        result.setWrapText(true);
        result.setMinHeight(125);
        result.setMaxHeight(125);
        result.setMinWidth(125);
        result.setMaxWidth(125);
        result.setOnAction(event-> Main.loadScene(Optional.of(product),"views/product", Main.bundle.getString("app.product.edit"), false, true));
        return result;
    }

    private HBox prepareProductIcons(final AProduct product) {
        final List<ImageView> result = new CopyOnWriteArrayList<>();
        if (product.isForCeliac()) {
            result.add(new ImageView(Tools.glutenFree));
        }
        if (product instanceof Food f && f.isForVegan()) {
            result.add(new ImageView(Tools.vegan));
        } else if (product instanceof Drink d && d.isAlcoholic()) {
            result.add(new ImageView(Tools.alcoholic));
        }
        final HBox box = new HBox();
        box.setSpacing(25);
        box.setMinHeight(30);
        box.setPrefHeight(30);
        box.setMaxHeight(30);
        box.getChildren().addAll(result);
        return box;
    }

    public void loadClients(){
        this.lvClients.getItems().clear();
        this.lvClients.getItems().addAll(repository.getAll(Client.class));
    }

    /**
     * A�adir un cliente.
     *
     * @param c Cliente a a�adir.
     * @return True si se ha a�adido, false si no.
     */
    public boolean addClient(Client c) {
        boolean result = false;
        if (c != null) {
            clients.addClient(c);
            result = true;
        }
        return result;
    }

    /**
     * Editar cliente.
     *
     * @param id El identificador del cliente.
     * @param c  El cliente a editar.
     * @return True si se ha editado, false si no.
     */
    public boolean editClient(int id, Client c) {
        boolean result = false;
        if (id > -1 && c != null && clients.getAllClients().get(id) != null) {
            if (c.getAddress() != null) {
                clients.getAllClients().get(id).setAddress(c.getAddress());
                result = true;
            } else if (c.getOrders() != null) {
                clients.getAllClients().get(id).setOrders(c.getOrders());
                result = true;
            } else if (c.getPoints() > -1) {
                clients.getAllClients().get(id).setPoints(c.getPoints());
                result = true;
            } else if (c.getName() != null) {
                clients.getAllClients().get(id).setName(c.getName());
                result = true;
            } else if (c.getDni() != null) {
                clients.getAllClients().get(id).setDni(c.getDni());
                result = true;
            } else if (c.getAge() > -1) {
                clients.getAllClients().get(id).setAge(c.getAge());
                result = true;
            }
        }
        return result;
    }

    /**
     * Borrar cliente.
     *
     * @param id El identificador del cliente.
     * @return True si se ha eliminado, false si no.
     */
    public boolean removeClient(int id) {
        boolean result = false;
        if (id > -1 && clients.getAllClients().get(id) != null) {
            clients.getAllClients().remove(id);
            result = true;
        }
        return result;
    }


    @Override
    /**
     * Nueva orden.
     * @param c Recibe un cliente.
     * @param o Recibe una orden.
     * @return True si se ha creado la orden , false si no.
     */
    public boolean newOrder(Client c, Order o) {
        boolean result = false;
        if (o != null && o.getClient() == null) {
            o.setClient(c);
            o.setDate(LocalDateTime.now());
            orders.getAllOrders().add(o);
            result = true;
        }
        return result;
    }

    @Override
    /**
     * Cambiar orden.
     * @param c Recibe un cliente.
     * @param id Redibe un identificador de order.
     * @return True si se ha cambiado la orden, false si no.
     */
    public boolean changeOrder(Client c, int id) {
        boolean result = false;
        if (c != null && id > -1 && orders.getAllOrders().get(id) != null) {

            orders.getAllOrders().get(id).setClient(c);
            result = true;
        }
        return result;
    }

    @Override
    /**
     * Cambiar orden.
     * @param d Recibe la hora local.
     * @param id Recibe un identificador de orders.
     * @return True si se ha cambiado la orden, false si no.
     */
    public boolean changeOrder(LocalDateTime d, int id) {
        boolean result = false;
        if (d != null && id > -1) {
            orders.getAllOrders().get(id).setDate(d);
            result = true;
        }
        return result;
    }

    @Override
    /**
     * Cambiar orden.
     * @param d Recibe la hora local.
     * @param id Recibe un identificador de orders.
     * @param c Recibe un cliente.
     * @return True si se ha cambiado la orden, false si no.
     */
    public boolean changeOrder(Client c, LocalDateTime d, int id) {
        boolean result = false;
        if (c != null && d != null && id > -1) {
            orders.getAllOrders().get(id).setClient(c);
            orders.getAllOrders().get(id).setDate(d);
            result = true;
        }
        return result;
    }

    @Override
    /**
     * Borrar orden.
     *@param c Recibe un cliente.
     *@param id Recibe un identificador de orders.
     *@return True si se ha eliminador la orden, false si no. 
     */
    public boolean deleteOrder(Client c, int id) {
        boolean result = false;
        if (c != null) {
            for (int i = 0; i < orders.getAllOrders().size() && !result; i++) {
                if (orders.getAllOrders().get(i).getClient().equals(c)) {
                    orders.getAllOrders().remove(i);
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    /**
     * Borrar orden.
     * @param id Recibe un identificador de orders.
     * @param d Recibe la hora local.
     * @return True si se ha eliminador la orden, false si no. 
     */
    public boolean deleteOrder(LocalDateTime d, int id) {
        boolean result = false;
        if (d != null) {
            for (int i = 0; i < orders.getAllOrders().size() && !result; i++) {
                if (orders.getAllOrders().get(i).getDate().equals(d)) {
                    orders.getAllOrders().remove(i);
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    /**
     * Borrar orden.
     * @param id Recibe un identificador de orders.
     * @param d Recibe la hora local.
     * @param c Recibe un cliente.
     * @return True si se ha eliminador la orden, false si no.
     */
    public boolean deleteOrder(Client c, LocalDateTime d, int id) {
        boolean result = false;
        if (c != null && d != null) {
            for (int i = 0; i < orders.getAllOrders().size() && !result; i++) {
                if (orders.getAllOrders().get(i).getClient().equals(c) && orders.getAllOrders().get(i).getDate().equals(d)) {
                    orders.getAllOrders().remove(i);
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    /**
     * Dinero recaudado en el dia.
     * @return Devuelbe un el dinero recaudado en el dia.
     */
    public double cashToDay() {
        double result = 0;
        int day = Calendar.DAY_OF_WEEK_IN_MONTH;
        for (int i = 0; i < orders.getAllOrders().size(); i++) {
            int day1 = orders.getAllOrders().get(i).getDate().getDayOfMonth();
            boolean isPayed = orders.getAllOrders().get(i).isPayed();
            if (day == day1 && isPayed) {
                result += orders.getAllOrders().get(i).getTotal();
            }
        }
        return result;
    }

    @Override
    /**
     * Dinero recaudado en el mes
     * @return Devuelbe un el dinero recaudado en el mes.
     */
    public double cashThisMonth() {
        double result = 0;
        int moth = Calendar.MONTH + 1;
        for (int i = 0; i < orders.getAllOrders().size(); i++) {
            int moth1 = orders.getAllOrders().get(i).getDate().getMonthValue();
            boolean isPayed = orders.getAllOrders().get(i).isPayed();
            if (moth == moth1 && isPayed) {
                result += orders.getAllOrders().get(i).getTotal();
            }
        }
        return result;
    }

    /*
     * Dinero total recaudado.
     * @return Dinero total recaudado
     */
    @Override

    public double cashTotal() {
        return orders.getAllInput();
    }

    /**
     * Array de los pedidos no pagados.
     *
     * @return Array de los pedidos.
     */
    @Override
    public ArrayList<Order> viewOrdersNotPayed() {
        ArrayList<Order> result = new ArrayList<Order>();
        for (int i = 0; i < orders.getAllOrders().size(); i++) {
            boolean isPayed = orders.getAllOrders().get(i).isPayed();
            if (!isPayed) {
                result.add(orders.getAllOrders().get(i));
            }
        }
        return result;
    }

    /**
     * Array de los pedidos pendientes de entregar.
     *
     * @return Array de los pedidos.
     */
    @Override
    public ArrayList<Order> viewOrdersPendingDelivered() {
        ArrayList<Order> result = new ArrayList<>();
        for (int i = 0; i < orders.getAllOrders().size(); i++) {
            boolean delivered = orders.getAllOrders().get(i).isDelivered();
            if (!delivered) {
                result.add(orders.getAllOrders().get(i));
            }
        }
        return result;
    }

    /**
     * Este metodo guarda todo antes de salir de la aplicacion,
     */
    @Override
    public void saveAllAndClose() {
        RepositoryUtils tmp = new RepositoryUtils();
        tmp.saveClients("clients.data", clients);
        tmp.saveOrders("orders.data", orders);
    }


}