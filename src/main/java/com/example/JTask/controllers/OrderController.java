package com.example.JTask.controllers;

import com.example.JTask.exceptions.OrderNotFoundException;
import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.exceptions.ProductNotFoundException;
import com.example.JTask.model.Orders;
import com.example.JTask.model.Product;
import com.example.JTask.projection.CustomerProjection;
import com.example.JTask.projection.OrderProductProjection;
import com.example.JTask.service.CustomerService;
import com.example.JTask.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

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
                                                        .orElseThrow(()->new ProductNotFoundException());
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

    Orders order = orderRepo.findById(id).orElseThrow(() -> new OrderNotFoundException());
    return ResponseEntity.ok(orderService.cancelOrder(order));

}

    @PutMapping("addImageToProduct")
    public ResponseEntity<Product> updateProd(@RequestParam("id") int id,@RequestParam("image") MultipartFile file)throws Exception {
        Product product = productRepo.findById(id).orElseThrow(()->new ProductNotFoundException());

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
