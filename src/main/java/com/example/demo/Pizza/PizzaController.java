package com.example.demo.Pizza;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.example.demo.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
     //public Pizza returnSinglePizza(@PathVariable(value = "id", defaultValue = "1") String id) {
    public Pizza returnSinglePizza(@PathVariable String id) {
        try {
            int id2 = Integer.parseInt(id);
            return pizzaService.getSinglePizza(id2);
        }
        catch (Exception e) {
            throw new ApiRequestException("This Pizza is not yet in our menu");
        }
    }

    @GetMapping("/order/{costumer_id}")
    public List<Order> returnAllOrders(@PathVariable String costumer_id) {
        List<Order> costumerAllOrders = new ArrayList<>();
        try {
            int costumer_id2 = Integer.parseInt(costumer_id);
            for (int i =0; i<pizzaOrders.size(); i++){
                if (pizzaOrders.get(i).getCostumer_id()==costumer_id2){
                    costumerAllOrders.add(pizzaOrders.get(i));
                }
            }
            return costumerAllOrders;
        }
        catch (Exception e) {
            throw new ApiRequestException("Invalid ID supplied");
        }
    }

    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order){
        pizzaOrders.add(order);
        System.out.println(order.getCostumer_id());
        return order;
    }






}
