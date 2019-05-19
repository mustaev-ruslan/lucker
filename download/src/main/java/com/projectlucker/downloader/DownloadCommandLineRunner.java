package com.projectlucker.downloader;

import com.projectlucker.downloader.entity.Chart;
import com.projectlucker.downloader.entity.Timeframe;
import com.projectlucker.downloader.repository.ChartRepository;
import com.projectlucker.downloader.repository.TimeframeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
class DownloadCommandLineRunner implements CommandLineRunner {

    private final ChartRepository chartRepository;

    private final TimeframeRepository timeframeRepository;

    private final DownloaderProperties downloaderProperties;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start init");
        System.out.println(downloaderProperties.getTickers());

        Timeframe timeframe = new Timeframe();
        timeframe.setUnit("m");
        timeframe.setValue(1);
        timeframeRepository.save(timeframe);

        Chart chart = new Chart();
        chart.setCode("RUI-9");
        chart.setStartDate(LocalDate.now());
        chart.setEndDate(LocalDate.now().plusMonths(1));
        chart.setSource("finam");
        chart.setTimeframe(timeframe);
        chartRepository.save(chart);

        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("End init");

    }
}
