package com.example.JTask.controllers;

import com.example.JTask.Exceptions.CustomerNotFoundException;
import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.model.Customers;
import com.example.JTask.model.Review;
import com.example.JTask.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @Autowired
    CustomerRepo customerRepo;

    @PostMapping("{custId}/review")
    public Review addReview(@PathVariable("custId")int id, @RequestBody Review review){
        Customers customers = customerRepo.findById(id).orElseThrow(()->new CustomerNotFoundException());
        review.setCustomer(customers);
        return reviewService.addReview(review);
    }
}
