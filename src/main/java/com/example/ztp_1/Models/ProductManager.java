package com.example.ztp_1.Models;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductManager {
    private final String filePath;

    public ProductManager() {
        this.filePath = "C:\\Users\\pc\\ztp_1\\products.json";
    }

    public void addProduct(Product newProduct) {
        List<Product> existingProducts = readExistingProducts();
        existingProducts.add(newProduct);
        writeProductsToFile(existingProducts);
    }

    public void deleteProduct(String productName) {
        List<Product> existingProducts = readExistingProducts();
        existingProducts.removeIf(product -> product.getName().equals(productName));
        writeProductsToFile(existingProducts);
    }

    public Optional<Product> retrieveProduct(String productName) {
        List<Product> existingProducts = readExistingProducts();
        return existingProducts.stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst();
    }

    public List<Product> getAllProducts(){
        return readExistingProducts();
    }

    private List<Product> readExistingProducts() {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Product.class);

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

    private void writeProductsToFile(List<Product> products) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            objectMapper.writeValue(new File(filePath), products);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
