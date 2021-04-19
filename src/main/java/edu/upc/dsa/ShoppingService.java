package edu.upc.dsa;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Path("/store")
public class ShoppingService {


    private ProductManagerImpl productManager;

    public ShoppingService() {

        this.productManager = ProductManagerImpl.getInstance();
        //LOAD ITEMS IN THE PRODUCT LIST
        List<Product> products = new ArrayList<>();
        Product cocacola = new Product("Coca Cola", 1.50F);
        products.add( cocacola);
        Product bocata = new Product("Bocata", 2.50F);
        products.add( bocata);
        Product cervesa = new Product("Cervesa", 3.50F);
        products.add(cervesa);
        productManager.setProducts(products);

        //LOAD USER LIST
        List<User> users = new ArrayList<>();
        User axel = new User("Axel", 0);
        User mario = new User("Mario", 1);
        User bruno = new User("Bruno", 2);

        users.add(axel);
        users.add(bruno);
        users.add(mario);
        productManager.setUsers(users);

        //LOAD SALES LIST
        HashMap<String , Integer> sales = new HashMap<>();
        sales.put(cocacola.getName(),1 );
        sales.put(cervesa.getName(), 13);
        sales.put(bocata.getName(), 10 );
        productManager.setSales(sales);
    }



    //OBTENER LAS VENTAS DE LOS USUARIOS
    @GET
    @Path("/completed/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompletedOrders (@PathParam("id") int id) {
        List<User> users = productManager.getUsers();
        for (User u : users){
            if ( u.getId() == id ){
                List<Order> orders = u.getCompletedOrders();
                GenericEntity<List<Order>> entity = new GenericEntity<List<Order>>(orders){};
                return Response.status(201).entity(entity).build();

            }
        }

        return Response.status(404).entity("USER NOT FOUD").build();

    }


    //RETURN THE LIST OF PRODUCTS SORTED
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getProductsSorted() {
        List<Product> products = productManager.getProductsSorted();
        GenericEntity<List<Product>> entity2 = new GenericEntity<List<Product>>(products){};
        return Response.status(201).entity(entity2).build();
    }


    //RETURN THE LIST OF PRODUCTS SORTED BY SALES
    @GET
    @Path("/sortedbysales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsBySales(){
        List<Product> products = productManager.getProductsSortedSales();
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products){};
        return Response.status(201).entity(entity).build();

    }

    //MAKE AN ORDER
    @POST
    @Path("/order")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makeOrder(List<Order> orders) {
        try{
            productManager.makeOrder(orders, orders.get(0).getId());
        }
        catch (Exception e ){
           return Response.status(404).entity("USER NOT FOUND").build();
        }

       return Response.status(201).entity("ORDER COMPLETED").build();

    }


    //ATTEND ORDER
    @GET
    @Path("/attend")
    public Response attendOrder() {
        if ( productManager.getPendingOrder().size() == 0){
            return Response.status(501).entity("NO PENDING ORDERS").build();
        }
        return Response.status(201).entity("NEXT ORDER ATTENDED ").build();
    }

}