package com.example.JTask.service;

import com.example.JTask.Repository.CustomerRepo;
import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.Repository.ProductRepo;
import com.example.JTask.model.Customers;
import com.example.JTask.projection.CustomerProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    ProductRepo productRepo;

    public Customers addCustomer(int id,String name){
        Customers customer = new Customers();
        customer.setCustomerId(id);
        customer.setCustomerName(name);
        return customerRepo.save(customer);
    }

    public Customers addCustomerOrder(Customers customer){


    return  customerRepo.save(customer);
    }





}
