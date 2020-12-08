package com.example.workflow.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static com.example.workflow.util.ApplicationConstants.SAMPLE_MESSAGE_TOPIC_NAME;

@Component
@Slf4j
@RequiredArgsConstructor
@KafkaListener(topics = SAMPLE_MESSAGE_TOPIC_NAME)
public class RecordConsumer {

    @KafkaHandler
    public void process(String record){
        log.info("*** Reading message {} ***", record);
    }
}
