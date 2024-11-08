package com.eureka_cloudgateway_openfeign.demo.Controller;

import com.eureka_cloudgateway_openfeign.demo.Entities.Product;
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
    ArrayList<Product> productList = new ArrayList(Arrays.asList(
            new Product((long) 1,"HTC Electric Shaver", 80, 10, "An electric shaver"),
            new Product((long) 2, "Arduino Uno", 150,  20,"A microcontroller board"),
            new Product((long) 3, "Canon EOS M100", 5500,  3,"Canon camera")));


    /*
    * Get the product list from the database
    * */
    @GetMapping("/AllProducts")
    public List<Product> getProductList(){ return productList; }

    /*
    * Get information about a specific product
    * */
    @GetMapping("/{prodId}")
    public Product getProductByid(@PathVariable int prodId){
        return productList.get(prodId);
    }

    /*
    * Check if the amount of a particular product is enough to fill an order
    * */
    @GetMapping("/Stock/{prodId}/{qnt}")
    public boolean stockCheck(@PathVariable long prodId, @PathVariable int qnt){
        return  productList.get((int) (prodId-1)).stockAvailability(qnt);
    }

}
