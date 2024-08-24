package com.example.JTask.exceptions;

public class WishListNotFoundException extends RuntimeException{
    public WishListNotFoundException(){
        super("wishlist not found");
    }
}
