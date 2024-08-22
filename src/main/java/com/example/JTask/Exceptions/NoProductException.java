package com.example.JTask.Exceptions;

public class NoProductException extends RuntimeException{
    public NoProductException(){
        super("No Product Found");
    }
}
