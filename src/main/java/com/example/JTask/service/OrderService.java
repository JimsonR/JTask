package com.example.JTask.service;

import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.model.Orders;
import com.example.JTask.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private ProductRepo productRepo;

public Orders addOrder(Orders orders){
//    Product product = productRepo.findById(orders.getProduct().getProductId()).orElse(null);

//    orders.setProduct(product);
//    orders.setCustomer(orders.getCustomer());
    orders.setOrderDate(LocalDate.now());
    orders.setOrderStatus("Order placed");
    return orderRepo.save(orders);
}

public Product addProduct( String pname, String ptype){
    Product product = Product.builder().productName(pname).productType(ptype).build();
    return  productRepo.save(product);
}

public Orders cancelOrder(Orders order){
    Orders ordered = orderRepo.findById(order.getOrderId()).orElseThrow(()->new RuntimeException("No order was placed"));
    ordered.setOrderStatus("Order Cancelled");
    return orderRepo.save(ordered);
}







}
