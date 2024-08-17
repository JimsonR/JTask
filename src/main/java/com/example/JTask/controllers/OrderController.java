package com.example.JTask.controllers;

import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.model.Orders;
import com.example.JTask.model.Product;
import com.example.JTask.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderService orderService;



    @PostMapping("addOrder")
    public Orders addOrder(@RequestParam("prod") int id ){
//        Product product= order.getProduct();
//        orderService.addOrder()
        Orders order = Orders.builder().product(productRepo.findById(id).orElse(null)).build();
       return orderService.addOrder(order);

    }

    @GetMapping("getOrder/{id}")
    public Orders getOrder(@PathVariable("id")int id){

        return orderRepo.findOrdersById(id);
//        return orderRepo.findById(id).orElse(null);
    }

    @GetMapping("allOrders")
    public List<Orders> allOrders(){
        return orderRepo.findAll();
    }

    @PutMapping("updateOrder")
    public Orders updateOrder(@RequestBody Orders orders ){


        Orders existingOrder = orderRepo.findById(orders.getOrderId()).orElseThrow(()-> new RuntimeException("order not found"));
        Product product = productRepo.findById(
                 orders
                .getProduct()
                .getProductId())
                .orElse(null);
        existingOrder
                .setOrderDate(orders.getOrderDate());
        existingOrder
                .setProduct(product);
        return orderRepo
                .save(existingOrder);
    }

//    @PostMapping("updateOrder")
//    public Orders updateOrder(){
//
//    }

}
