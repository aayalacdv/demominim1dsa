package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;

import java.util.List;

public interface ProductManager {

    //Operaciones del enunciado
    public void makeOrder(List<Order> order) throws Exception;
    public void attendOrder();
    public List<Product> getProductsSortedbyPrice();
    public List<Product> getProductsSortedbySales();
    public List<Order> getUserCompletedOrders( String id );

    //Operaciones complementarias
    public Product  getProductbyId(String id );
    public User getUserbyId(String id );
    public void setUpResources();
    public void tearDownResources();

}
