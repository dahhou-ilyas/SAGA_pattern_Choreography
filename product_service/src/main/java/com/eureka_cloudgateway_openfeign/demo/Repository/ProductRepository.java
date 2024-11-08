package com.eureka_cloudgateway_openfeign.demo.Repository;

import com.eureka_cloudgateway_openfeign.demo.Entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
