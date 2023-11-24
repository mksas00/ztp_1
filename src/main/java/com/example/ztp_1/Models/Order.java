package com.example.ztp_1.Models;

import com.example.ztp_1.OrderDelivery.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    @JsonProperty("id")
    private int id;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("provider")
    private String provider;
    @JsonProperty("totalPrice")
    private double totalPrice;
    @JsonIgnore
    private OrderProvider orderProvider = null;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date date;

    public void setOrderProvider(String providerName) {

        switch (providerName){
            case "InPost":
                this.orderProvider = new InPostDelivery();
            case "DHL":
                this.orderProvider = new DHLDelivery();
            case "DPD":
                this.orderProvider = new DPDDelivery();
            case "UPS":
                this.orderProvider = new UPSDelivery();
            case "FedEx":
                this.orderProvider = new FedExDelivery();
        }
    }

    public void deliver()
    {
        this.orderProvider.send(this);
    }
}