package com.example.ztp_1.DTO;

import com.example.ztp_1.Models.Order;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    @JsonProperty("productName")
    private String productName;
    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("provider")
    private String provider;

}
