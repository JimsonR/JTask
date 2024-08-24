package com.example.JTask.exceptions;
public class OrderNotFoundException extends RuntimeException{
   public OrderNotFoundException(){
       super("Order Not Found");
   }
}



