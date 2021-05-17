package com.example.demo.Pizza;

import main.java.com.example.demo.exception.ApiRequestException;
import com.fasterxml.jackson.annotation.JsonView;

@JsonView({Views.DeliveryTime.class, Views.OrderNotTime.class})
public class DeliveryAddress {
    private String street;
    private String city;
    private String country;
    private String zipcode;

    public DeliveryAddress (String street, String city, String country, String zipcode){
       if (street == null || city == null || country == null || zipcode == null) {
           throw new ApiRequestException("The format of the object is not valid", "400");
       }
       this.street= street;
       this.city= city;
       this.country= country;
       this.zipcode= zipcode;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getZipcode() {
        return zipcode;
    }
}
