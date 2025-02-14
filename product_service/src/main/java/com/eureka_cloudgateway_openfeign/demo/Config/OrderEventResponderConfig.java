package com.eureka_cloudgateway_openfeign.demo.Config;

import com.eureka_cloudgateway_openfeign.demo.Entities.Product;
import com.eureka_cloudgateway_openfeign.demo.Repository.ProductRepository;
import org.example.datatransferobject.Events.OrderEvent;
import org.example.datatransferobject.Events.ProductEvent;
import org.example.datatransferobject.Events.ProductStockState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Configuration
public class OrderEventResponderConfig {
    @Autowired
    private ProductRepository productRepository;

    @Bean
    public Function<Flux<OrderEvent>, Flux<ProductEvent>> orderEventProcessor(){
        return orderEventFlux -> orderEventFlux.flatMap(this::productStockCheck);
    }

    private Mono<ProductEvent> productStockCheck(OrderEvent orderEvent){
        Product product = productRepository.findById(orderEvent.getProdId()).get();
        ProductStockState stockAvailabilty = (product.getStock() >= orderEvent.getProdqnt())
                ? ProductStockState.AVAILABLE : ProductStockState.OUT_OF_STOCK;

        if(stockAvailabilty.equals(ProductStockState.AVAILABLE)){
            product.setStock(product.getStock() - orderEvent.getProdqnt());
            productRepository.save(product);
        }

        ProductEvent productEvent = new ProductEvent(
                orderEvent.getOrderId(),
                orderEvent.getProdId(),
                orderEvent.getProdqnt(),
                stockAvailabilty);

        return Mono.fromSupplier(()-> productEvent);
    }
}
