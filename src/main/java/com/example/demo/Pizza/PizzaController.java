package com.example.demo.Pizza;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.example.demo.exception.ApiRequestException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
public class PizzaController {

    private final PizzaService pizzaService;
    private List<Order> pizzaOrders;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
        this.pizzaOrders = new ArrayList<>();
    }

    @GetMapping("/pizza")
    public List<Pizza> returnPizza() {
        return pizzaService.getPizza();
    }

    @GetMapping(path = "/pizza/{id}")
    public Pizza returnSinglePizza(@PathVariable String id) {
        try {
            int id2 = Integer.parseInt(id);
            return pizzaService.getSinglePizza(id2);
        } catch (Exception e) {
            throw new ApiRequestException("Pizza not found", "404");
        }
    }

    @JsonView(Views.Short.class)
    @GetMapping("/order/{costumer_id}")
    public List<Order> returnAllOrders(@PathVariable String costumer_id) {
        List<Order> costumerAllOrders = new ArrayList<>();
        System.out.println("SIZE " + costumerAllOrders.size());
        try {
            int costumer_id2 = Integer.parseInt(costumer_id);
            for (int i = 0; i < pizzaOrders.size(); i++) {
                System.out.println("CUSTOMER ID 1 " + pizzaOrders.get(i).getCostumer_id());
                System.out.println("CUSTOMER ID 2 " + costumer_id2);
                if (pizzaOrders.get(i).getCostumer_id() == costumer_id2) {
                    costumerAllOrders.add(pizzaOrders.get(i));
                    return costumerAllOrders;
                }
            }
            throw new ApiRequestException("Customer ID not found", "404");
        } catch (Exception e) {
            throw new ApiRequestException("Invalid ID supplied", "400");
        }
    }
    @JsonView(Views.DeliveryTime.class)
    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order) {
        order.findPizzasByID(pizzaService.getPizza()); //initializing pizzas based on their ID
        pizzaOrders.add(order);
        //System.out.println(order.getCostumer_id());
        return order;
    }

    @JsonView(Views.Long.class)
    @GetMapping("/order/deliverytime/{id}")
    public Order returnDeliveryTime(@PathVariable String id) {
        Order costumerAllOrders;
        try {
            int costumer_id2 = Integer.parseInt(id);
            for (Order order : pizzaOrders) {
                if (order.getCostumer_id() == costumer_id2) {

                    return order;
                }
            }
            throw new ApiRequestException("Order not found", "404");
        } catch (Exception e) {
            throw new ApiRequestException("Order not found", "404");
        }
    }

    @JsonView(Views.CancelOrder.class)
    @PutMapping("/order/cancel/{order_id}")
    public Order cancelOrder(@PathVariable String order_id) {
        Order order = null;
        int order_id2;
        try {
            order_id2 = Integer.parseInt(order_id);
        } catch (Exception e) {
            throw new ApiRequestException("Invalid ID supplied", "400");
        }
        for (int i = 0; i < pizzaOrders.size(); i++) {
            if (pizzaOrders.get(i).getOrder_id() == order_id2) { // find order
                order = pizzaOrders.get(i);
                break;
            }
        }
        if (order == null) { //order not found
            throw new ApiRequestException("Order not found.", "404");
        } else if (order.getStatus().equalsIgnoreCase("cancelled") || order.getStatus().equalsIgnoreCase("delivered")) {
            throw new ApiRequestException("Unable to cancel an already cancelled or delivered order.", "422");
        } else if (order.minutesPassed() > 5) { //more than 5 minutes have passed
            throw new ApiRequestException("Unable to cancel your order after 5 minutes have elapsed.", "412");
        } else {
            order.setStatus("cancelled");
            return order; //maybe we shouldnt return entire order, but only the status and the order_id?
        }

    }


}
