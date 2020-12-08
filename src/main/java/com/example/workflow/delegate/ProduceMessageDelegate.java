package com.example.workflow.delegate;

import com.example.workflow.model.Record;
import com.example.workflow.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.example.workflow.util.ApplicationConstants.SAMPLE_MESSAGE_TOPIC_NAME;

@Component
@RequiredArgsConstructor
public class ProduceMessageDelegate implements JavaDelegate {

    private final RecordRepository recordRepository;
    private final KafkaTemplate<String, String> recordKafkaTemplate;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        recordRepository.save(new Record(execution.getBusinessKey()));
        recordKafkaTemplate.send(SAMPLE_MESSAGE_TOPIC_NAME, execution.getBusinessKey());
    }
}
