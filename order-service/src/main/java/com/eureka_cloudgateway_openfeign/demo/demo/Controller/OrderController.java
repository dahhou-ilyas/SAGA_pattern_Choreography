package com.eureka_cloudgateway_openfeign.demo.demo.Controller;

import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Order;
import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Product;
import com.eureka_cloudgateway_openfeign.demo.demo.Proxies.Productproxy;
import com.eureka_cloudgateway_openfeign.demo.demo.Repository.OrderRepository;
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
    private Productproxy productproxy;

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
        List<Order> listOrders = orderRepository.findAll();
        boolean isProductAvailable = productproxy.stockCheck(prodId, qnt);
        Order newOrder = new Order();

        if(isProductAvailable){
            Product prod = productproxy.getProductByid(prodId);
            newOrder = new Order(
                            (long) (listOrders.size()+1),
                            prod.getPrice()*qnt,
                            new Date(),
                            Order.OrderState.PROCESSING);

            orderRepository.save(newOrder);
        }
        return newOrder;
    }

}
