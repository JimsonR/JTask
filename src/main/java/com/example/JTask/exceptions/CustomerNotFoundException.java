package com.example.JTask.exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(){
        super("Customer Not Found");
    }
}
