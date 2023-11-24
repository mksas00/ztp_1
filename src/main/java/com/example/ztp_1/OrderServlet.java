package com.example.ztp_1;

import com.example.ztp_1.DTO.OrderDTO;
import com.example.ztp_1.Filters.OrderFilter;
import com.example.ztp_1.Models.OrderManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import javax.persistence.criteria.Order;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet(name = "OrderServlet", value = "/Servlet")
public class OrderServlet extends HttpServlet {

    private OrderManager orderManager = new OrderManager();
    private Gson gson = new Gson();
    ObjectMapper objectMapper = new ObjectMapper();
    private OrderFilter orderFilter = new OrderFilter();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.setStatus(200);
        out.print(gson.toJson(orderManager.getAllOrders()));
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestData = request.getReader().lines().collect(Collectors.joining());
        System.out.println(requestData);
        try {
            OrderDTO requestOder = objectMapper.readValue(requestData, OrderDTO.class);
            orderManager.addOrder(orderFilter.filterOrder(requestOder));
        }
        catch (IllegalArgumentException exception){
            exception.printStackTrace();
            System.err.println(exception.getMessage());
            response.setStatus(422);
        }
        catch (Exception exception){
            response.setStatus(400);
        }

        response.setStatus(201);

    }
}
