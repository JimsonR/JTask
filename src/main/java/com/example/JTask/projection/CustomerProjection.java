package com.example.JTask.projection;

import com.example.JTask.model.Customers;
import com.example.JTask.model.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerProjection {

    private Integer orderId;

    private LocalDate orderDate;

    private String orderStatus;

    private Integer productId;

    private String productName;

    private String productType;

//    private byte[] productImg;
    private Integer customerId;

    private String customerName;

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "StudentProjection{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                '}';
    }
}
