package com.vivekt.ktpp;

import com.vivekt.ktpp.datamodel.Execution;
import com.vivekt.ktpp.datamodel.Order;

import java.util.ArrayList;
import java.util.List;

public class DataModelMock {

    public  List<Order> generateOrders(int count) {
        List<Order> orderList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Order order = Order.builder().orderId("Order-" + i).symbol("Google" + i).Quantity(100 + i).Side("sell").build();
            orderList.add(order);
        }

        return orderList;

    }

    public List<Execution> generateExecutions(int count) {
        List<Execution> executionListList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Execution execution = Execution.builder().executionId("Exe-"+ i).orderId("Order-"+i).price(10+ i).side("sell").build();
            executionListList.add(execution);
        }

        return executionListList;

    }
}
