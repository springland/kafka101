package com.springland365.wikimedia;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.MessageEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

@Slf4j
public class WikimediaChangeHandler implements EventHandler {

    protected KafkaProducer<String , String> producer;

    protected String topic ;
    public WikimediaChangeHandler(KafkaProducer<String , String>  producer , String topic){
        this.producer = producer;
        this.topic = topic ;
    }
    @Override
    public void onOpen() throws Exception {
        log.info("Stream is open");

    }

    @Override
    public void onClosed() throws Exception {

        log.info("Stream is closed");
        this.producer.close();
    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {


        this.producer.send(
                new ProducerRecord<String , String>(this.topic , messageEvent.getData())
        );
        log.info(messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {
        log.error(throwable.getMessage() , throwable);
    }
}
