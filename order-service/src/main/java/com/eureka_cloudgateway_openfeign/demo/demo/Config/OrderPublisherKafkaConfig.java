package com.eureka_cloudgateway_openfeign.demo.demo.Config;

import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Order;
import com.eureka_cloudgateway_openfeign.demo.demo.Entities.Product;
import com.eureka_cloudgateway_openfeign.demo.demo.Proxies.Productproxy;
import com.eureka_cloudgateway_openfeign.demo.demo.Repository.OrderRepository;
import org.example.datatransferobject.Events.OrderEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


public class OrderPublisherKafkaConfig {

}
