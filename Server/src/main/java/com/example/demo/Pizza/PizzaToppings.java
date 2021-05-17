package main.java.com.example.demo.Pizza;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "api/Pizza/id")
public class PizzaToppings {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaToppings(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }
}
