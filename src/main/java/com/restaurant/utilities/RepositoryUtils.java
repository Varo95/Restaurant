package com.restaurant.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.restaurant.models.Order;
import com.restaurant.repositorys.RepositoryClients;
import com.restaurant.repositorys.RepositoryOrders;

public class RepositoryUtils {
	/**
	 * Carga los clientes desde un archivo
	 * @param path archivo a cargar
	 * @return repositorio cargado desde el archivo
	 */
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

	/**
	 * Este método guarda un repositorio de clientes
	 * @param path archivo a guardar
	 * @param clients Repositorio a guardar
	 * @return true si se ha podido, false en caso contrario
	 */
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
	/**
	 * Carga los pedidos desde un archivo
	 * @param path archivo a cargar
	 * @return repositorio cargado desde el archivo
	 */
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

	/**
	 * Este método guarda un repositorio de pedidos
	 * @param path archivo a guardar
	 * @param orders repositorio de pedidos a guardar
	 * @return true si ha sido exitoso, false si no se ha podido guardar.
	 */
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

	/**
	 * Este método sirve para guardar un array de pedidos
	 * @param path archivo a guardar
	 * @param orders pedidos a almacenar
	 * @return true si ha podido, false en caso contrario.
	 */
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
}
