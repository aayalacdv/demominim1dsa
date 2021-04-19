package edu.upc.dsa;

import org.apache.log4j.Logger;

import java.util.*;


public class ProductManagerImpl implements ProductManager {

    private List<User> users;
    private List<Product> products;
    private HashMap<String, Integer> sales;
    private Queue<Order> pendingOrder;

    private static ProductManagerImpl productManager;
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);


    public static ProductManagerImpl getInstance(){
       if(productManager == null ){
           logger.warn("Se crea la instancia");
           productManager = new ProductManagerImpl();

       }
       logger.info("PRODUCT MANAGER CREADO");
       return productManager;
    }

    public ProductManagerImpl() { this.users = new ArrayList<>(); this.products = new ArrayList<>(); this.sales = new HashMap<>(); this.pendingOrder = new LinkedList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public HashMap<String, Integer> getSales() {
        return sales;
    }

    public void setSales(HashMap<String, Integer> sales) {
        this.sales = sales;
    }

    public Queue<Order> getPendingOrder() {
        return pendingOrder;
    }

    public void setPendingOrder(Queue<Order> pendingOrder) {
        this.pendingOrder = pendingOrder;
    }

    public static void setProductManager(ProductManagerImpl productManager) {
        ProductManagerImpl.productManager = productManager;
    }

    @Override
    public void makeOrder(List<Order> orders, int id ) throws Exception {
        boolean found = false;
        logger.info("PROCESANDO PEDIDO DE USUARIO CON ID" + id);
        //imprimimos el estado actual de la lista de colasç
        logger.info("Cola actual");
        for( Order o : pendingOrder){
           logger.debug("PRODUCTO:" + o.getProduct().getName() + "CANTIDAD" + o.getQuantity());
        }
        for(User u : this.users ){
            if (id == u.getId()){
                logger.info("USUARI ENCONTRADO");
                for (Order o : orders){
                    pendingOrder.add(o);
                    logger.info("PEDIDO AÑADIDO");
                }
                found = true;
            }
        }
        logger.info("COLA MODIFICADA");
        for( Order o : pendingOrder){
            logger.debug("PRODUCTO: " + o.getProduct().getName() + " CANTIDAD: " + o.getQuantity());
        }
        if (!found){
            logger.debug("USUARIO NO ENCONTRADO, ID " + id + "NO VALIDO");
            throw new Exception("USUARIO NO ENCONTRADO");
        }

    }

    @Override
    public void attendOrder() {
        logger.info("ATENDIENDO PEDIDO");

        logger.debug("COLA ACTUAL");
        for( Order o : pendingOrder){
            logger.info("PRODUCTO: " + o.getProduct().getName() + " CANTIDAD: " + o.getQuantity());
        }
        Order order = this.pendingOrder.peek();
        for(User user: this.users){
            if(order.getId() == user.getId()){
                logger.info("LISTA DE PEDIDOS DEL USUSARIO " + user.getName() );
                for ( Order o : user.getCompletedOrders()){
                   logger.debug("Producto: " + o.getProduct().getName() + " CANTIDAD" + o.getQuantity());
                }
                order.setCompleted();
                user.getCompletedOrders().add(order);
                logger.info("LISTA DE PEDIDOS DEL USUSARIO " + user.getName() + " MODIFICADA" );
                for ( Order o : user.getCompletedOrders()) {
                    logger.debug("Producto: " + o.getProduct().getName() + " CANTIDAD: " + o.getQuantity());
                }

                this.sales.put(order.getProduct().getName(), order.getQuantity() + this.sales.get(order.getProduct().getName()));
                logger.info("LISTA DE VENTAS");
                for(String n  : this.sales.keySet()){
                    logger.debug(n + " " + this.sales.get(n));
                }

            }
        }
        this.pendingOrder.poll();

        logger.info("COLA MODIFICADA");
        for( Order o : pendingOrder){
            logger.debug("PRODUCTO: " + o.getProduct().getName() + " CANTIDAD: " + o.getQuantity());
        }
    }

    @Override

    public List<Product> getProductsSorted() {
        this.products.sort((Product a, Product b ) -> Float.compare(a.getCost(),b.getCost()));
        return this.products;
    }

    @Override
    public List<Product> getProductsSortedSales() {
        List<Product> productsSortedBySales = this.products;
        productsSortedBySales.sort((Product a, Product b ) -> Integer.compare(this.sales.get(a.getName()),this.sales.get(b.getName())));
        Collections.reverse(productsSortedBySales);
        return productsSortedBySales;
    }
}
