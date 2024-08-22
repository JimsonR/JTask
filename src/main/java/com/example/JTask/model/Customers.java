package com.example.JTask.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;
@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
public class Customers {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    private String customerName;


    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private Set<Orders> customerOrders;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Set<Orders> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(Set<Orders> customerOrders) {
        this.customerOrders = customerOrders;
    }

    @Override
    public String toString() {
        return "Customers{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", customerOrders=" + customerOrders +
                '}';
    }

}
