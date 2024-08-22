package com.example.JTask;

import com.example.JTask.Repository.OrderRepo;
import com.example.JTask.model.Orders;
import com.example.JTask.model.Product;
import com.example.JTask.service.CustomerService;
import com.example.JTask.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;

@SpringBootApplication
public class JTaskApplication {


	public static void main(String[] args) {

			ConfigurableApplicationContext context = SpringApplication.run(JTaskApplication.class, args);


//		OrderService service = context.getBean(OrderService.class);
//		CustomerService customerService = context.getBean(CustomerService.class);

//		customerService.addCustomer(1,"jim");
//		customerService.addCustomer(2,"jyo");
//		customerService.addCustomer(3,"sub");

}

}
