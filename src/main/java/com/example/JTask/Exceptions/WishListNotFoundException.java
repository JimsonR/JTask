package com.example.JTask.Exceptions;

public class WishListNotFoundException extends RuntimeException{
    public WishListNotFoundException(){
        super("wishlist not found");
    }
}
