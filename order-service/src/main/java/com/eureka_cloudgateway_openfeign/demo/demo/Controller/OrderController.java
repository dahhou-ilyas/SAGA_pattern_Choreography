package com.eureka_cloudgateway_openfeign.demo.demo.Controller;

import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Order;
import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Product;
import com.eureka_cloudgateway_openfeign.demo.demo.Proxies.Productproxy;
import com.eureka_cloudgateway_openfeign.demo.demo.Repository.OrderRepository;
import com.eureka_cloudgateway_openfeign.demo.demo.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    /*
    * Product proxy injection
    * */
    @Autowired
    private OrderService orderService;

    /*
     * A static list of orders
     * */
    @Autowired
    private OrderRepository orderRepository;


    /*
    * Get the order list from the database
    * */
    @GetMapping("/AllOrders")
    public List<Order> getOrderList(){
        return orderRepository.findAll();
    }

    /*
    * Get information about a specific product
    * */
    @GetMapping("/{orderId}")
    public Order getOrderByid(@PathVariable long orderId){
        return orderRepository.findById(orderId).orElseThrow(()->new RuntimeException("order not found"));
    }


    @GetMapping("/new/{prodId}/{qnt}")
    public Order createOrder(@PathVariable long prodId, @PathVariable int qnt){
        return orderService.saveOrderInDB(prodId,qnt);
    }

}
