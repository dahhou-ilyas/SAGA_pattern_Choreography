package com.eureka_cloudgateway_openfeign.demo.demo.Config;

import com.eureka_cloudgateway_openfeign.demo.demo.Service.OrderService;
import org.example.datatransferobject.Events.ProductEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class ProductEventConsumerConfig {
    @Autowired
    private OrderService orderService;

    @Bean
    public Consumer<ProductEvent> productEventConsumer(){
        return (productEvn) -> orderService.updateOrde(productEvn);
    }
}
