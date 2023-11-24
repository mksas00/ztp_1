package com.example.ztp_1.Filters;

import com.example.ztp_1.DTO.OrderDTO;
import com.example.ztp_1.Models.Order;
import com.example.ztp_1.Models.OrderManager;
import com.example.ztp_1.Models.Product;
import com.example.ztp_1.Models.ProductManager;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;


public class OrderFilter {

    private ProductManager productManager = new ProductManager();
    private OrderManager orderManager = new OrderManager();

    public Order filterOrder(OrderDTO orderDTO){

        Order filteredOrder = new Order();
        filteredOrder.setId(orderManager.getOrderCount());

        /**
         * Check if the product with the summited name exists in the database
         */
        Boolean doesProductExist = false;
        for (Product product : productManager.getAllProducts()) {
            if(product.getName().equals(orderDTO.getProductName())){
                    doesProductExist = true;
            }
            if (doesProductExist)break;
        }
        if (!doesProductExist){
            throw new IllegalArgumentException("Product Name is wrong");
        }
        filteredOrder.setProductName(orderDTO.getProductName());

        /**
         * Check if the quantity of the ordered product is correct and set it
         */
        if(orderDTO.getQuantity() <= 0){
            throw new IllegalArgumentException("Product quantity is wrong");
        }
        filteredOrder.setQuantity(orderDTO.getQuantity());

        /**
         * Check if the provider name is correct and set the order provider name
         */
        String orderProvider = orderDTO.getProvider();
        System.out.println(orderProvider);
        if(!orderProvider.equals("InPost") && !orderProvider.equals("DPD") && !orderProvider.equals("DHL") && !orderProvider.equals("UPS") && !orderProvider.equals("FedEx")){
            throw new IllegalArgumentException("Order provider is wrong");
        }
        filteredOrder.setProvider(orderProvider);

        /**
         * Set the total price of the submitted order
         */
        double productPrice = 0;
        Optional<Product> product = productManager.retrieveProduct(orderDTO.getProductName());
        if(product.isPresent()){
            productPrice = product.get().getPrice();
        }
        filteredOrder.setTotalPrice(orderDTO.getQuantity() * productPrice );

        /**
         * Set the date at which the order was created
         */
        LocalDateTime localDateTime = LocalDateTime.now();
        filteredOrder.setDate(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
        /**
         * Set order provider of the object
         */
        filteredOrder.setOrderProvider(orderDTO.getProvider());
        return filteredOrder;
    }
}
