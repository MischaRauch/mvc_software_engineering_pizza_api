package main.java.com.example.demo.Pizza;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import main.java.com.example.demo.exception.ApiRequestException;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
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

        Thread thread = new Thread() { //changing status to delivered
            public void run() {
                while (true) {
                    for (int i = 0; i < pizzaOrders.size(); i++) {
                        pizzaOrders.get(i).checkIfDelivered();
                    }
                    try {
                        TimeUnit.MINUTES.sleep(1);   //this means that stops the while for 1 minute and then starts again
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }; thread.start();
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

    @JsonView(Views.OrderNotTime.class)
    @GetMapping("/order/{costumer_id}")
    public List<Order> returnAllOrders(@PathVariable String costumer_id) {
        List<Order> costumerAllOrders = new ArrayList<>();
        int costumer_id2;
        try {
            costumer_id2 = Integer.parseInt(costumer_id);
        }
        catch (Exception e) {
            throw new ApiRequestException("Invalid ID supplied", "400");
        }
        for (int i = 0; i < pizzaOrders.size(); i++) {
            if (pizzaOrders.get(i).getCostumer_id() == costumer_id2) {
                costumerAllOrders.add(pizzaOrders.get(i));
                return costumerAllOrders;
            }
        }
        throw new ApiRequestException("Customer ID not found", "404");
    }

    @JsonView(Views.DeliveryTime.class)
    @PostMapping("/order")
    public Order createOrder(@RequestBody Order order) {
        order.findPizzasByID(pizzaService.getPizza()); //initializing pizzas based on their ID
        pizzaOrders.add(order);
        return order;
    }

    @JsonView(Views.DeliveryTime.class)
    @GetMapping("/order/deliverytime/{id}")
    public Order returnDeliveryTime(@PathVariable String id) {
        Order costumerAllOrders;
        try {
            int id2 = Integer.parseInt(id);
            for (Order order : pizzaOrders) {
                if (order.getOrder_id() == id2) {
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
