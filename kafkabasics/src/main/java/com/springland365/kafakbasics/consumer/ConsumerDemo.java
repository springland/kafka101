package com.springland365.kafakbasics.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

@Slf4j
public class ConsumerDemo {

    public static void main(String[] args){
        log.info("Kafka Consumer demo");
        String groupId = "mygroup";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers" , "localhost:19092");

        properties.setProperty("key.deserializer" , StringDeserializer.class.getName());
        properties.setProperty("value.deserializer" , StringDeserializer.class.getName());

        //To use the group management or offset commit APIs, you must provide a valid group.id in the consumer configuration.
        properties.setProperty("group.id" , groupId);
        properties.setProperty("auto.offset.reset" , "earliest");
        KafkaConsumer<String , String> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(List.of("kafkabasics"));

        while(true){
            log.info("poll");
            ConsumerRecords<String , String> records = consumer.poll(Duration.ofSeconds(10));
            for(ConsumerRecord<String ,String> record : records){
                log.info("Topic " + record.topic() + "\n" +
                        "Partition :" + record.partition() + " \n" +
                        "Offset :" + record.offset() + "\n" +
                        " Key: " + record.key() + "\n" +
                        " Value: " + record.value() + "\n");
            }

        }

    }
}
