package com.example.JTask.Repository;

import com.example.JTask.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishListRepo extends JpaRepository<WishList,Integer> {
   Optional<WishList> findByCustomer_CustomerId(int customerId);
}
