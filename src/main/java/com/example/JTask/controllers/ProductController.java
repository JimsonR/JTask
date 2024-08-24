package com.example.JTask.controllers;

import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.exceptions.ProductNotFoundException;
import com.example.JTask.model.Product;
import com.example.JTask.service.CustomerService;
import com.example.JTask.service.OrderService;
import com.example.JTask.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ProductService productService;

    @PutMapping("{pid}/updateProduct")
    public ResponseEntity<Product> updateProduct(@PathVariable("pid")int id, @RequestBody Product product){
      Product  product1 = productRepo.findById(id).orElseThrow(()->new ProductNotFoundException());
      product1.setProductPrice(product.getProductPrice());
      productService.updateProduct(product1);
      return new ResponseEntity<>(product1, HttpStatus.OK);
    }

}
