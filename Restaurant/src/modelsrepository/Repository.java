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

   
    /**
     * Función que devuelve todos los productos
     * @return Todos los productos
     */
    public ArrayList<AProduct> getAllProducts() {
        return products;
    }

    
    /**
     * Función que devuelve todas las bebidas
     * @return Todas las bebidas
     */
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

    
    /**
     * Función que devuelve todas las comidas
     * @return Todas las comidas
     */
    
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

    
    /**
     * Función que devuelve todas las bebidas no alcoholicas
     * @return Todas las bebidas no alcoholicas
     */
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

    
    /**
     * Función que devuelve todas las bebidas alcoholicas
     * @return Todas las bebidas alcoholicas
     */
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

    
    /**
     * Función que devuelve todas las comidas veganas
     * @return Todas las comidas veganas
     */
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

    
    /**
     * Función que devuelve todas las comidas para celiacos
     * @return Todas las comidas para celiacos
     */
    public ArrayList<AProduct> getAllCeliacProducts() {
        ArrayList<AProduct> result = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).isForCeliac()) {
                result.add(products.get(i));
            }
        }
        return result;
    }

    
    /**
     * Este metodo no se a desarrollado y no se a inplementado
     * @param 
     * @return
     */
    public ArrayList<AProduct> getBundleProduct(AProduct p) {
        ArrayList<AProduct> result = new ArrayList<>();
        Drink d1 = new Drink();
        Food f1 = new Food();
        for (AProduct product : products) {
            if (product.equals(p)) {
                if (product.getClass() == d1.getClass()) {
                    Drink d2 = (Drink) product;
                    for (int j = 0; j < d2.getBundlePack().size(); j++) {
                        int tmp = d2.getBundlePack().get(j);
                        result.add(products.get(tmp));
                    }
                } else if (product.getClass() == f1.getClass()) {
                    Food f2 = (Food) product;
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

    
    /**
     * Devuelve la lista de los productos encontrados
     * @param name nombre a buscar 
     * @return Lista de Productos 
     */
    public ArrayList<AProduct> searchProduct(String name) {
        ArrayList<AProduct> result = new ArrayList<>();
        for (AProduct product : products) {
            if (product.getName().equals(name)) {
                result.add(product);
            }
        }
        return result;
    }

    
    /**
     * Devuelve la lista de los productos encontrados
     * @param name nombre a buscar
     * @return Lista de Productos 
     */
    public ArrayList<AProduct> searchDrinks(String name) {
        ArrayList<AProduct> result = new ArrayList<>();
        for (AProduct product : products) {
            Drink tmp = new Drink();
            if (product.getClass() == tmp.getClass()) {
                if (product.getName().equals(name)) {
                    result.add(product);
                }
            }
        }
        return result;
    }

    /**
     * Devuelve la lista de los productos encontrados
     * @param name nombre a buscar
     * @return Lista de Productos 
     */
    public ArrayList<AProduct> searchFood(String name) {
        ArrayList<AProduct> result = new ArrayList<>();
        for (AProduct product : products) {
            Food tmp = new Food();
            if (product.getClass() == tmp.getClass()) {
                if (product.getName().equals(name)) {
                    result.add(product);
                }
            }
        }
        return result;
    }

}