package com.example.demo.Pizza;

import com.fasterxml.jackson.annotation.JsonView;

public class Pizza {
    private static int idCounter = 0;
    private String[] toppings;
    @JsonView({Views.DeliveryTime.class, Views.OrderNotTime.class})
    private int pizza_id;
    private boolean vegetarian;
    private double price;
    private String name;
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

    public String toString() {
        return ("Pizza Type:"+this.getName());
    }

    public static void setIdCounter(int idCounter) {
        Pizza.idCounter = idCounter;
    }

    public void setToppings(String[] toppings) {
        this.toppings = toppings;
    }

    public void setPizza_id(int pizza_id) {
        this.pizza_id = pizza_id;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
