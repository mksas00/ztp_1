package com.example.ztp_1.OrderDelivery;

import com.example.ztp_1.Models.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DHLDelivery implements OrderProvider{

    @JsonProperty("website")
    String website = "https://www.dhl.com/pl-pl/home.html";

    @Override
    public void send(Order order) {
        System.out.println("DHL delivery for order with id: " + order.getId() + " is being processed");
    }

    @Override
    public String getWebsite() {
        return this.website;
    }

    @Override
    public String toString() {
        return "DHLDelivery{" +
                "website='" + website + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWebsite());
    }
}
