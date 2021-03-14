package modelsrepository;

import models.Client;
import utilities.RepositoryUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class RepositoryClients implements Serializable {

    @Serial
    private static final long serialVersionUID = 6529685098267757690L;
    private ArrayList<Client> clients;

    private static RepositoryClients instance;

    public static RepositoryClients getInstance() {
        if (instance == null) {
            RepositoryUtils u = new RepositoryUtils();
            if (u.loadClients("clients.data") != null) {
                instance = u.loadClients("clients.data");
            } else {
                instance = new RepositoryClients();
            }
        }
        return instance;
    }

    private RepositoryClients() {
        clients = new ArrayList<Client>();
    }
/**
 * 
 * @return Todos los clientes
 */
    public ArrayList<Client> getAllClients() {
        return this.clients;
    }
/**
 * Funcion que devuelve un cliente el cual buscamos por el nombre
 * @param name nombre cliente
 * @return cliente
 */
    public ArrayList<Client> searchClientByName(String name) {
        ArrayList<Client> result = null;
        if (name != null && !(name.isEmpty())) {
            result = new ArrayList<Client>();
            for (Client c : clients) {
                //No fufa
                if (c.getName().matches("^\\w?" + name + "\\w$?")) {
                    result.add(c);
                    //El de abajo si fufa
                }else if(c.getName().equalsIgnoreCase(name)){
                    result.add(c);
                }
            }
        }
        return result;
    }

  /**
   * Función que devuelve un cliente el cual buscamos por el dni
   * @param dni cliente
   * @return cliente
   */
    public Client searchClientByDni(String dni) {
        Client result = null;
        if (dni != null && !(dni.isEmpty())) {
            boolean exit = false;
            for (int i = 0; i < clients.size() && !exit; i++) {
                if (clients.get(i).getDni().equals(dni)) {
                    result = clients.get(i);
                    exit = true;
                }
            }
        }
        return result;
    }
/**
 * Función que devuelve un true o un folse si a o no aniadido un cliente
 * @param c cliente
 * @return true aniadido false no aniadido
 */
    public boolean addClient(Client c) {
        boolean result = false;
        if (c != null && c.getDni() != null && !c.getDni().isEmpty()) {
            if (!clients.contains(c)) {
                clients.add(c);
                result = true;
            }
        }
        return result;
    }
/**
 * Función que devuelve un true o un folse si a o no eliminado un cliente
 * @param dni cliente
 * @return true eliminado false no eliminado
 */
    public boolean deleteClient(String dni) {
        boolean result = false;
        if (dni != null && !(dni.isEmpty())) {
            Client tmp = searchClientByDni(dni);
            if (tmp != null) {
                clients.remove(tmp);
                result = true;
            }
        }
        return result;
    }

}
