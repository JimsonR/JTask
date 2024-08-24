package com.example.JTask.Repository;

import com.example.JTask.model.Customers;
import com.example.JTask.model.WishList;
import com.example.JTask.projection.OrderProductProjection;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customers,Integer> {

    @Query(value = "select product_name from product as prods join wishlist_products as wishp on prods.product_id = wishp.product_id " +
            "join wish_list as wish on wish.wishlist_id = wishp.wishlist_id where  customer_id = :id", nativeQuery = true)
    List<OrderProductProjection> getCustomerWishlist(@Param("id") int id);

    @Query(value = "select customerid, sum(product_price) as product_price from orders join product on orders.product = product.product_id group by customerid;" ,nativeQuery = true)
    <T>List<OrderProductProjection> getTotalAmount(Class<T> Class);

    @Query(value ="select sum(product_price) from orders join product on orders.product = product.product_id group by customerid having customerid=:cid" ,nativeQuery = true)
    Long totalAmount(@Param("cid") int id);

//    @Query(value = "select ")
//    <T>List<OrderProductProjection> getCustAllOrders(Class<T> Class);
}

