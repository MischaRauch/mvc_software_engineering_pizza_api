package com.example.demo.Pizza;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1")
public class PizzaController {
    
    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
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

}
