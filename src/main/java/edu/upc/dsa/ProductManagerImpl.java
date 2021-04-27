package edu.upc.dsa;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.models.User;
import org.apache.log4j.Logger;

import java.util.*;

public class ProductManagerImpl implements ProductManager {
    //Estructuras de datos para poder hacer los pedidos y operaciones
    private HashMap<String, User> users;
    private HashMap<String, Product> products;
    private List<Product> productList;
    private Queue<List<Order>> pendinOrders;

    //hacemos el singleton y el logger
    final static Logger logger = Logger.getLogger(ProductManagerImpl.class);
    private static ProductManagerImpl productManager;

    public static ProductManagerImpl getInstance(){
        if(productManager == null ){
            productManager = new ProductManagerImpl();
        }
        return productManager;
    }

    private ProductManagerImpl (){
        this.users = new HashMap<>();
        this.productList = new ArrayList<>();
        this.products = new HashMap<>();
        this.pendinOrders = new LinkedList<>();
    }

    public HashMap<String, User> getUsers() {
        return users;
    }

    public void setUsers(HashMap<String, User> users) {
        this.users = users;
    }

    public HashMap<String, Product> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, Product> products) {
        this.products = products;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public Queue<List<Order>> getPendinOrders() {
        return pendinOrders;
    }

    public void setPendinOrders(Queue<List<Order>> pendinOrders) {
        this.pendinOrders = pendinOrders;
    }

    @Override
    public void makeOrder(List<Order> order) throws Exception {
        User u = getUserbyId(order.get(0).getUserId()) ;
        if (u == null ){
            logger.info("USUARIO NO ENCONTRADO");
            throw new Exception( "USUARIO NO ENCONTRADO ");
        }
        else {
            //hacer la orden
            logger.info("PROCESANDO PEDIDO ");
            logger.debug(this.pendinOrders);
            this.pendinOrders.add(order);
            logger.info("COLA MODIFICADA");
            logger.debug(this.pendinOrders);
        }


    }

    @Override
    public void attendOrder() {
        logger.info("SE ESTÁ ATENDIENDO UNA ORDEN");
        logger.debug(this.pendinOrders);

        User u = getUserbyId(this.pendinOrders.peek().get(0).getUserId());
        List<Order> orders = this.pendinOrders.peek();



        logger.info("LISTA DEL USUARIO " );
        logger.debug(u.getCompletedOrder()) ;

        for (Order o : orders ){
            u.getCompletedOrder().add(o);
            Product p = getProductbyId(o.getProductId());
            p.setSales( p.getSales() + products.get(p.getId()).getSales());
            this.products.put(p.getId(),p);
        }

        users.put(u.getId(), u);

        logger.info("LISTA DEL USUARIO MODIFICADA" );
        logger.debug(u.getCompletedOrder()) ;

        this.pendinOrders.poll();

        logger.info("COLA DE PEDIDOS MODIFICADA " );
        logger.debug(this.pendinOrders);
    }

    @Override
    public List<Product> getProductsSortedbyPrice() {
        this.productList.sort((Product a, Product b) -> Float.compare(a.getPrice(), b.getPrice()));
        return this.productList;
    }

    @Override
    public List<Product> getProductsSortedbySales() {
       this.productList.sort((Product a, Product b) -> Integer.compare(a.getSales(), b.getSales()));
       Collections.reverse(this.productList);
       return this.productList;
    }

    @Override
    public List<Order> getUserCompletedOrders(String id) {
        return this.users.get(id).getCompletedOrder();
    }

    @Override
    public Product getProductbyId(String id) {
        return this.products.get(id);
    }

    @Override
    public User getUserbyId(String id) {
        return this.users.get(id);
    }

    @Override
    public void setUpResources() {
        User user1 = new User("Axel");
        User user2 = new User("Alex");

        HashMap<String, User> users = new HashMap<>();
        users.put(user1.getId(),user1);
        users.put(user2.getId(), user2);
        productManager.setUsers(users);

        Product p1 = new Product("cafe", 1.5f);
        Product p2 = new Product("pizza", 2.5f);

        HashMap<String, Product>products = new HashMap<>();
        products.put(p1.getId(), p1);
        products.put(p2.getId(),p2);

        productManager.setProducts(products);
        List<Product> productList = new ArrayList<>();
        productList.add(p1);
        productList.add(p2);

        productManager.setProductList(productList);

        Order o1 = new Order( user1.getId(),p1.getId(),2);
        Order o2 = new Order(user1.getId(), p2.getId(), 3);

        Order o3 = new Order(user2.getId(),p2.getId(),1);

        List<Order> olist1 = new ArrayList<>();
        olist1.add(o1);
        olist1.add(o2);

        List<Order> olist2 = new ArrayList<>();
        olist2.add(o3);
        Queue<List<Order>> pendinOrders = new LinkedList<>();
        pendinOrders.add(olist2);
        pendinOrders.add(olist1);
        productManager.setPendinOrders(pendinOrders);

    }

    @Override
    public void tearDownResources() {
        setProductList(new ArrayList<Product>());
        setProducts(new HashMap<String,Product>());
        setPendinOrders(new LinkedList<List<Order>>());
        setUsers(new HashMap<String,User>());
    }
}
