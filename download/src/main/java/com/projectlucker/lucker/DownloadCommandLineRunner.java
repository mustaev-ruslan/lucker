package com.projectlucker.lucker;

import com.projectlucker.lucker.entity.Chart;
import com.projectlucker.lucker.entity.Timeframe;
import com.projectlucker.lucker.repository.ChartRepository;
import com.projectlucker.lucker.repository.TimeframeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
class DownloadCommandLineRunner implements CommandLineRunner {

    private final ChartRepository chartRepository;

    private final TimeframeRepository timeframeRepository;


    @Override
    public void run(String... args) throws Exception {
        System.out.println("Start init");

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
