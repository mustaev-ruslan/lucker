package com.projectlucker.lucker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.configuration.TaskProperties;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableTask
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        System.out.println("Start main");
        SpringApplication.run(Application.class, args);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End main");
    }

    @AfterTask
    public void afterMe(TaskExecution taskExecution) {
        System.out.println("After task");
        taskExecution.setExitMessage("After task DOWNLOADEEERRR");
    }


}
