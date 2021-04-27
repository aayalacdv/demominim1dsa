package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ProductManagerTest {
    private static ProductManagerImpl productManager;

    @BeforeAll
    public static void setUp() {
        //CREATE PRODUCT MANAGER
        productManager = ProductManagerImpl.getInstance();
        productManager.setUpResources();

    }

    @Test
    public void testHacerOrdern() {
        assertNotNull(productManager);
        List<Product>products = productManager.getProductList();
        List<String> userKeys = new ArrayList<>();
        for(String k : productManager.getUsers().keySet()){
            userKeys.add(k);
        }
        //Como el usuario no está verificado no tiene porque añadirse a la cola
        edu.upc.dsa.models.Order order = new edu.upc.dsa.models.Order("xdddd","xddd",4);
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);

        try{
            productManager.makeOrder(orderList);
        } catch (Exception e ){}
        assertEquals(2,productManager.getPendinOrders().size());
    }

    @Test
    public void testServirOrden() {
        productManager.attendOrder();
        assertEquals(1,productManager.getPendinOrders().size());
    }

    @AfterAll
    public static void tearDown() {
       productManager.tearDownResources();
    }

}