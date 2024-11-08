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
        //cettte méthode permet de ecrire dans un pipline de order (cette pipline pas de kakfa mias just de mon application utilisé a l'aide de webFlux)
        //par la suite dans un autre class on listen a cette pipline pour recevoire les order et envoyé par la suite dans kafka
        orderSinks.tryEmitNext(orderEvent);
    }
}
