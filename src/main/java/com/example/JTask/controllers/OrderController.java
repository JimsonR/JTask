package com.example.JTask.controllers;

import com.example.JTask.Exceptions.CustomerNotFoundException;
import com.example.JTask.Exceptions.NoProductException;
import com.example.JTask.Exceptions.OrderNotFoundException;
import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.model.Customers;
import com.example.JTask.model.Orders;
import com.example.JTask.model.Product;
import com.example.JTask.projection.CustomerProjection;
import com.example.JTask.projection.OrderProductProjection;
import com.example.JTask.service.CustomerService;
import com.example.JTask.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.hibernate.query.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import com.example.JTask.*;

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


        Orders existingOrder = orderRepo.findById(orders.getOrderId()).orElseThrow(()-> new OrderNotFoundException());
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
    public List<CustomerProjection> getProductOrdDates(){

        return orderService.printNotNullOrderDetails(orderRepo.findOrderDateProductNames(OrderProductProjection.class));
    }

    @GetMapping("getAllOrders")
    public List<CustomerProjection> getAllProducts(){

        List<Object> list= new ArrayList<>();

        return orderService.printNotNullOrderDetails(orderRepo.findAllOrders(OrderProductProjection.class));

    }

    @PutMapping("cancelOrder")
    public ResponseEntity<?> cancelProduct(@RequestParam("id") int id)throws Exception{
//try {
    Orders order = orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException());
    return ResponseEntity.ok(orderService.cancelOrder(order));
//}
}


    @PostMapping("{custId}/addOrder")
    public Customers addCustOrder(@PathVariable("custId")int id, @RequestParam("prod") int od) throws Exception{

        Customers customers = customerRepo
                                    .findById(id)
                                        .orElseThrow(()->new OrderNotFoundException());
        Orders order = orderService.addOrder(Orders.builder()
                                                    .customer(customers)
                                                        .product(productRepo
                                                            .findById(od)
                                                                .orElseThrow(()-> new NoProductException())).build());
        Set<Orders>  set = customers.getCustomerOrders();

       set.add(order);

       customers.setCustomerOrders(set);

       return customerService.addCustomerOrder(customers);
    }


    @GetMapping("{custId}/getOrders")
    public Customers getCustOrder(@PathVariable("custId")int id){

        return customerRepo.findById(id).orElseThrow(()->new CustomerNotFoundException());

//        return customers;
    }

    @PutMapping("{custId}/cancelOrder")
    public ResponseEntity<?> cancelOrder(@PathVariable("custId")int id, @RequestParam("oid") int oid)throws Exception{
//try {
    Orders order = orderRepo.findById(oid).orElseThrow(() -> new OrderNotFoundException());
    return   ResponseEntity.ok(orderService.cancelOrder(order));
//}catch (RuntimeException e){
//    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//}
    }

    @DeleteMapping("{custId}/delOrder")
    public Customers deleteOrder(@PathVariable("custId")int id, @RequestParam("oid") int oid){

        orderRepo.deleteById(oid);
        return  customerRepo.findById(id).orElse(null) ;

    }

    @GetMapping("getAllCustomerOrders")
    public List<?> getAllCustOrder(){
        return orderService.printNotNullOrderDetails(orderRepo.findAllCustomerOrders(OrderProductProjection.class));
    }

    @PutMapping("addImageToProduct")
    public ResponseEntity<Product> updateProd(@RequestParam("id") int id,@RequestParam("image") MultipartFile file)throws Exception {
        Product product = productRepo.findById(id).orElseThrow(()->new NoProductException());

       product.setProductImg(file.getBytes());

   return ResponseEntity.ok(productRepo.save(product));
    }

    @GetMapping("getImage")
    public ResponseEntity<byte[]> getProdImg(@RequestParam int id)throws Exception{
        Product product = productRepo.findById(id).orElse(null);

        byte[] img = product.getProductImg();

        String contentType = "image/jpeg";

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                .body(img);

    }



}
