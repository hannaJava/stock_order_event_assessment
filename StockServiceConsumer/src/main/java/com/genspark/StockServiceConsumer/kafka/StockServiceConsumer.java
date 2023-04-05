package com.genspark.StockServiceConsumer.kafka;

import com.genspark.MainApp.dto.Order;
import com.genspark.MainApp.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class StockServiceConsumer {
    public static final Logger LOGGER= LoggerFactory.getLogger(StockServiceConsumer.class);

    @Value("${spring.kafka.topic.name}")
    private  String topic;
    @Value("${spring.kafka.consumer.group_id}")
    private  String groupId;
    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId ="${spring.kafka.consumer.group_id}" )//listen/subscribe to the order topic
    public void consume(OrderEvent orderEvent){
        LOGGER.info(String.format("order event -> %s",orderEvent.toString()));
        //save orderEvent obj to database
        //save order obj to database
        Order order=orderEvent.getOrder();
    }
}
