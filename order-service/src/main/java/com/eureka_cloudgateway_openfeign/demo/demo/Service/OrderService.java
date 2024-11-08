package com.eureka_cloudgateway_openfeign.demo.demo.Service;

import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Order;
import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Product;
import com.eureka_cloudgateway_openfeign.demo.demo.Proxies.Productproxy;
import com.eureka_cloudgateway_openfeign.demo.demo.Repository.OrderRepository;
import org.example.datatransferobject.Events.OrderEvent;
import org.example.datatransferobject.Events.OrderState;
import org.example.datatransferobject.Events.ProductEvent;
import org.example.datatransferobject.Events.ProductStockState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderPublisher orderPublisher;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private Productproxy productproxy;


    public Order saveOrderInDB(long prodId, int qnt){
        boolean isProductAvailable = productproxy.stockCheck(prodId, qnt);
        Order newOrder = new Order();

        if(isProductAvailable){
            Product prod = productproxy.getProductByid(prodId);
            newOrder = new Order(
                    prod.getPrice()*qnt,
                    new Date(),
                    OrderState.PROCESSING);

            orderRepository.save(newOrder);
        }


        orderPublisher.publishOrderEvent(newOrder,prodId,qnt);
        return newOrder;
    }

    public void updateOrde(ProductEvent prdct){
        Optional<Order> newOrder = orderRepository.findById(prdct.getOrderId());
        if(newOrder.isPresent()){
            OrderState newOrderState = prdct.getStockAvailability().equals(ProductStockState.AVAILABLE) ?
                    OrderState.PROCESSING : OrderState.FAILED;

            newOrder.get().setOrederState(newOrderState);
            orderRepository.save(newOrder.get());
        }
    }
}
