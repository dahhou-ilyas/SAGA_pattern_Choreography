package com.eureka_cloudgateway_openfeign.demo.demo.Controller;

import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Order;
import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Product;
import com.eureka_cloudgateway_openfeign.demo.demo.Proxies.Productproxy;
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
    ArrayList<Order> orderList = new ArrayList(Arrays.asList(
            new Order((long) 1, 160, new Date(),Order.OrderState.CREATED),
            new Order((long) 2, 360, new Date(), Order.OrderState.CREATED),
            new Order((long) 3, 5500, new Date(), Order.OrderState.CREATED)));


    /*
    * Get the order list from the database
    * */
    @GetMapping("/AllOrders")
    public List<Order> getOrderList(){
        return orderList;
    }

    /*
    * Get information about a specific product
    * */
    @GetMapping("/{orderId}")
    public Order getOrderByid(@PathVariable int orderId){
        return orderList.get(orderId);
    }


    @GetMapping("/new/{prodId}/{qnt}")
    public Order createOrder(@PathVariable long prodId, @PathVariable int qnt){
        boolean isProductAvailable = productproxy.stockCheck(prodId, qnt);
        Order newOrder = new Order();

        if(isProductAvailable){
            Product prod = productproxy.getProductByid(prodId);
            newOrder = new Order(
                            (long) (orderList.size()+1),
                            prod.getPrice()*qnt,
                            new Date(),
                            Order.OrderState.PROCESSING);

            orderList.add(newOrder);
        }
        return newOrder;
    }

}
