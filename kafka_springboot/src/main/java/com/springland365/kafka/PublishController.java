package com.springland365.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("pub")
public class PublishController {

    KafkaTemplate<String, String> template ;

    public PublishController(KafkaTemplate<String, String> template){
        this.template = template ;
    }
    @PostMapping("/{value}")
    public String publish(@PathVariable("value") String value){

        log.info("Publish " + value );
        template.send(KafkaConfig.TOPIC_NAME , value);
        return "Publish " + value + " successfully";
    }
}
