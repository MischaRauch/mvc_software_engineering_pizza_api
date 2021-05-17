package com.example.demo.Pizza;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class PizzaService extends RuntimeException {

    private List<Pizza> menu = new ArrayList<>();


	public List<Pizza> getPizza() {
        if (menu.isEmpty()) {
            addMenu();
        }
        return menu;
	}
	public Pizza getSinglePizza(int id) {

	    return menu.get(id);

    }

    public void addNewPizza(Pizza pizza) {
        menu.add(pizza);
    }

    public void addMenu() {
        String[] ingredientsMargarita = new String[]{"Basil", "Tomato", "Mozzarella"};
        String[] ingredientsHawaian = new String[]{"Tomato", "Mozzarella", "Pineaple", "Jam"};
        String[] ingredientsSpinnach = new String[]{ "Tomato", "Mozzarella", "Spinach", "Egg"};
        menu.add(new Pizza("Magarita", true, 8,ingredientsMargarita));
        menu.add(new Pizza("Hawaian",false, 12, ingredientsHawaian));
        menu.add(new Pizza("Spinnach", true, 10, ingredientsSpinnach));
    }

    
}
