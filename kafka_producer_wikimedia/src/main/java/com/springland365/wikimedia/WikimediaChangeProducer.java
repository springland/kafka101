package com.springland365.wikimedia;

import com.launchdarkly.eventsource.EventSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import com.launchdarkly.eventsource.EventHandler ;

import java.net.URI;
import java.time.Duration;
import java.util.Properties;

@Slf4j
public class WikimediaChangeProducer {

    public static void main(String[] args) throws  Exception{

        log.info("Wikimeida producer");
        // create producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG , "localhost:9092");

        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG , StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , StringSerializer.class.getName());

        // This is default since Kafka 3.0
        //properties.setProperty("enable.idempotence" , "true");

        // create producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        String topic = "wikimeida.recentchange";

        EventHandler eventHandler = new WikimediaChangeHandler(producer , topic) ;

        String url = "https://stream.wikimedia.org/v2/stream/recentchange" ;

        EventSource.Builder  builder = new EventSource.Builder(eventHandler , URI.create(url));
        EventSource eventSource = builder.build();
        eventSource.start();

        // we produce for 10 minutes and block the program until then
        Thread.sleep(Duration.ofSeconds(10));
    }
}
