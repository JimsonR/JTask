package com.example.JTask.service;

import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.Repository.ReviewRepo;
import com.example.JTask.model.Customers;
import com.example.JTask.model.Product;
import com.example.JTask.model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
@Service
public class ReviewService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    ProductRepo productRepo;
    @Autowired
    ReviewRepo reviewRepo;

    public Review addReview(Review review){

//        Customers customers = customerRepo.findById(review.getReviewId()).orElse(null);
        Product product = productRepo.findById(review.getProduct().getProductId()).orElseThrow(()->new RuntimeException("no product found"));

//        review.setCustomer(customers);
        review.setProduct(product);
        review.setReviewDate(LocalDate.now());

        return reviewRepo.save(review);

    }

}
