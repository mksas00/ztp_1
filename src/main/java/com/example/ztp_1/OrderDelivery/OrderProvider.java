package com.example.ztp_1.OrderDelivery;

import com.example.ztp_1.Models.Order;

public interface OrderProvider {

    public void send(Order order);
    public String getWebsite();
}
