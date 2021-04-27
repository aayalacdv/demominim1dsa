package edu.upc.dsa.models;

import edu.upc.dsa.utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name; 
    private String id;
    private List<Order> completedOrders;

    public User(){
        this.id = RandomUtils.getId();
    }

    public User(String name){
        this();
        this.completedOrders = new ArrayList<>();
        this.name = name; 
    }

    public String getName(){
        return this.name; 

    }
    
    public void setName(String name){
        this.name = name; 
    }

    public String getId(){
        return this.id; 
    }

    public void setId( String id ){
        this.id = id; 
    }

    public List<Order> getCompletedOrder(){
        return this.completedOrders; 
    }

    public void setCompletedOrders( List<Order> orders){
        this.completedOrders = orders;  
    }

    @Override
    public String toString (){
        return "Usuario: " + this.name + " Id: " + this.id; 
    }
}