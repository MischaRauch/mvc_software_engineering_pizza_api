package com.example.demo.Pizza;

import java.util.Arrays;
import java.util.List;
//http://localhost:8080/api/v1/pizza
public class Pizza {

    private static int idCounter = 1;
    private String[] toppings;
    private int id;
    private boolean vegetarian;
    private double price;
    private String note;
    private String type;
   // private final List<String> toppings = Arrays.asList(topping);
    private final double defaultPrice = 13;

    public Pizza() {
        this.id = idCounter;
        this.vegetarian = false;
        this.price = defaultPrice;
        idCounter++;
    }
    public String[] getToppings(){
        return toppings;
    }

    public Pizza(String type) {
        this.type = type;
        this.id = idCounter;
        this.vegetarian = false;
        this.price = defaultPrice;
        idCounter++;

    }
    public Pizza(String type, boolean vegetarian, double price, String[] toppings) {
        this.type = type;
        this.id = idCounter;
        this.vegetarian = vegetarian;
        this.price = price;
        this.toppings= toppings;
        idCounter++;
    }
    public Pizza(String type, boolean vegetarian, double price, String[] toppings, String note) {
        this.type = type;
        this.id = idCounter;
        this.vegetarian = vegetarian;
        this.price = price;
        this.note = note;
        this.toppings= toppings;
        idCounter++;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getType() {
        return this.type;
    }
    public int getId() {return this.id;}
    public boolean getVegetarian() {return this.vegetarian;}
    public double getPrice() {return this.price;}
    public String getNote() { return note; }

    public String toString() {
        return ("Pizza Type:"+this.getType());
    }
}
