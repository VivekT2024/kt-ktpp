package com.vivekt.ktpp.kafka;


import com.vivekt.ktpp.AppConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class OrderConsumerClient {

    public static void main(String[] args) {
        String topicName = AppConfig.ORDER_TOPIC;   // same topic as producer
        String bootstrapServers = "localhost:9092";
        String groupId = "my-consumer-group";

        // Consumer configuration
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(org.springframework.kafka.support.serializer.JsonDeserializer.TRUSTED_PACKAGES, "com.vivekt.ktpp.datamodel");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                "org.springframework.kafka.support.serializer.JsonDeserializer");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // "earliest" = consume from beginning if no offset found

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList(topicName));

        System.out.println("Consumer started. Waiting for messages...");

        try {
            while (true) {
                // poll for new records
                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Consumed record(key=%s value=%s) " +
                                    "meta(partition=%d, offset=%d)%n",
                            record.key(), record.value(),
                            record.partition(), record.offset());
                }
            }
        } finally {
            consumer.close();
        }
    }
}
