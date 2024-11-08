package com.eureka_cloudgateway_openfeign.demo.demo.Service;

import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Order;
import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Product;
import com.eureka_cloudgateway_openfeign.demo.demo.Proxies.Productproxy;
import com.eureka_cloudgateway_openfeign.demo.demo.Repository.OrderRepository;
import org.example.datatransferobject.Events.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class OrderService {

    @Autowired
    private OrderPublisher orderPublisher;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Productproxy productproxy;


    public Order saveOrderInDB(long prodId, int qnt){
        List<Order> listOrders = orderRepository.findAll();
        boolean isProductAvailable = productproxy.stockCheck(prodId, qnt);
        Order newOrder = new Order();

        if(isProductAvailable){
            Product prod = productproxy.getProductByid(prodId);
            newOrder = new Order(
                    prod.getPrice()*qnt,
                    new Date(),
                    Order.OrderState.PROCESSING);

            orderRepository.save(newOrder);
        }


        orderPublisher.publishOrderEvent(newOrder,prodId,qnt);
        return newOrder;
    }
}
