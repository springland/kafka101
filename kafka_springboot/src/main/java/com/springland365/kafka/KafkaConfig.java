package com.springland365.kafka;

import org.apache.kafka.clients.admin.NewTopic;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String TOPIC_NAME = "springboottopic";


    //@Bean
    NewTopic topic(){
        return TopicBuilder.name("springboottopic")
                .partitions(3)
                .replicas(1)
                .build();
    }



}
