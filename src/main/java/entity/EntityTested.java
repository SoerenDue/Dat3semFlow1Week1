/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.time.LocalDate;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author zarpy
 */
public class EntityTested {
        
        public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        EntityManager em = emf.createEntityManager();
        Customer customer1 = new Customer("Hans","Olsen");
        Customer customer2 = new Customer("Ira","Shamir");
            try {
                 em.getTransaction().begin();
                 em.persist(customer1);
                 em.persist(customer2);
                 em.getTransaction().commit();
       	 
    	}finally{
        	em.close();
    	}
         //Verify that customers are managed and has been given a database id    
        System.out.println("Customer 1 " + customer1.toString());
        System.out.println("Customer 2 " + customer2.toString());
    }
}
