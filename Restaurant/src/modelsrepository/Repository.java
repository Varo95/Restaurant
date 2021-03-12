package modelsrepository;

import interfaces.AProduct;
import models.Drink;
import models.Food;
import utilities.RepositoryUtils;

import java.util.ArrayList;

public class Repository {

    private ArrayList<AProduct> products;
    private RepositoryUtils RU = new RepositoryUtils();

    private static Repository instance;

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
        }
        return instance;
    }

    private Repository() {
        this.products = RU.loadMenu();
    }

    // TODAS LOS PRODUCTOS
    public ArrayList<AProduct> getAllProducts() {
        return products;
    }

    // TODAS LAS BEBIDAS
    public ArrayList<AProduct> getAllDrinks() {
        ArrayList<AProduct> result = new ArrayList<>();
        Drink tmp = new Drink();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getClass() == tmp.getClass()) {
                result.add(products.get(i));
            }
        }
        return result;
    }

    // TODAS LAS COMIDAS
    public ArrayList<AProduct> getAllFood() {
        ArrayList<AProduct> result = new ArrayList<>();
        Food tmp = new Food();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getClass() == tmp.getClass()) {
                result.add(products.get(i));
            }
        }
        return result;
    }

    // TODAS LAS BEBIDAS NO ALCOHOLICAS
    public ArrayList<AProduct> getAllNoAlcoholicDrinks() {
        ArrayList<AProduct> result = new ArrayList<>();
        Drink tmp = new Drink();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getClass() == tmp.getClass()) {
                Drink tmp2 = (Drink) products.get(i);
                if (!tmp2.isAlcoholic()) {
                    result.add(products.get(i));
                }
            }
        }
        return result;
    }

    // TODAS LAS BEBIDAS ALCOHOLICAS
    public ArrayList<AProduct> getAllAlcoholicDrinks() {
        ArrayList<AProduct> result = new ArrayList<>();
        Drink tmp = new Drink();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getClass() == tmp.getClass()) {
                Drink tmp2 = (Drink) products.get(i);
                if (tmp2.isAlcoholic()) {
                    result.add(products.get(i));
                }
            }
        }
        return result;
    }

    // TODAS LAS COMIDAS VEGANAS
    public ArrayList<AProduct> getAllVeganFood() {
        ArrayList<AProduct> result = new ArrayList<>();
        Food tmp = new Food();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getClass() == tmp.getClass()) {
                Food tmp2 = (Food) products.get(i);
                if (tmp2.isForVegan()) {
                    result.add(products.get(i));
                }
            }
        }
        return result;
    }

    // TODAS LAS COMIDAS PARA CELIACOS
    public ArrayList<AProduct> getAllCeliacProducts() {
        ArrayList<AProduct> result = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).isForCeliac()) {
                result.add(products.get(i));
            }
        }
        return result;
    }

    // BUSCAR PACK INSERTANDO PRODUCTO
    public ArrayList<AProduct> getBundleProduct(AProduct p) {
        ArrayList<AProduct> result = new ArrayList<>();
        Drink d1 = new Drink();
        Food f1 = new Food();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).equals(p)) {
                if (products.get(i).getClass() == d1.getClass()) {
                    Drink d2 = (Drink) products.get(i);
                    for (int j = 0; j < d2.getBundlePack().size(); j++) {
                        int tmp = d2.getBundlePack().get(j);
                        result.add(products.get(tmp));
                    }
                } else if (products.get(i).getClass() == f1.getClass()) {
                    Food f2 = (Food) products.get(i);
                    for (int j = 0; j < f2.getBundlePack().size(); j++) {
                        int tmp = f2.getBundlePack().get(j);
                        result.add(products.get(tmp));
                    }
                }
                //TODO things
            }
        }

        return result;
    }

    // BUSCAR PRODUCTO POR EL NOMBRE
    public ArrayList<AProduct> searchProduct(String name) {
        ArrayList<AProduct> result = null;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(name)) {
                result.add(products.get(i));
            }
        }
        return result;
    }

    // BUSCAR BEBIDA POR EL NOMBRE
    public ArrayList<AProduct> searchDrinks(String name) {
        ArrayList<AProduct> result = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Drink tmp = new Drink();
            if (products.get(i).getClass() == tmp.getClass()) {
                if (products.get(i).getName().equals(name)) {
                    result.add(products.get(i));
                }
            }
        }
        return result;
    }

    // BUSCAR BEBIDA POR EL NOMBRE
    public ArrayList<AProduct> searchFood(String name) {
        ArrayList<AProduct> result = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            Food tmp = new Food();
            if (products.get(i).getClass() == tmp.getClass()) {
                if (products.get(i).getName().equals(name)) {
                    result.add(products.get(i));
                }
            }
        }
        return result;
    }

}