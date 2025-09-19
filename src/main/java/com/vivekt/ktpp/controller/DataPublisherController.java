package com.vivekt.ktpp.controller;

import com.vivekt.ktpp.DataModelMock;
import com.vivekt.ktpp.datamodel.Execution;
import com.vivekt.ktpp.datamodel.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataPublisherController {
    DataModelMock dataModelService = new DataModelMock();

    @GetMapping("v")
    public ResponseEntity<Order> version() {
        Order order = Order.builder().orderId("1").symbol("Google").Quantity(100).Side("sell").build();

        return ResponseEntity.ok(order);
    }

    @PostMapping("gen/orders")
    public ResponseEntity<List<Order>> publishOrders(@RequestParam Integer count) {
        List<Order> generatedOrders = dataModelService.generateOrders(count);
        return ResponseEntity.ok(generatedOrders);
    }

    @PostMapping("gen/executions")
    public ResponseEntity<List<Execution>> publishExecutions(@RequestParam Integer count) {
        List<Execution> generatedOrders = dataModelService.generateExecutions(count);
        return ResponseEntity.ok(generatedOrders);
    }
}
