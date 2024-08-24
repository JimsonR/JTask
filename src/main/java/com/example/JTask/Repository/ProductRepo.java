package com.example.JTask.Repository;

import com.example.JTask.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {
    @Modifying
    @Query(value = "update product set product_price = :price where product_id=:id",nativeQuery = true )
    int updateVal(@Param("price")long price, @Param("id")int id);
}
