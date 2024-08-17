package com.example.JTask.Repository;

import com.example.JTask.model.Orders;
import com.example.JTask.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders,Integer> {

    @Query(value = "SELECT * FROM orders WHERE order_id = ?", nativeQuery = true)
    Orders findOrdersById( int id);

//    @Query(value= "select * from product")



}
