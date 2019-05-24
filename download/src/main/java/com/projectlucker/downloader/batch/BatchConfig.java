package com.projectlucker.downloader.batch;

import com.projectlucker.downloader.model.DownloadData;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Log
@RequiredArgsConstructor
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobs;
    private final StepBuilderFactory steps;
    private final ItemReader<DownloadData> downloadChartReader;
    private final ItemWriter<Object> csvChartWriter;

    @Bean
    public Job job(Step step1) {
        return jobs.get("Download").start(step1).build();
    }

    @Bean
    public Step step1() {
        return steps.get("step1")
                .chunk(10)
                .reader(downloadChartReader)
                .writer(csvChartWriter)
                .listener(new ItemReadListener<DownloadData>() {
                    public void beforeRead() { log.info("Начало чтения"); }
                    public void afterRead(DownloadData o) { log.info("Конец чтения"); }
                    public void onReadError(Exception e) { log.info("Ошибка чтения"); }
                })
                .build();
    }
}
