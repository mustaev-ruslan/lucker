package com.projectlucker.dataflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.dataflow.server.EnableDataFlowServer;

@EnableDataFlowServer
@SpringBootApplication
public class SpringDataFlowServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataFlowServerApplication.class);
    }

}
