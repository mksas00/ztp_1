package com.example.ztp_1;

import com.example.ztp_1.Models.Product;
import com.example.ztp_1.Models.ProductManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.stream.Collectors;


@WebServlet(name = "ProductServlet")
public class ProductServlet extends HttpServlet {

    private ProductManager productManager = new ProductManager();
    private Gson gson = new Gson();
    ObjectMapper objectMapper = new ObjectMapper();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        response.setStatus(200);
        out.print(gson.toJson(productManager.getAllProducts()));
        out.flush();
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String requestData = request.getReader().lines().collect(Collectors.joining());
        Product newProduct;
        System.out.println(requestData);
        try {
            newProduct = objectMapper.readValue(requestData,Product.class);
            productManager.addProduct(newProduct);
            response.setStatus(201);
        }
        catch (Exception exception){
            response.setStatus(400);
            response.getWriter().print(exception.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String productNameToDelete = req.getParameter("productName");
            productManager.deleteProduct(productNameToDelete);
            resp.setStatus(200);
        }
        catch (Exception exception){
            resp.setStatus(400);
            resp.getWriter().print(exception.getMessage());
        }

    }
}

