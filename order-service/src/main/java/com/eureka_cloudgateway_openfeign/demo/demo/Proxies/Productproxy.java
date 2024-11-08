package com.eureka_cloudgateway_openfeign.demo.demo.Proxies;

import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/*
* OpenFeign product proxy
* */

@FeignClient(name = "PRODUCT-SERVICE")
public interface Productproxy {

    @GetMapping("/{prodId}")
    public Product getProductByid(@PathVariable long prodId);

    @GetMapping("/Stock/{prodId}/{qnt}")
    public boolean stockCheck(@PathVariable long prodId, @PathVariable int qnt);

}
