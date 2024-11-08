package com.eureka_cloudgateway_openfeign.demo.demo.Initiator;

import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Order;
import com.eureka_cloudgateway_openfeign.demo.demo.Repository.OrderRepository;
import org.example.datatransferobject.Events.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DataInitiatore implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(event.getApplicationContext().getParent()==null){
            orderRepository.save(new Order(160, new Date(), OrderState.CREATED));
            orderRepository.save(new Order(360, new Date(), OrderState.CREATED));
            orderRepository.save(new Order(5500, new Date(), OrderState.CREATED));
        }
    }
}
