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
        }
        catch (Exception e) {
            throw new ApiRequestException("Pizza not found", "404");
        }
    }

    @JsonView(Views.Short.class)
    @GetMapping("/order/{costumer_id}")
    public List<Order> returnAllOrders(@PathVariable String costumer_id) {
        List<Order> costumerAllOrders = new ArrayList<>();
        System.out.println("SIZE "+costumerAllOrders.size());
        try {
            int costumer_id2 = Integer.parseInt(costumer_id);
            for (int i =0; i<pizzaOrders.size(); i++){
                System.out.println("CUSTOMER ID 1 "+pizzaOrders.get(i).getCostumer_id());
                System.out.println("CUSTOMER ID 2 "+costumer_id2);
                if (pizzaOrders.get(i).getCostumer_id()==costumer_id2){
                    costumerAllOrders.add(pizzaOrders.get(i));
                    return costumerAllOrders;
                }
            }
            throw new ApiRequestException("Customer ID not found", "404");
        }
        catch (Exception e) {
            throw new ApiRequestException("Invalid ID supplied", "400");
        }
    }

    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order){
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
        }
        catch (Exception e) {
            throw new ApiRequestException("Order not found", "404");
        }
    }

/**
    SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter.serializeAllExcept("toppings", "note");
    FilterProvider filterProvider = new SimpleFilterProvider().addFilter("toppings_note_filter", simpleBeanPropertyFilter);

    List<MappingJacksonValue> mappingJacksonValue = new ArrayList<>();
    List<Pizza> mappedPizzas = pizzaService.getPizza();
        for (int i = 0; i<mappedPizzas.size(); i++){
        mappingJacksonValue.set(i)= new MappingJacksonValue(mappedPizzas.get(i));
        mappingJacksonValue.get(i).setFilters(filterProvider);
    }
        return mappingJacksonValue;
*/



}
