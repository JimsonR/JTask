package com.example.JTask.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super("No Product Found");
    }
}
