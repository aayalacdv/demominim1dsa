package edu.upc.dsa.models;

import edu.upc.dsa.utils.RandomUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Paciente {
     String id;
     String name;
     int age;
     Level level;

     List<Muestra> muestras;

     public Paciente(){
         this.id = RandomUtils.getId();
     }
    public Paciente( String namem, int age, Level level){
         this();
         this.name = namem;
         this.age = age;
         this.level = level;
         this.muestras = new LinkedList<>();

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public List<Muestra> getMuestras() {
        return muestras;
    }

    public void setMuestras(List<Muestra> muestras) {
        this.muestras = muestras;
    }

    @Override
    public String toString(){
         return "nombre: " + this.name + " id: " + this.id + " estado de salud: " + this.level + " edad:" + this.age;
    }
}
