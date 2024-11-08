package com.eureka_cloudgateway_openfeign.demo.demo.Repository;

import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
