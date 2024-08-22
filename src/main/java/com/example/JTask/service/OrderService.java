package com.example.JTask.service;

import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.model.Customers;
import com.example.JTask.model.Orders;
import com.example.JTask.model.Product;
import com.example.JTask.projection.CustomerProjection;
import com.example.JTask.projection.OrderProductProjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

public List<CustomerProjection> printNotNullOrderDetails(List<OrderProductProjection> list) {
    List<OrderProductProjection> orders = list;
    List<CustomerProjection> list1 = new ArrayList<>();

    for (OrderProductProjection order : orders) {
        CustomerProjection projection = new CustomerProjection();
//        LinkedHashMap<String, Object> nonNullFields = new LinkedHashMap<> ();
        if(order.getCustomerId() != null){
//         nonNullFields.put("Customer id", order.getCustomerId());
         projection.setCustomerId(order.getCustomerId());

        }
        if(order.getCustomerName() != null){
//            nonNullFields.put("Customer Name", order.getCustomerName());
            projection.setCustomerName(order.getCustomerName());
        }
        if (order.getProductId() != null)
        {
//            nonNullFields.put("Product Id", order.getProductId());
            projection.setProductId(order.getProductId());

        }
        if (order.getProductName() != null) {
//            nonNullFields.put("Product Name", order.getProductName());
            projection.setProductName(order.getProductName());
        }
        if (order.getProductType() != null) {
//            nonNullFields.put("Product Type", order.getProductType());
            projection.setProductType(order.getProductType());
        }
        if (order.getOrderId() != null) {
//            nonNullFields.put("Order Id", order.getOrderId());
            projection.setOrderId(order.getOrderId());
        }
        if (order.getOrderDate() != null) {
//            nonNullFields.put("Order Date", order.getOrderDate());
            projection.setOrderDate(order.getOrderDate());
        }
        if (order.getOrderStatus() != null) {
//            nonNullFields.put("Order Status", order.getOrderStatus());
            projection.setOrderStatus(order.getOrderStatus());
        }
        list1.add(projection);
    }
return list1;

}

}
