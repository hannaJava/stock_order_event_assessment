package com.genspark.OrderServiceProducer.kafka;

import com.genspark.MainApp.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceProducer {
    public static final Logger LOGGER= LoggerFactory.getLogger(OrderServiceProducer.class);
    private NewTopic topic;
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    //constructor dependency injection
    public OrderServiceProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEvent orderEvent){
        LOGGER.info(String.format("order event -> %s",orderEvent));
        //create a message for OrderEvent object associated to topic name
        Message<OrderEvent> message= MessageBuilder.withPayload(orderEvent)
                                                    .setHeader(KafkaHeaders.TOPIC,topic.name())
                                                    .build();
        //send message to topic using KafkaTemplate send message
        kafkaTemplate.send(message);
    }
}
