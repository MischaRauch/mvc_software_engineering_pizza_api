package com.example.demo.Pizza;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;

import java.sql.Time;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Timer;
import java.time.LocalDateTime;


public class Order {
    @JsonView(Views.Short.class)
    private int customer_id;
    private  static int numberOfOrders =1;
    private int order_id;
    @JsonView(Views.Short.class)
    private String status;
    @JsonView(Views.Short.class)
    private String payment_type;
    @JsonView(Views.Short.class)
    private final LocalDateTime initialTime;
    @JsonView(Views.Long.class)
    private final LocalDateTime deliveryTime;
    @JsonView(Views.Short.class)
    private List <Pizza> pizzas;
    private DeliveryAddress delivery_address;
    private final boolean takeaway;
    private String note;

    public Order(String payment_type, DeliveryAddress delivery_address, boolean takeaway, int customer_id, List <Pizza> order, String note) {
        status = "In Progress";
        this.pizzas = order;
        this.order_id = numberOfOrders; numberOfOrders++;
        this.customer_id = customer_id;
        this.payment_type = payment_type;
        this.takeaway = takeaway;
        this.note = note;
        if (takeaway==false){
            this.delivery_address= delivery_address;
        }
        initialTime = LocalDateTime.now();
        deliveryTime = LocalDateTime.now().plus(Duration.of(10, ChronoUnit.MINUTES));

        Timer countTime = new Timer();
        //set up initial time
        //decide on expected delivery time

    }
    public void findPizzasByID(List<Pizza> menu){
        for (int i =0; i<pizzas.size(); i++){
            for (int j=0; j<menu.size(); j++){
                if (pizzas.get(i).getPizza_id()==menu.get(j).getPizza_id()){
                    pizzas.get(i).setName( menu.get(j).getName());
                    pizzas.get(i).setPrice( menu.get(j).getPrice());
                    pizzas.get(i).setToppings( menu.get(j).getToppings());
                    pizzas.get(i).setVegetarian( menu.get(j).getVegetarian());
                }
            }
        }
    }

    public int getCostumer_id(){
        return customer_id;
    }

    public LocalDateTime getInitialTime() {
        return initialTime;
    }
    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }
    public static int getNumberOfOrders() {
        return numberOfOrders;
    }

    public int getOrder_id() {
        return order_id;
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

    public DeliveryAddress getDelivery_address() {
        return delivery_address;
    }

    public String getNote(){ return note;    }


    public int minutesPassed(){
       LocalDateTime currentTime = LocalDateTime.now();
       return currentTime.minusMinutes(initialTime.getMinute()).getMinute();
    }

    public void setStatus(String status){
        this.status= status;
    }




}

