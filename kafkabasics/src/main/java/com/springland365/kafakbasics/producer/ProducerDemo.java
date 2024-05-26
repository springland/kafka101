package com.springland365.kafakbasics.producer;


import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

@Slf4j
public class ProducerDemo {

    public static void main(String[] args){
        log.info("Producer");

        // create producer properties
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers" , "localhost:19092");

        properties.setProperty("key.serializer" , StringSerializer.class.getName());
        properties.setProperty("value.serializer" , StringSerializer.class.getName());
        // create producer

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String , String> record = new ProducerRecord<>("kafkabasics" , "Hello Kakfa");

        // send data
        producer.send(record);

        // flush and close producer
        producer.close();
    }
}
