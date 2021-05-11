package com.example.demo.Pizza;

public class DeliveryAddress {
    private String street;
    private String city;
    private String country;
    private String zipcode;

    public DeliveryAddress (String street, String city, String country, String zipcode){
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
