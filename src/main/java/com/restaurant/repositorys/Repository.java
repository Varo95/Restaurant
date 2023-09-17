package com.restaurant.repositorys;

import com.restaurant.interfaces.AProduct;
import com.restaurant.models.Client;
import com.restaurant.models.Order;
import com.restaurant.utilities.PersistenceUnit;
import com.restaurant.views.Tools;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Log4j2
public class Repository {

    private static Repository instance;


    private Repository(){

    }

    public static Repository getInstance() {
        if (instance == null) {
            instance = new Repository();
            initApp();
        }
        return instance;
    }

    private static void initApp(){
        final List<AProduct> products = instance.getAll(AProduct.class);
        if(products.isEmpty()){
            for(final AProduct product : Tools.createProducts()){
                instance.persist(product);
            }
        }
        final List<Client> clients = instance.getAll(Client.class);
        if(clients.isEmpty()){
            for(final Client client : Tools.createClients()){
                instance.persist(client);
            }
        }
    }

    public List<Order> getAllDayOrders(){
        EntityTransaction transaction;
        final List<Order> result = new CopyOnWriteArrayList<>();
        try(final EntityManager em = PersistenceUnit.createEM()){
            transaction = em.getTransaction();
            transaction.begin();
            final TypedQuery<Order> a = em.createNamedQuery("Order.getBetweenDate", Order.class);
            a.setParameter("start", LocalDateTime.of(LocalDate.now(), LocalTime.MIN));
            a.setParameter("end", LocalDateTime.of(LocalDate.now(), LocalTime.MAX));
            result.addAll(a.getResultList());
            transaction.commit();
        }catch (final Exception e){
            log.error("Error al obtener los pedidos del día", e);
        }
        return result;
    }

    /**
     * Función que devuelve todos los items de una clase en concreto
     * @return todos los items de la bbdd
     */

    public <T> List<T> getAll(final Class<T> type){
        EntityTransaction transaction;
        final List<T> result = new CopyOnWriteArrayList<>();
        try (final EntityManager em = PersistenceUnit.createEM()) {
            transaction = em.getTransaction();
            transaction.begin();
            final TypedQuery<T> a = em.createNamedQuery(type.getSimpleName()+".findAll", type);
            result.addAll(a.getResultList());
            transaction.commit();
        } catch (final Exception e) {
            log.error("Error al obtener todos los "+type.getSimpleName(), e);
        }
        return result;
    }


    /**
     * Devuelve la lista de los productos encontrados
     * @param name nombre a buscar 
     * @return Lista de Productos 
     */
    public <T> List<T> searchByName(final String name, final Class<T> type) {
        EntityTransaction transaction;
        final List<T> products = new CopyOnWriteArrayList<>();
        try (final EntityManager em = PersistenceUnit.createEM()) {
            transaction = em.getTransaction();
            final TypedQuery<T> a = em.createNamedQuery(type.getSimpleName() + ".findByName", type);
            a.setParameter("name", name);
            products.addAll(a.getResultList());
            transaction.commit();
        } catch (final Exception e) {
            log.error("Error al buscar los objetos", e);
        }
        return products;
    }

    /**
     * Función para guardar objetos
     * @param object objeto a guardar
     * @param <T> tipo de objeto
     */

    public <T> void persist(T object) {
        EntityTransaction transaction;
        try (final EntityManager em = PersistenceUnit.createEM()) {
            transaction = em.getTransaction();
            transaction.begin();
            object = em.merge(object);
            em.persist(object);
            transaction.commit();
        } catch (final Exception e) {
            log.error("Error al persistir el objeto", e);
        }
    }

    /**
     * Función para eliminar objetos
     * @param object objeto a eliminar
     * @param <T> tipo de objeto
     */

    public <T> void delete(T object){
        EntityTransaction transaction;
        try (final EntityManager em = PersistenceUnit.createEM()) {
            transaction = em.getTransaction();
            transaction.begin();
            object = em.merge(object);
            em.remove(object);
            transaction.commit();
        } catch (final Exception e) {
            log.error("Error al eliminar objeto", e);
        }
    }

}