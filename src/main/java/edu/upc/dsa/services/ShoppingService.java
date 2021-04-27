package edu.upc.dsa.services;

import edu.upc.dsa.models.Order;
import edu.upc.dsa.models.Product;
import edu.upc.dsa.ProductManagerImpl;
import edu.upc.dsa.models.User;

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
        productManager = ProductManagerImpl.getInstance();
        if(productManager.getUsers().size() == 0 ){
            productManager.setUpResources();
        }

    }



    //OBTENER LAS VENTAS DE LOS USUARIOS
    @GET
    @Path("/completed/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompletedOrders (@PathParam("id") String id) {
        User user = productManager.getUserbyId(id);
        List<Order> orders = user.getCompletedOrder();
        GenericEntity<List<Order >>entity = new GenericEntity<List<Order>>(orders){};
        return Response.status(201).entity(entity).build();
    }


    //RETURN THE LIST OF PRODUCTS SORTED
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getProductsSorted() {
        List<Product> products = productManager.getProductsSortedbyPrice();
        GenericEntity<List<Product>> entity2 = new GenericEntity<List<Product>>(products){};
        return Response.status(201).entity(entity2).build();
    }


    //RETURN THE LIST OF PRODUCTS SORTED BY SALES
    @GET
    @Path("/sortedbysales")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductsBySales(){
        List<Product> products = productManager.getProductsSortedbySales();
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(products){};
        return Response.status(201).entity(entity).build();

    }

    @GET
    @Path("/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers(){
        HashMap<String, User> users = productManager.getUsers();
        List<User> userList = new ArrayList<>();
        for(User u : users.values()){
            userList.add(u);
        }
        GenericEntity<List<User>>entity = new GenericEntity<List<User>>(userList){};
        return Response.status(201).entity(entity).build();
    }

    //MAKE AN ORDER
    @POST
    @Path("/order")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response makeOrder(List<Order> orders) {
        try{
            productManager.makeOrder(orders);
        }catch(Exception e){
           return Response.status(401).entity("USER NOT FOUD").build();
        }
        productManager.attendOrder();
        return Response.status(201).entity("ORDER COMPLETED").build();
    }







}