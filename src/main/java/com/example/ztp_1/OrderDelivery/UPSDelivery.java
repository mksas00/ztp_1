package com.example.ztp_1.OrderDelivery;

import com.example.ztp_1.Models.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UPSDelivery implements OrderProvider{

    @JsonProperty("website")
    String website = "https://www.ups.com/pl/pl/Home.page";

    @Override
    public void send(Order order) {
        System.out.println("UPS delivery for order with id: " + order.getId() + " is being processed");
    }

    @Override
    public String getWebsite() {
        return this.website;
    }

    @Override
    public String toString() {
        return "UPSDelivery{" +
                "website='" + website + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWebsite());
    }
}
