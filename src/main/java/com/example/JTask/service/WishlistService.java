package com.example.JTask.service;

import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.Repository.WishListRepo;
import com.example.JTask.exceptions.CustomerNotFoundException;
import com.example.JTask.exceptions.ProductNotFoundException;
import com.example.JTask.exceptions.WishListNotFoundException;
import com.example.JTask.model.Product;
import com.example.JTask.model.WishList;
import com.example.JTask.projection.CustomerProjection;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WishlistService {
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
    @Autowired
    private WishListRepo wishListRepo;

    public WishList addToWishlist(WishList wishList) {
        List<Integer> list = new ArrayList<>();
        for (Integer id : wishList.getProductId()) {

            Product pro = productRepo.findById(id).orElseThrow(() -> new ProductNotFoundException());
            list.add(id);
        }
        wishList.setProductId(list);
        return wishListRepo.save(wishList);

    }

    @Transactional
    public void updateWishList(int customerId, List<Integer> newProductIds) {
        Optional<WishList> wishListOptional = wishListRepo.findByCustomer_CustomerId(customerId);
        if (wishListOptional.isPresent()) {
            WishList wishList = wishListOptional.get();
            wishList.setProductId(newProductIds);
            wishListRepo.save(wishList);
        }
    else{
        WishList newWishList = new WishList();
        newWishList.setCustomer(customerRepo.findById(customerId).orElseThrow(()->new CustomerNotFoundException()));
        newWishList.setProductId(newProductIds);
        wishListRepo.save(newWishList);
    }

}





}
