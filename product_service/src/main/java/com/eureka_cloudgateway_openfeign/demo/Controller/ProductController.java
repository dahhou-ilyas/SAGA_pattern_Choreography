package com.eureka_cloudgateway_openfeign.demo.Controller;

import com.eureka_cloudgateway_openfeign.demo.Entities.Product;
import com.eureka_cloudgateway_openfeign.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class ProductController {

    /*
    * A static list of products
    * */
    @Autowired
    private ProductRepository productRepository;


    /*
    * Get the product list from the database
    * */
    @GetMapping("/AllProducts")
    public List<Product> getProductList(){
        return productRepository.findAll();
    }

    /*
    * Get information about a specific product
    * */
    @GetMapping("/{prodId}")
    public Product getProductByid(@PathVariable long prodId){
        return productRepository.findById(prodId).orElseThrow(()->new RuntimeException("Product not found"));
    }

    /*
    * Check if the amount of a particular product is enough to fill an order
    * */
    @GetMapping("/Stock/{prodId}/{qnt}")
    public boolean stockCheck(@PathVariable long prodId, @PathVariable int qnt){
        Product prd = productRepository.findById(prodId).orElseThrow(() -> new RuntimeException("Product not found"));

        return prd.stockAvailability(qnt);
    }

}
