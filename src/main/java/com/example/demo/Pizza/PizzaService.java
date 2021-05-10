package com.example.demo.Pizza;

import java.util.*;

import org.springframework.stereotype.Service;

@Service
public class PizzaService extends RuntimeException {

    private LinkedList<Pizza> menu = new LinkedList<Pizza>();


	public List<Pizza> getPizza() {
		//return List.of(new Pizza("magahrita"));
        if (menu.isEmpty()) {
            addMenu();
        }
        return menu;
	}
	public Pizza getSinglePizza(int id) {

	    return menu.get(id);

    }

    public void addNewPizza(Pizza pizza) {
        //System.out.println(pizza);
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
