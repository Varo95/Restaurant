package controllers;

import java.util.ArrayList;

import interfaces.AProduct;
import models.Drink;
import models.Food;


public class Repository {

private  ArrayList<AProduct> products;
    
    public static Repository instance;

    public static Repository getInstance() {
        if(instance==null) {
            instance = new Repository();
        }
        return instance;
    }
    
    private Repository() {
        this.products=new ArrayList<AProduct>();
    }
    
    public Repository(ArrayList<AProduct> products) {
        this.products = products;
    }
	//TODAS LOS PRODUCTOS
	public ArrayList<AProduct> getAllProducts() {
		
		return products;
	}
	//TODAS LAS BEBIDAS
	public ArrayList<AProduct> getAllDrinks() {
		
		ArrayList<AProduct> result = null;
		Drink tmp=new Drink();
		
		for(int i=0; i<products.size();i++) {
			
			if (products.get(i).getClass() == tmp.getClass()) {
				
				result.add(products.get(i));
			}
		}
		return result;
		
	}
	//TODAS LAS COMIDAS
	public ArrayList<AProduct> getAllFood(){
		ArrayList<AProduct> result = null;
		Food tmp=new Food();
		
		for(int i=0; i<products.size();i++) {
			
			if(products.get(i).getClass() == tmp.getClass()) {
				
				result.add(products.get(i));
			}
		}
		
		return result;
	}
	//TODAS LAS BEBIDAS NO ALCOHOLICAS
	public ArrayList<AProduct> getAllNoAlcoholicDrinks() {
		
		ArrayList<AProduct> result = null;
		Drink tmp=new Drink();
		
		
		for(int i=0; i<products.size();i++) {
			
			if (products.get(i).getClass() == tmp.getClass()) {
				Drink tmp2=(Drink) products.get(i);
				if (!tmp2.isAlcoholic()) {
					
					result.add(products.get(i));
				}
			}
		}
		return result;
	}
	//TODAS LAS BEBIDAS ALCOHOLICAS
	public ArrayList<AProduct> getAllAlcoholicDrinks(){
		ArrayList<AProduct> result = null;
		Drink tmp=new Drink();
		
		for(int i=0;i<products.size();i++) {
			
			if(products.get(i).getClass() == tmp.getClass()) {
				
				Drink tmp2=(Drink) products.get(i);
				
				if(tmp2.isAlcoholic()) {
					
					result.add(products.get(i));
				}
			}
		}
		
		return result;
	}
	//TODAS LAS COMIDAS VEGANAS
	public ArrayList<AProduct> getAllVeganFood(){
		ArrayList<AProduct> result = null;
		Food tmp=new Food();
		for(int i=0; i<products.size();i++) {
			
			if(products.get(i).getClass() == tmp.getClass()) {
				
				Food tmp2=(Food) products.get(i);
				if(tmp2.isForVegan()) {
					
					result.add(products.get(i));
				}
			}
		}
		return result;
	}
	
	//TODAS LAS COMIDAS PARA CELIACOS
	public ArrayList<AProduct> getAllCeliacProducts(){
		ArrayList<AProduct> result = null;
		
		
		for(int i=0; i<products.size();i++) {
			
			if(products.get(i).isForCeliac()) {
				
				result.add(products.get(i));
			}
		}
			
		
		return result;
	}
	
	//BUSCAR PACK INSERTANDO PRODUCTO
	public ArrayList<AProduct> getBundleProduct(AProduct p){
			ArrayList<AProduct> result = null;
			
			
			return result;
	}
	
	//BUSCAR PRODUCTO POR EL NOMBRE
	public ArrayList<AProduct> searchProduct(String name){
		ArrayList<AProduct> result = null;
		for(int i=0; i<products.size();i++) {
			
			if(products.get(i).getName().equals(name)) {
				
				result.add(products.get(i));
			}
		}
		return result;
	}
	//BUSCAR BEBIDA POR EL NOMBRE
	public ArrayList<AProduct> searchDrinks(String name){
		ArrayList<AProduct> result = null;
		for(int i=0; i<products.size();i++) {
			Drink tmp=new Drink();
			if(products.get(i).getClass() == tmp.getClass()) {
				
				if(products.get(i).getName().equals(name)) {
					
					result.add(products.get(i));
				}
			}
			
		}
		return result;
	}
	//BUSCAR BEBIDA POR EL NOMBRE
		public ArrayList<AProduct> searchFood(String name){
			ArrayList<AProduct> result = null;
			for(int i=0; i<products.size();i++) {
				Food tmp=new Food();
				if(products.get(i).getClass() == tmp.getClass()) {
					
					if(products.get(i).getName().equals(name)) {
						
						result.add(products.get(i));
					}
				}
				
			}
			return result;
		}
		
		
	
	
	
}