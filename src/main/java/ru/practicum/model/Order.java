package ru.practicum.model;

import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

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

    public Order() {
        firstName = RandomStringUtils.randomAlphabetic(5);
        lastName = RandomStringUtils.randomAlphabetic(5);
        address = RandomStringUtils.randomAlphabetic(10);
        metroStation = "Полянка";
        phone = "89997776655";
        rentTime = 2;
        deliveryDate = "2024-06-20";
        comment = "";
        color = List.of("BLACK");
    }
}