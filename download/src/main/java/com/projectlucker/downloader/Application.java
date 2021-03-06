package com.projectlucker.downloader;

import com.projectlucker.downloader.configuration.DownloaderProperties;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@EnableRetry
@EnableTask
@SpringBootApplication
@EnableBatchProcessing
@Configuration
@EnableConfigurationProperties(DownloaderProperties.class)
public class Application {

    public static void main(String[] args) {
        System.out.println("Start main");
        SpringApplication.run(Application.class, args);
        System.out.println("End main");
    }

    @AfterTask
    public void afterTask(TaskExecution taskExecution) {
        taskExecution.setExitMessage("Готово!");
    }


}
