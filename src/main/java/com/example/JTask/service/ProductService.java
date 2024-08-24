package com.example.JTask.service;

import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.model.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProductRepo productRepo;
    @Transactional
    public void updateProduct(Product product){

        productRepo.updateVal(product.getProductPrice(), product.getProductId());
    }
}
