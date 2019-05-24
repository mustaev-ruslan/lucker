package com.projectlucker.downloader.batch;

import com.projectlucker.downloader.entity.Chart;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.google.common.io.Files;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class CsvChartWriter implements ItemWriter {

    private static DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.uuuu");

    @Override
    public void write(List items) throws Exception {
        List<DownloadData> datas = (List<DownloadData>) items;
        for (DownloadData data : datas) {
            Files.write(data.getData(), new File(generateFilePath(data.getChart())));
        }
    }

    private String generateFilePath(Chart chart) {
        return "C:\\Users\\Руслан\\IdeaProjects\\projectlucker\\lucker\\csv\\" +
                chart.getSource() + "_" +
                chart.getCode() + "_" +
                chart.getTimeframe() + "_" +
                chart.getStartDate().format(df) + "_" +
                chart.getEndDate().format(df) +
                ".csv";
    }
}
