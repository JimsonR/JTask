package com.example.JTask.controllers;

import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.model.Customers;
import com.example.JTask.model.Orders;
import com.example.JTask.model.Product;
import com.example.JTask.service.CustomerService;
import com.example.JTask.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class OrderController {
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
    public ResponseEntity<List<Orders>> allOrders(){
        return new ResponseEntity<>(orderRepo.findAll(), HttpStatus.BAD_GATEWAY);
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

    @GetMapping("getProduct")
    public String getProduct(int id){
       return orderRepo.findProductName(id);
    }

    @GetMapping("getOrderProduct")
    public String getProductOrdDate(int id){
        return orderRepo.findOrderDateProductName(id);
    }

    @GetMapping("getOrderProducts")
    public List<Object> getProductOrdDates(){
        return orderRepo.findOrderDateProductNames();
    }

    @PutMapping("cancelOrder")
    public Orders cancelProduct(@RequestParam("id") int id){
       Orders order =  orderRepo.findById(id).orElseThrow(()->new RuntimeException("no order found"));

        return orderService.cancelOrder(order);
    }

    @PostMapping("{custId}/addOrder")
    public Customers addCustOrder(@PathVariable("custId")int id, @RequestParam("prod") int od){
        Customers customers = customerRepo
                                    .findById(id)
                                        .orElseThrow(()->new RuntimeException("no customer found"));
        Orders order = orderService.addOrder(Orders.builder()
                                        .customer(customers)
                                            .product(productRepo
                                                .findById(od)
                                                    .orElse(null)).build());
        Set<Orders>  set = customers.getCustomerOrders();

       set.add(order);

       customers.setCustomerOrders(set);

       return customerService.addCustomerOrder(customers);
    }

    @GetMapping("{custId}/getOrders")
    public Customers getCustOrder(@PathVariable("custId")int id){

        return customerRepo.findById(id).orElse(null);

//        return customers;
    }

    @PutMapping("{custId}/cancelOrder")
    public Orders cancelOrder(@PathVariable("custId")int id, @RequestParam("oid") int oid){

        Orders order = orderRepo.findById(oid).orElseThrow(()->new RuntimeException("no order found"));
      return   orderService.cancelOrder(order);

    }

    @DeleteMapping("{custId}/delOrder")
    public Customers deleteOrder(@PathVariable("custId")int id, @RequestParam("oid") int oid){

        orderRepo.deleteById(oid);
        return  customerRepo.findById(id).orElse(null) ;

    }









}
