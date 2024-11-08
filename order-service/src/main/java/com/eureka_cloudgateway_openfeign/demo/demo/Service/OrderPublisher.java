package com.eureka_cloudgateway_openfeign.demo.demo.Service;

import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Order;
import org.example.datatransferobject.Events.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Sinks;

@Component
public class OrderPublisher {

    @Autowired
    private Sinks.Many<OrderEvent> orderSinks;

    public void publishOrderEvent(Order newOrder, long prodId, int qnt){
        OrderEvent orderEvent = new OrderEvent(newOrder.getId(), prodId, qnt);
        orderSinks.tryEmitNext(orderEvent);
    }
}
