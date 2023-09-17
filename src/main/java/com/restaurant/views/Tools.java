package com.restaurant.views;


import com.restaurant.Main;
import com.restaurant.Start;
import com.restaurant.controllers.ClientController;
import com.restaurant.controllers.MainMenuController;
import com.restaurant.controllers.ProductController;
import com.restaurant.interfaces.AProduct;
import com.restaurant.models.Client;
import com.restaurant.models.Drink;
import com.restaurant.models.Food;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.materialdesign2.MaterialDesignA;
import org.kordamp.ikonli.materialdesign2.MaterialDesignB;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignF;
import org.kordamp.ikonli.materialdesign2.MaterialDesignG;
import org.kordamp.ikonli.materialdesign2.MaterialDesignT;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

public class Tools {
    private Tools() {
    }

    public static final Image glutenFree = getImageProduct("images/productTypes/gluten.png");
    public static final Image vegan = getImageProduct("images/productTypes/vegan.png");
    public static final Image alcoholic = getImageProduct("images/productTypes/alcoholic.png");
    private static final JMetro jMetroLight = new JMetro(Style.LIGHT);
    private static final Map<String, Stage> scenes = new ConcurrentHashMap<>();
    private static final Image icon = getImage("icon.png");

    public static void addCssAndIcon(final Stage window) {
        jMetroLight.setScene(window.getScene());
        window.getIcons().add(icon);
    }

    public static <T> Optional<Stage> getStageAndUpdateObjects(final String scene, final Optional<T> object) {
        final Stage stage = scenes.get(scene);
        if (stage != null) {
            final Object controller = stage.getUserData();
            if (controller instanceof ProductController p) {
                p.setProductOrClient(true, (AProduct) object.orElse(null));
            }else if(controller instanceof ClientController c){
                c.setProductOrClient(true, (Client) object.orElse(null));
            }
        }
        return Optional.ofNullable(stage);
    }

    public static void addStage(final String scene, final Stage stage, final Object controller) {
        stage.setUserData(controller);
        if (scene != null) scenes.put(scene, stage);
    }

    public static void updateMainStage() {
        getStageAndUpdateObjects("main", Optional.empty()).ifPresent(stage -> {
            final MainMenuController controller = (MainMenuController) stage.getUserData();
            controller.loadClients();
            controller.loadFoodAndDrinks();
        });
    }

    public static Image getImage(final String resPath) {
        return new Image(Objects.requireNonNull(Start.class.getResourceAsStream(resPath)));
    }

    private static Image getImageProduct(final String resPath) {
        return new Image(Objects.requireNonNull(Start.class.getResourceAsStream(resPath)), 30, 30, true, true);
    }

    public static FontIcon getIcon(final String value) {
        final FontIcon result = switch (value) {
            case "foodAndDrink" -> new FontIcon(MaterialDesignF.FOOD);
            case "food" -> new FontIcon(MaterialDesignF.FOOD_DRUMSTICK);
            case "drink" -> new FontIcon(MaterialDesignG.GLASS_MUG);
            case "clients" -> new FontIcon(MaterialDesignA.ACCOUNT_GROUP);
            case "individual" -> new FontIcon(MaterialDesignB.BADGE_ACCOUNT);
            case "tables" -> new FontIcon(MaterialDesignT.TABLE_FURNITURE);
            case "statistics" -> new FontIcon(MaterialDesignC.CHART_BAR);
            default -> new FontIcon(MaterialDesignA.AB_TESTING);
        };
        result.setIconSize(25);
        return result;
    }

    public static void onlyDoubleValue(final TextField tf) {
        tf.setTextFormatter(new TextFormatter<>(new StringConverter<>() {
            @Override
            public Double fromString(final String s) {
                if (s.isEmpty() || "-".equals(s) || ".".equals(s) || "-.".equals(s))
                    return 0.0;
                else
                    return Double.valueOf(s);
            }

            @Override
            public String toString(final Double d) {
                return d.toString();
            }
        }, 0.0, c -> Pattern.compile("-?(([1-9][0-9]*)|0)?(\\.[0-9]*)?").matcher(c.getControlNewText()).matches() ? c : null));
    }

    public static List<AProduct> createProducts() {
        final List<AProduct> result = new CopyOnWriteArrayList<>();
        result.add(new Drink("Coca Cola", 1.5, true, false));
        result.add(new Drink("Casera", 1.5, true, false));
        result.add(new Drink("Pepsi", 1.5, true, false));
        result.add(new Drink("Sake", 1.0, true, true));
        result.add(new Drink("Estrella Galicia", 1.80, false, true));
        result.add(new Drink("Fanta Naranja", 2.00, false, false));
        result.add(new Drink("Fanta Limon", 2.00, false, false));
        result.add(new Drink("Nestea", 2.30, false, false));
        result.add(new Drink("Tonica Schweeppes", 2.10, false, false));
        result.add(new Drink("Tinto de verano", 3.00, false, false));
        result.add(new Drink("Cruzcampo", 2.00, false, false));
        result.add(new Drink("Zumo", 1.50, false, false));
        result.add(new Drink("Mojito", 3.50, false, true));
        result.add(new Drink("Barcelo Cola", 5.00, false, true));
        result.add(new Drink("Batido chocolate", 2.00, false, false));
        result.add(new Drink("Batido vainilla", 2.00, false, false));
        result.add(new Drink("Batido fresa", 2.00, false, false));
        result.add(new Drink("Agua 1L", 1.00, false, false));
        result.add(new Drink("Agua 1.5L", 1.50, false, false));
        result.add(new Drink("Aquarious", 1.50, false, false));
        result.add(new Food("Kebab", 3.00, false, false));
        result.add(new Food("Durum", 3.00, false, false));
        result.add(new Food("Hamburguesa Vegana", 6.00, true, true));
        result.add(new Food("Hamburguesa Casera(Vacuno)", 5.0, true, false));
        result.add(new Food("Hamburguesa Casera(Cerdo)", 5.0, true, false));
        result.add(new Food("Cachopo", 3.5, false, false));
        result.add(new Food("Tortilla de patatas", 4.0, true, true));
        result.add(new Food("Arroz con tomate", 3.5, true, true));
        result.add(new Food("Arroz negro", 6.0, true, false));
        result.add(new Food("Pizza Jamon y Queso", 7.8, false, false));
        result.add(new Food("Cocido", 6.7, true, false));
        result.add(new Food("Patatas Bravas", 3.5, true, true));
        result.add(new Food("Gazpacho", 2.5, true, true));
        result.add(new Food("Croquetas de jamon", 2.0, false, false));
        result.add(new Food("Salmorejo", 3.0, true, true));
        result.add(new Food("Perrito caliente", 2.5, false, false));
        result.add(new Food("Ensalada de pollo", 3.0, true, false));
        result.add(new Food("Ensalada de pasta", 3.0, true, true));
        result.add(new Food("Brochetas de verduras", 2.0, true, true));
        result.add(new Food("Croquetas de calabacín", 4.0, true, true));
        return result;
    }

    public static List<Client> createClients(){
        final List<Client> result = new CopyOnWriteArrayList<>();
        result.add(new Client("99999999R", Main.bundle.getString("app.client.anonymous"), -1, List.of("Unknown"), -1));
        return result;
    }

}
