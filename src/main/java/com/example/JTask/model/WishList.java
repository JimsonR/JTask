package com.example.JTask.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id","wishlist_id"}))
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistId;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customers customer;

    @ElementCollection
    @CollectionTable(name = "wishlist_products", joinColumns = @JoinColumn(name = "wishlist_id"))
    @Column(name = "product_id")
//    @ManyToMany
//    @JoinTable(name = "wishlist_product",
//    joinColumns = @JoinColumn(name="wishlist_id"),
//    inverseJoinColumns = @JoinColumn(name = "prod"))
    private List<Integer> productId;

    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

    public int getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }

    public List<Integer> getProductId() {
        return productId;
    }

    public void setProductId(List<Integer> productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "WishList{" +
                "wishlistId=" + wishlistId +
                ", customer=" + customer +
                ", productId=" + productId +
                '}';
    }


}
