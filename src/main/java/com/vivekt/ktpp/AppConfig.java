package com.vivekt.ktpp;

import com.vivekt.ktpp.datamodel.Order;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class AppConfig {
public static String ORDER_TOPIC = "kt-orders.02";
    @Bean
    public KafkaProducer<String, Order> orderKafkaProducer() {
         // replace with your topic
        String bootstrapServers = "localhost:9092";

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                "org.springframework.kafka.support.serializer.JsonSerializer");
        props.put(ProducerConfig.ACKS_CONFIG, "all"); // strongest durability

        KafkaProducer<String, Order> producer = new KafkaProducer<>(props);

        return producer;
    }


}
