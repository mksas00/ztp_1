package com.example.ztp_1.OrderDelivery;

import com.example.ztp_1.Models.Order;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class DPDDelivery implements OrderProvider{

    @JsonProperty("website")
    String website = "https://www.dpd.com/pl/pl/";

    @Override
    public void send(Order order) {
        System.out.println("DPD delivery for order with id: " + order.getId() + " is being processed");
    }

    @Override
    public String getWebsite() {
        return this.website;
    }

    @Override
    public String toString() {
        return "DPDDelivery{" +
                "website='" + website + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(getWebsite());
    }
}
