package com.genspark.OrderServiceProducer.controller;

import com.genspark.MainApp.dto.Order;
import com.genspark.MainApp.dto.OrderEvent;
import com.genspark.OrderServiceProducer.kafka.OrderServiceProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OrderServiceController {

    @Autowired
    private OrderServiceProducer orderServiceProducer;

    @PostMapping("/addorder")
    public String addOrder(@RequestBody Order order){//convert json to java object
        //you can add order record to database
        OrderEvent orderEvent=new OrderEvent();
        orderEvent.setOrder(order);
        orderEvent.setStatus("placed");
        orderEvent.setMessage("order has been placed");
        orderServiceProducer.sendMessage(orderEvent);
        return "The order has been successfully placed";
    }
}
