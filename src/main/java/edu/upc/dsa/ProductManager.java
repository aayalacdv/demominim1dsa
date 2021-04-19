package edu.upc.dsa;

import java.util.List;

public interface ProductManager {

    public void makeOrder(List<Order> orders, int id) throws Exception;
    public void attendOrder();
    public List<Product> getProductsSorted();
    public List<Product> getProductsSortedSales();
}
