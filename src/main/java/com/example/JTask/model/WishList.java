package com.example.JTask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor

public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "wishlistId=" + wishlistId +
                ", customer=" + customer +
                ", product=" + product +
                '}';
    }
}
