package com.example.demo.Pizza;

import java.sql.Time;

import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

public class Order {
    private static int costumer_id;
    private String status;
    private String payment_type;
    private Timer countTime;
    private Time initial_time;
    private Time delivery_time; //expected delivery time
    //private final LinkedList<String> delivery_address;
    private final boolean takeaway;

    public Order(String status, String payment_type, LinkedList<String> delivery_address, boolean takeaway, int costumer_id) {
        this.status = status;
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

    public Time getInitial_time(){ //when was the pizza ordered
        return initial_time;
    }

    public Time getWaitingTime(){  //return total time that client has been waiting in minutes
        return initial_time;
    }

    public Time getDelivery_time(){
        return delivery_time;
    }

}

