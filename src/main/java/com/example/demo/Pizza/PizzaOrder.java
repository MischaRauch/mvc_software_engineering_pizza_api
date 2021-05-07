package com.example.demo.Pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/OrderPizza")
public class PizzaOrder {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaOrder(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @PostMapping
    public void registerNewPizza(@RequestBody Pizza pizza) {
        pizzaService.addNewPizza(pizza);
    }
}
