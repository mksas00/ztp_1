package com.example.ztp_1.Models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderManager {

    private final String filePath;

    public OrderManager() {
        this.filePath = "C:\\Users\\pc\\ztp_1\\orders.json";
    }

    public void addOrder(Order newOrder) {
        List<Order> existingOrders = readExistingOrders();
        existingOrders.add(newOrder);
        writeOrdersToFile(existingOrders);
        newOrder.deliver();
    }

    public Optional<Order> retrieveOrder(int orderId) {
        List<Order> existingOrders = readExistingOrders();
        return existingOrders.stream()
                .filter(order -> order.getId() == orderId)
                .findFirst();
    }

    public void updateOrder(int orderId, Order updatedOrder) {
        List<Order> existingOrders = readExistingOrders();
        for (int i = 0; i < existingOrders.size(); i++) {
            if (existingOrders.get(i).getId() == orderId) {
                existingOrders.set(i, updatedOrder);
                break;
            }
        }
        writeOrdersToFile(existingOrders);
    }


    private List<Order> readExistingOrders() {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Order.class);

        File file = new File(filePath);

        if (file.exists()) {
            try {
                return objectMapper.readValue(file, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new ArrayList<>();
    }

    public List<Order> getAllOrders(){
        return readExistingOrders();
    }


    public int getOrderCount(){

        List<Order> orders = readExistingOrders();
        return orders.size();
    }

    private void writeOrdersToFile(List<Order> orders) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            objectMapper.writeValue(new File(filePath), orders);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


