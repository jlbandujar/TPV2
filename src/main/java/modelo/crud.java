/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Jose
 */
public class crud {
        public static List<Productos> getProductos() {
        
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT * FROM productos";
        Query q = manager.createNativeQuery(sql,Productos.class);
        List<Productos> productosBD =  q.getResultList();
      /*   for ( Productos p: productosBD ){
            Producto miProducto = new Productos(p.getId(),p.getNombre(),p.getImagen(),p.getCategoria(),p.getPrecio(),0);
            productos.add(miProducto);         
        } */
        return productosBD;
        
    } 
        
        public static Productos getProducto(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager manager = factory.createEntityManager();
        String sql = "SELECT p FROM Productos p WHERE p.id=" + id; //consulta en JPQL 
        Query q = manager.createQuery(sql,Productos.class); //método para consultas en JPQL
        Productos productosBD =  ( Productos )q.getSingleResult();
        return productosBD;
        } 

        
      public static int destroyProducto(int id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager manager = factory.createEntityManager();
        String sql = "DELETE from Productos p WHERE p.id = " + id;
        Query q = manager.createQuery(sql);
        manager.getTransaction().begin();
        int filasAfectadas = q.executeUpdate(); //para las consultas de modif. datos se usa el método executeUpdate
        manager.getTransaction().commit();
        return filasAfectadas;  
    }
    
        
}
