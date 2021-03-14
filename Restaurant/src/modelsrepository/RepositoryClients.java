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

    public ArrayList<Client> getAllClients() {
        return this.clients;
    }

    public ArrayList<Client> searchClientByName(String name) {
        ArrayList<Client> result = null;
        if (name != null && !(name.isEmpty())) {
            result = new ArrayList<Client>();
            for (Client c : clients) {
                if (c.getName().matches("\\w^" + name + "$\\w")) {
                    result.add(c);
                }
            }
        }
        return result;
    }

    public int updateClient(Client c) {
        int result = 0;
        if (c != null && c.getDni() != null && !(c.getDni().isEmpty())) {
            Client tmp = searchClientByDni(c.getDni());
            if (c.getAge() > 0) {
                tmp.setAge(c.getAge());
                result++;
            }
            if (c.getName() != null && !(c.getName().isEmpty())) {
                tmp.setName(c.getName());
                result++;
            }
            if (c.getDni() != null && !(c.getDni().isEmpty())) {
                tmp.setDni(c.getDni());
                result++;
            }
            if (c.getOrders() != null && !(c.getOrders().isEmpty())) {
                tmp.setOrders(c.getOrders());
                result++;
            }
            if (c.getAddress() != null && !(c.getAddress().isEmpty())) {
                tmp.setAddress(c.getAddress());
                result++;
            }
            if (c.getPoints() > 0) {
                tmp.setPoints(c.getPoints());
                result++;
            }
        }
        return result;
    }

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
