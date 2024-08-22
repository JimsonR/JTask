package com.example.JTask.Exceptions;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(){
        super("Customer Not Found");
    }
}
