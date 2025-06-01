package com.mritun.bpm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;

@SpringBootApplication
@RestController
public class CamundaBasicAppApplication {
    @Autowired
    private ProcessEngine processEngine;
    @Autowired
    private RepositoryService repositoryService;

    public static void main(String[] args) {
        SpringApplication.run(CamundaBasicAppApplication.class, args);
    }

    @GetMapping("/camunda/health")
    public String camundaHealth() {
        try {
            // Try to get the process engine name as a health check
            String engineName = processEngine.getName();
            long processCount = repositoryService.createProcessDefinitionQuery().count();
            return "Camunda Engine is UP. Engine name: " + engineName + ", Process definitions: " + processCount;
        } catch (Exception e) {
            return "Camunda Engine is DOWN: " + e.getMessage();
        }
    }
}
