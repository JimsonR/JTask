package com.example.JTask.Repository;

import com.example.JTask.model.Orders;
import com.example.JTask.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders,Integer> {

    @Query(value = "SELECT * FROM orders WHERE order_id = ?1", nativeQuery = true)
    Orders findOrdersById( int id);

    @Query(value = "select product_name from orders join product on orders.product = product.product_id where order_id= :id", nativeQuery = true)
    String findProductName(@Param("id") int id);

    @Query(value = "select order_date,product_name from orders join product on orders.product = product.product_id where order_id= :id", nativeQuery = true)
    String findOrderDateProductName(@Param("id") int id);

    @Query(value = "select order_date,product_name from orders join product on orders.product = product.product_id ", nativeQuery = true)
    <T> List<T> findOrderDateProductNames(Class<T> type);

    @Query(value = "select * from orders join product on orders.product = product.product_id ", nativeQuery = true)
    <T> List<T> findAllOrders(Class<T> type);
//    @Query(value = "select order_date,product_name from orders join product on orders.product = product.product_id ", nativeQuery = true)
//    List<Object> findOrderDateProductNames();
    @Query(value = "select * from orders join product on orders.product = product.product_id join  customers on orders.customerid = customers.customer_id", nativeQuery = true)
    <T> List<T> findAllCustomerOrders(Class<T> type);

    @Query(value = "select * from orders join product on orders.product = product.product_id join  customers on orders.customerid = customers.customer_id join review on customers.customer_id = review.customer_id",nativeQuery = true)
    <T> List<T> findAllReviews(Class<T> type);




//    @Query(value= "select * from product")



}
