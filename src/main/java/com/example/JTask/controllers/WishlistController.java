package com.example.JTask.controllers;

import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.Repository.WishListRepo;
import com.example.JTask.exceptions.CustomerNotFoundException;
import com.example.JTask.exceptions.ProductNotFoundException;
import com.example.JTask.model.Product;
import com.example.JTask.model.WishList;
import com.example.JTask.projection.CustomerProjection;
import com.example.JTask.service.CustomerService;
import com.example.JTask.service.OrderService;
import com.example.JTask.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WishlistController {

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
    @Autowired
    WishlistService wishlistService;

@PostMapping("{custId}/addToWishlist")
    public WishList addtoWishList(@PathVariable("custId")int id , @RequestBody WishList wishList) {
    wishList.setCustomer(customerRepo.findById(id).orElseThrow(()->new CustomerNotFoundException()));
    return wishlistService.addToWishlist(wishList);
}

@GetMapping("{custId}/getWishList")
    public ResponseEntity<List<CustomerProjection>> getWishListedProducts(@PathVariable("custId") int id){
    if(customerRepo.existsById(id)){

    }
    else{
        throw new CustomerNotFoundException();
    }
    return new ResponseEntity<>(orderService.printNotNullOrderDetails(customerRepo.getCustomerWishlist(id)),HttpStatus.OK);
}
//@PostMapping("{custId}/deleteWishlist")
//    public List<CustomerProjection> deleteFromWishlist(@PathVariable("custId") int id){
//    WishList wishList= wishListRepo.
//}
@PutMapping("{custId}/addToWishList")
    public ResponseEntity<List<CustomerProjection>> updateList(@PathVariable("custId") int id, @RequestBody WishList wishList ){
    wishlistService.updateWishList(id,wishList.getProductId());
    return getWishListedProducts(id);
}

}
