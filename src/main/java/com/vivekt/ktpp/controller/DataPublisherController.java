package com.vivekt.ktpp.controller;

import com.vivekt.ktpp.datamodel.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataPublisherController {

    @GetMapping("v")
    public ResponseEntity<Order> version(){
        Order order = Order.builder().orderId("1").symbol("Google").Quantity(100).Side("sell").build();

        return ResponseEntity.ok(order);
    }
}
