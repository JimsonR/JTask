package com.example.JTask.Repository;

import com.example.JTask.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review,Integer> {
}
