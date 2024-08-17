package com.example.JTask.Repository;

import com.example.JTask.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer> {


}
