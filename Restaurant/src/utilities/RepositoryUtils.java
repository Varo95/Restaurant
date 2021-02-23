package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import controllers.RepositoryClients;
import controllers.RepositoryOrders;
import models.Client;


public class RepositoryUtils {

	public RepositoryClients loadClients(String path) {
		RepositoryClients result = null;
		if(path!=null && !path.isEmpty()) {
			
			FileInputStream f;
			try {
				f = new FileInputStream(path);
				ObjectInputStream of= new ObjectInputStream(f);
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
	
	public boolean saveClients(String path , RepositoryClients clients) {
		
		boolean result = false;
		
		if (path !=null && !path.isEmpty()) {
			
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
	
	
	
	public RepositoryOrders loadOrders (String path) {
		RepositoryOrders  result = null;
		if(path!=null && !path.isEmpty()) {
			
			FileInputStream f;
			try {
				f = new FileInputStream(path);
				ObjectInputStream of= new ObjectInputStream(f);
				result = (RepositoryOrders ) of.readObject();
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
	
public boolean saveOrders(String path , RepositoryOrders orders) {
		
		boolean result = false;
		
		if (path !=null && !path.isEmpty()) {
			
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
}
