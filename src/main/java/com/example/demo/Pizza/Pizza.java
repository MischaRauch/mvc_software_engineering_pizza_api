package com.example.demo.Pizza;

import java.util.Arrays;
import java.util.List;
//http://localhost:8080/api/v1/pizza
public class Pizza {

    private static int idCounter = 0;
    private String[] toppings;
    private int pizza_id;
    private boolean vegetarian;
    private double price;
    private String note;
    private String name;
   // private final List<String> toppings = Arrays.asList(topping);
    private final double defaultPrice = 13;

    public Pizza() {
        this.pizza_id = idCounter;
        this.vegetarian = false;
        this.price = defaultPrice;
        idCounter++;
    }
    public String[] getToppings(){
        return toppings;
    }

    public Pizza(String type) {
        this.name = type;
        this.pizza_id = idCounter;
        this.vegetarian = false;
        this.price = defaultPrice;
        idCounter++;

    }
    public Pizza(String name, boolean vegetarian, double price, String[] toppings) {
        this.name = name;
        this.pizza_id = idCounter;
        this.vegetarian = vegetarian;
        this.price = price;
        this.toppings= toppings;
        idCounter++;
    }
    public Pizza(String name, boolean vegetarian, double price, String[] toppings, String note) {
        this.name = name;
        this.pizza_id = idCounter;
        this.vegetarian = vegetarian;
        this.price = price;
        this.note = note;
        this.toppings= toppings;
        idCounter++;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public int getPizza_id() {return this.pizza_id;}
    public boolean getVegetarian() {return this.vegetarian;}
    public double getPrice() {return this.price;}
    public String getNote() { return note; }

    public String toString() {
        return ("Pizza Type:"+this.getName());
    }
}
