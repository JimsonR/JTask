package com.example.JTask.Repository;

import com.example.JTask.model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customers,Integer> {
}
