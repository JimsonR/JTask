package com.example.JTask.Repository;

import com.example.JTask.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishListRepo extends JpaRepository<WishList,Integer> {
}
