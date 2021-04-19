package edu.upc.dsa;

public class Product {

    private String  name;
    private float cost;


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Product (String name, float cost){
        this.name = name;
        this.cost = cost;

    }
    public Product (){};


    @Override
    public String toString(){
        return "product name:" + this.name + " cost:" + this.cost;
    }
}
