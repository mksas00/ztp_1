package com.example.ztp_1.OrderDelivery;

import com.example.ztp_1.Models.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class FedExDelivery implements OrderProvider{

    @JsonProperty("website")
    String website = "https://www.fedex.com/pl-pl/home.html";

    @Override
    public void send(Order order) {
        System.out.println("FedEx delivery for order with id: " + order.getId() + " is being processed");
    }

    @Override
    public String getWebsite() {
        return website;
    }

    @Override
    public String toString() {
        return "FedExDelivery{" +
                "website='" + website + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWebsite());
    }
}
