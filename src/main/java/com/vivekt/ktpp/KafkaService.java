package com.vivekt.ktpp;

import com.vivekt.ktpp.datamodel.Order;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KafkaService {

    KafkaProducer<String, Order> kafkaProducer;
    KafkaConsumer<String, Order> kafkaConsumer;

    public KafkaService(KafkaProducer<String, Order> orderKafkaProducer) {
        this.kafkaProducer = orderKafkaProducer;
    }

    public void publishOrderList(List<Order> orderList) {
        for (Order order : orderList) {
            ProducerRecord<String, Order> producerRecord = new ProducerRecord<>(AppConfig.ORDER_TOPIC, order.getOrderId(), order);
            kafkaProducer.send(producerRecord);
        }

    }


}
