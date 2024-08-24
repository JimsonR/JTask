package com.example.JTask.projection;

import java.time.LocalDate;

public interface OrderProductProjection {
    Integer   getCustomerId();
    String    getCustomerName();
    Integer   getProductId();
    String    getProductName();
    String    getProductType();
    Integer   getOrderId();
    LocalDate getOrderDate();
    String    getOrderStatus();
    Integer   getWishlistId();
    LocalDate getReviewDate();
    String    getComment();
    Integer   getRating();
    Long      getProductPrice();

}
