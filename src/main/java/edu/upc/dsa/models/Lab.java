package edu.upc.dsa.models;

import edu.upc.dsa.utils.RandomUtils;

public class Lab {
    private String id;
    private String name;

    public Lab(){
        this.id = RandomUtils.getId();

    }


    public Lab(String name){
        this();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "id: " + this.id + " name: " + this.name;
    }
}
