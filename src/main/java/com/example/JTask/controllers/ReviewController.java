package com.example.JTask.controllers;

import com.example.JTask.exceptions.CustomerNotFoundException;
import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.model.Customers;
import com.example.JTask.model.Review;
import com.example.JTask.projection.CustomerProjection;
import com.example.JTask.projection.OrderProductProjection;
import com.example.JTask.service.CustomerService;
import com.example.JTask.service.OrderService;
import com.example.JTask.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private OrderService orderService;
    @Autowired
    private CustomerService customerService;

    @PostMapping("{custId}/review")
    public ResponseEntity<Review> addReview(@PathVariable("custId")int id, @RequestBody Review review){
        Customers customers = customerRepo.findById(id).orElseThrow(()->new CustomerNotFoundException());
        review.setCustomer(customers);
        return new ResponseEntity<>(reviewService.addReview(review),HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CustomerProjection>> showAll(){
        return new ResponseEntity<>(orderService
                        .printNotNullOrderDetails(orderRepo
                                .findAllReviews(OrderProductProjection.class)) ,HttpStatus.OK);
    }

    @PutMapping("{custId}/review")
    public ResponseEntity<Review> changeReview(@PathVariable("custId")int id, @RequestBody Review review  ){
        Customers customers = customerRepo.findById(review.getCustomer().getCustomerId()).orElseThrow(()->new CustomerNotFoundException());
        review.setCustomer(customers);
         return new ResponseEntity<>(reviewService.changeReview(review), HttpStatus.OK);
    }



}
