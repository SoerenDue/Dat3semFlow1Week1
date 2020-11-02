
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author zarpy
 */
public class CustomerFacade {
    private static EntityManagerFactory emf;
    private static CustomerFacade instance;

    private CustomerFacade() {
    }

    public static CustomerFacade getCustomerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new CustomerFacade();
        }
        return instance;
    }
    

    public Customer addCustomer(String firstName, String lastName){
        
        EntityManager em = emf.createEntityManager();
        try{
            
            Customer customer1 = new Customer(firstName, lastName);
            em.getTransaction().begin();
            em.persist(customer1);
            em.getTransaction().commit();
            return customer1;
        }finally {
            em.close();
        }
    }
    
    
    public Customer findByID(int id){
         EntityManager em = emf.createEntityManager();
        try{
            Customer customer = em.find(Customer.class,id);
            return customer;
        }finally {
            em.close();
        }
    }
    
    public List<Customer> allCustomers(){
         EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = 
                       em.createQuery("Select customer from Customer customer", Customer.class);
            return query.getResultList();
        }finally {
            em.close();
        }
    }
    
   public List<Customer> findByLastName(String name){
                EntityManager em = emf.createEntityManager();
        try{
            TypedQuery<Customer> query = 
                       em.createQuery("Select customer FROM Customer customer WHERE customer.lastName = :lastName",Customer.class).setParameter("lastName",name);
            return query.getResultList();
        }finally {
            em.close();
        }
   }
    
    public int getNumberOfCustomers(){
        EntityManager em = emf.createEntityManager();
        int res = 0;
        try {
            Query q1 = em.createQuery("SELECT COUNT(c) FROM Customer c");
            try {
                res = Integer.parseInt(q1.getSingleResult().toString());
            } catch (Exception e){
                throw(e);
            }
            return res;
            
        } finally {
            em.close();
        }
    }
        public void deleteAllCostumers(){
        EntityManager em = emf.createEntityManager();
        try {
                em.getTransaction().begin();
                Query query = em.createQuery("DELETE FROM Customer c");
                query.executeUpdate();
                em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
