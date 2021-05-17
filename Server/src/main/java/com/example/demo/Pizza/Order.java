package main.java.com.example.demo.Pizza;

import main.java.com.example.demo.exception.ApiRequestException;
import com.fasterxml.jackson.annotation.JsonView;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.time.LocalDateTime;


public class Order {
    @JsonView({Views.DeliveryTime.class, Views.OrderNotTime.class})
    private int customer_id;
    private  static int numberOfOrders =1;
    @JsonView({Views.CancelOrder.class, Views.DeliveryTime.class, Views.OrderNotTime.class})
    private int order_id;
    @JsonView({Views.CancelOrder.class, Views.DeliveryTime.class, Views.OrderNotTime.class})
    private String status;
    @JsonView({Views.DeliveryTime.class, Views.OrderNotTime.class})
    private String payment_type;
    @JsonView({Views.DeliveryTime.class, Views.OrderNotTime.class})
    private final LocalDateTime ordered_at;
    @JsonView(Views.DeliveryTime.class)
    private final LocalDateTime deliveryTime;
    @JsonView({Views.DeliveryTime.class, Views.OrderNotTime.class})
    private List <Pizza> pizzas;
    @JsonView({Views.DeliveryTime.class, Views.OrderNotTime.class})
    private DeliveryAddress delivery_address;
    @JsonView({Views.DeliveryTime.class, Views.OrderNotTime.class})
    private final boolean takeaway;
    @JsonView({Views.DeliveryTime.class, Views.OrderNotTime.class})
    private String note;

    public Order(String payment_type, DeliveryAddress delivery_address, boolean takeaway, int customer_id, List <Pizza> order, String note) {
        if (payment_type == null || delivery_address == null || customer_id == 0) {
            System.out.println("Got here");
            System.out.println("payemnet "+ payment_type);
            System.out.println("delivery "+ delivery_address);
            System.out.println("id "+ customer_id);
            System.out.println("order "+ order);
            throw new ApiRequestException("The format of the object is not valid", "400");
        }

        status = "In Progress";
        this.pizzas = order;
        this.order_id = numberOfOrders; numberOfOrders++;
        this.customer_id = customer_id;
        this.payment_type = payment_type;
        this.takeaway = takeaway;
        this.note = note;
        if (takeaway==false){
            this.delivery_address= delivery_address;
        }
        ordered_at = LocalDateTime.now();
        deliveryTime = LocalDateTime.now().plus(Duration.of(10, ChronoUnit.MINUTES));


    }
    public void findPizzasByID(List<Pizza> menu){
        boolean found = false;
        for (int i =0; i<pizzas.size(); i++){
            for (int j=0; j<menu.size(); j++){
                if (pizzas.get(i).getPizza_id()==menu.get(j).getPizza_id()){
                    pizzas.get(i).setName( menu.get(j).getName());
                    pizzas.get(i).setPrice( menu.get(j).getPrice());
                    pizzas.get(i).setToppings( menu.get(j).getToppings());
                    pizzas.get(i).setVegetarian( menu.get(j).getVegetarian());
                    found = true;
                }
            }
        }
        if (found == false) { throw new ApiRequestException("The format of the object is not valid", "400");}
    }

    public int getCostumer_id(){
        return customer_id;
    }

    public LocalDateTime getOrdered_at() {
        return ordered_at;
    }
    public LocalDateTime getDeliveryTime() {
        return deliveryTime;
    }
    public static int getNumberOfOrders() {
        return numberOfOrders;
    }

    public int getOrder_id() {
        return order_id;
    }

    public String getStatus() {
        return status;
    }

    public String getPayment_type() {
        return payment_type;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public DeliveryAddress getDelivery_address() {
        return delivery_address;
    }

    public String getNote(){ return note; }


    public int minutesPassed(){
       LocalDateTime currentTime = LocalDateTime.now();
       return currentTime.minusMinutes(ordered_at.getMinute()).getMinute();
    }

    public boolean checkIfDelivered (){
        LocalDateTime currentTime = LocalDateTime.now();
        if ( currentTime.isAfter(deliveryTime) || currentTime.isEqual(deliveryTime) ){
            setStatus(("delivered"));
            return true;
        }
        return false;
    }

    public void setStatus(String status){
        this.status= status;
    }
}

