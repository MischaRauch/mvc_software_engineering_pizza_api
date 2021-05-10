package com.example.demo.Pizza;

import java.sql.Time;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

public class Order {
    private int costumer_id;
    private  static int numberOfOrders =1;
    private int id;
    private String status;
    private String payment_type;
    private Time initial_time;
    private Time delivery_time; //expected delivery time
    private List <Pizza> pizzas;
    private List<String> delivery_address;
    private final boolean takeaway;

    public Order(String payment_type, List<String> delivery_address, boolean takeaway, int costumer_id, List <Pizza> order) {
        status = "In Progress";
        this.pizzas = pizzas;
        this.id = numberOfOrders; numberOfOrders++;
        this.costumer_id = costumer_id;
        this.payment_type = payment_type;
        this.takeaway = takeaway;
        if (takeaway==false){
            delivery_address = delivery_address;
        }

        Timer countTime = new Timer();
        //set up initial time
        //decide on expected delivery time

    }
    public int getCostumer_id(){
        return costumer_id;
    }

    public Time getInitial_time(){ //when was the pizza ordered
        return initial_time;
    }

    public Time getWaitingTime(){  //return total time that client has been waiting in minutes
        return initial_time;
    }

    public Time getDelivery_time(){
        return delivery_time;
    }

    public static int getNumberOfOrders() {
        return numberOfOrders;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public List<String> getDelivery_address() {
        return delivery_address;
    }

    public boolean isTakeaway() {
        return takeaway;
    }


}

