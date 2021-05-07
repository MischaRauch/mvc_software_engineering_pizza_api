package com.example.demo.Pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
@RequestMapping(path = "api/order")
public class PizzaOrder {
    private LinkedList<Order> all_orders = new LinkedList<Order>();
    private final PizzaService pizzaService;

    public void addOrder(String status, String payment_type, LinkedList<String> delivery_address, boolean takeaway, int costumer_id){
        Order new_one = new Order (status,payment_type,delivery_address,takeaway, costumer_id );
        all_orders.add(new_one);
    }
    public LinkedList<Order> getAll_orders(){
        return all_orders;
    }

    @Autowired
    public PizzaOrder (PizzaService pizzaService){
        this.pizzaService = pizzaService;
    }
    @PostMapping
    public void registerNewPizza (@RequestBody Pizza pizza){ pizzaService.addNewPizza(pizza);}

}
