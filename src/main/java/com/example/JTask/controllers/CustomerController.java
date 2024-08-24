package com.example.JTask.controllers;

import com.example.JTask.exceptions.CustomerNotFoundException;
import com.example.JTask.exceptions.OrderNotFoundException;
import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.exceptions.ProductNotFoundException;
import com.example.JTask.model.Customers;
import com.example.JTask.model.Orders;
import com.example.JTask.projection.CustomerProjection;
import com.example.JTask.projection.OrderProductProjection;
import com.example.JTask.service.CustomerService;
import com.example.JTask.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class CustomerController {

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



    @PostMapping("{custId}/addOrder")
    public Customers addCustOrder(@PathVariable("custId")int id, @RequestParam("prod") int od){

        Customers customers = customerRepo
                .findById(id)
                .orElseThrow(()->new OrderNotFoundException());
        Orders order = orderService.addOrder(Orders.builder()
                .customer(customers)
                .product(productRepo
                        .findById(od)
                        .orElseThrow(()-> new ProductNotFoundException())).build());
        Set<Orders> set = customers.getCustomerOrders();

        set.add(order);

        customers.setCustomerOrders(set);

        return customerService.addCustomerOrder(customers);
    }

    @PutMapping("{custId}/cancelOrder")
    public ResponseEntity<?> cancelOrder(@PathVariable("custId")int id, @RequestParam("oid") int oid)throws Exception{

        Orders order = orderRepo.findById(oid).orElseThrow(() -> new OrderNotFoundException());
        return   ResponseEntity.ok(orderService.cancelOrder(order));

    }

    @DeleteMapping("{custId}/delOrder")
    public ResponseEntity<Customers> deleteOrder(@PathVariable("custId")int id, @RequestParam("oid") int oid){

        orderRepo.deleteById(oid);
        return  ResponseEntity.status(200).body(customerRepo.findById(id).orElseThrow(()->new CustomerNotFoundException()));

    }

    @GetMapping("getAllCustomerOrders")
    public List<?> getAllCustOrder(){
        return orderService.printNotNullOrderDetails(orderRepo.findAllCustomerOrders(OrderProductProjection.class));
    }

    @GetMapping("{custId}/getOrders")
    public ResponseEntity<?> getCustOrder(@PathVariable("custId")int id){
        List<List<CustomerProjection>> list= new ArrayList<>();

        Set<Orders> list1 = customerRepo.findById(id).get().getCustomerOrders();

        CustomerProjection customerProjection = new CustomerProjection();
        customerProjection.setOrdersList(list1);
        customerProjection.setTotalPrice(customerRepo.totalAmount(id));

        list.add(orderService
                .printNotNullOrderDetails(customerRepo
                        .getTotalAmount(OrderProductProjection.class)));


        return ResponseEntity.ok(customerProjection);

//        return customers;
    }

}
