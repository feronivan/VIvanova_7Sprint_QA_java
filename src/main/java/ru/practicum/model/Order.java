package ru.practicum.model;

import lombok.Data;
import java.util.List;

@Data
public class Order {
    private String firstName;
    private String lastName;
    private String address;
    private String metroStation;
    private String phone;
    private int rentTime;
    private String deliveryDate;
    private List<String> color;
    private String comment;

//    public Order(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.address = address;
//        this.metroStation = metroStation;
//        this.phone = phone;
//        this.rentTime = rentTime;
//        this.deliveryDate = deliveryDate;
//        this.comment = comment;
//        this.color = color;
//    }
}