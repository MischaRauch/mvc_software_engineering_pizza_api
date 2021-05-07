package com.example.demo.Pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/Pizza/id")
public class PizzaToppings {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaToppings(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    //@GetMapping
    //public String[] returnToppings(Pizza pizza) { return pizza.getToppings(); }
}
