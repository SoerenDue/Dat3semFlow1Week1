
package dbfacade;

import entity.Customer;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author zarpy
 */
public class CustomerFacadeTest {
    
    private static Customer customer1;
    private static Customer customer2;
    private static EntityManagerFactory emf;
    private static CustomerFacade facade;
    
    public CustomerFacadeTest() {
    }
   
    @BeforeAll
    public static void setUpClass() {
        emf = Persistence.createEntityManagerFactory("pu");
        facade = CustomerFacade.getCustomerFacade(emf);
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
         System.out.println("Before each");
         facade.deleteAllCostumers();
         customer1 = facade.addCustomer("Hans","Olsen");
         customer2 = facade.addCustomer("Ira","Shamir");        
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getCustomerFacade method, of class CustomerFacade.
     */
    @Test
    public void testGetCustomerFacade() {
        System.out.println("getCustomerFacade");     
        assertNotNull(facade);
    }

    /**
     * Test of getNumberOfCustomer method, of class CustomerFacade.
     */
    @Test
    public void testGetNumberOfCustomers() {
        System.out.println("getNumberOfCustomers");       
        int expResult = 2;
        int result = facade.getNumberOfCustomers();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of addCustomer method, of class CustomerFacade.
     */
    @Test
    public void testAddCustomer() {
        System.out.println("addCustomer");
        String firstName = "Irene";
        String lastName = "Velgaard";
        int expResultSize = 3;
        Customer result = facade.addCustomer(firstName, lastName);
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertEquals(expResultSize, facade.getNumberOfCustomers());
    }

    /**
     * Test of findByID method, of class CustomerFacade.
     * Needs and @Override equals to work
     */
    @Test
    public void testFindByID() {
        System.out.println("findByID");
        
        int id = customer1.getId();
        Customer expResult = customer1;
        System.out.println(customer1);
        Customer result = facade.findByID(id);
        System.out.println(result);
        System.out.println(result.toString());
        assertEquals(expResult, result);
    }

    /**
     * Test of allCustomers method, of class CustomerFacade.
     * Needs and @Override equals to work
     */
    @Test
    public void testAllCustomers() {
        System.out.println("allCustomers");
        int expResult = 2;
        List<Customer> result = facade.allCustomers();
        assertEquals(expResult, result.size());
        for (Customer customer: result){
            System.out.println("Customer 1 " + customer1.toString());
            System.out.println("Customer 2 " + customer2.toString());
            assertTrue(customer.equals(customer1) || customer.equals(customer2));
        }
    }

    /**
     * Test of findByLastName method, of class CustomerFacade.
     */
    @Test
    public void testFindByLastName() {
        System.out.println("findByLastName");
        String name = "Olsen";
        String expResult = customer1.getLastName();
        List<Customer> result = facade.findByLastName("Olsen");
        assertTrue(result.size() > 0);
        String resultName = result.get(0).getLastName();
        assertEquals(expResult, resultName);
    }
    
    @Test
    public void testDeleteAllCostumers() {
        System.out.println("deleteAllCostumers");
        int expResultSize = 0;
        facade.deleteAllCostumers();
        assertEquals(expResultSize, facade.getNumberOfCustomers());    
    }
}
