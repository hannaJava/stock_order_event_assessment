package com.genspark.EmailServiceConsumer.kafka;

import com.genspark.MainApp.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceConsumer {

    public static final Logger LOGGER= LoggerFactory.getLogger(EmailServiceConsumer.class);

    @KafkaListener(topics = "${}", groupId = "${}")
    public void consume(OrderEvent orderEvent){
        LOGGER.info(String.format("order event ->%s",orderEvent.toString()));
        //send email
    }
}
