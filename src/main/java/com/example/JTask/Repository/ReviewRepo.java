package com.example.JTask.Repository;

import com.example.JTask.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface  ReviewRepo extends JpaRepository<Review,Integer> {
    @Modifying
    @Query(value = "update review " +
                   "set rating = :rating ,comment= :comment ,review_date= :rdate " +
                   "where customer_id= :cid and product_id= :pid", nativeQuery = true)
    int changeReview(@Param("rating") int rating, @Param("comment")String comment, @Param("rdate")LocalDate localDate, @Param("cid")int id, @Param("pid") int pid);
}
