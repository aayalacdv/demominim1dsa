package edu.upc.dsa;

public class Order {

    private Product product;
    private int quantity;
    private int id;
    private boolean completed;

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted() {
        this.completed = true;
    }

    public Order(Product product, int quantity, int id) {
        this.product = product;
        this.quantity = quantity;
        this.id = id;
        this.completed = false;

    }


    @Override
    public String toString(){
        return "dsamin1.Models.Product:" + this.product.getName() + " quantity:" + this.quantity + " UserID:" + this.id;
    }


}
