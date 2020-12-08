package com.example.workflow.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class ShouldErrorDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Boolean shouldFail = (Boolean) execution.getVariable("shouldFail");

        if(shouldFail != null && shouldFail) throw new RuntimeException("Failure!");
    }
}
