package com.springland365.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TopicListener {

    @KafkaListener(groupId = "myId" , topics = KafkaConfig.TOPIC_NAME)
    public void listen(String in){
        log.info("Received event " + in);
    }
}
