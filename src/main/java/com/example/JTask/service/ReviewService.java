package com.example.JTask.service;

import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.Repository.ReviewRepo;
import com.example.JTask.exceptions.ProductNotFoundException;
import com.example.JTask.model.Customers;
import com.example.JTask.model.Product;
import com.example.JTask.model.Review;
import jakarta.transaction.Transactional;
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

        Product product = productRepo.findById(review.getProduct().getProductId()).orElseThrow((()->new ProductNotFoundException()));
        review.setProduct(product);

        review.setReviewDate(LocalDate.now());

        return reviewRepo.save(review);

    }

    @Transactional
    public Review changeReview(Review review){

        Product product = productRepo.findById(review.getProduct().getProductId()).orElseThrow((()->new ProductNotFoundException()));
        review.setProduct(product);

        review.setReviewDate(LocalDate.now());

       int rowsAffected =  reviewRepo.changeReview(review.getRating(),review.getComment(),LocalDate.now(),review.getCustomer().getCustomerId(),review.getProduct().getProductId());
       if(rowsAffected>0){
           System.out.println("update successful");
       }else {
           System.out.println("not updated");
       }
       return review;
    }




}
