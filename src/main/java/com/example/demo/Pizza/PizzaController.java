package com.example.demo.Pizza;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
public class PizzaController {
    
    private final PizzaService pizzaService;
    private final PizzaOrder pizzaOrder;

    @Autowired
    public PizzaController(PizzaService pizzaService, PizzaOrder pizzaOrder) {
        this.pizzaOrder = pizzaOrder;
        this.pizzaService = pizzaService;
    }

    @GetMapping("/pizza")
	public List<Pizza> returnPizza() {
        return pizzaService.getPizza();
	}

	@GetMapping(path = "/pizza/{id}")
     //public Pizza returnSinglePizza(@PathVariable(value = "id", defaultValue = "1") String id) {
    public Pizza returnSinglePizza(@PathVariable String id) {
        int id2 = Integer.parseInt(id);
        return pizzaService.getSinglePizza(id2);
    }

    @GetMapping("/order")
    public LinkedList<Order> returnAllOrders() {
        return pizzaOrder.getAll_orders();
    }



}
