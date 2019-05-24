package com.projectlucker.downloader.batch;

import com.projectlucker.downloader.DownloaderProperties;
import com.projectlucker.downloader.entity.Chart;
import org.springframework.batch.item.ItemReader;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
class DownloadChartReader implements ItemReader<DownloadData> {

    private final Iterator<Chart> chartIterator;
    private final SourceDownloader source;

    DownloadChartReader(DownloaderProperties downloaderProperties,
                        SourceDownloaderFactory sourceDownloaderFactory) {
        this.source = sourceDownloaderFactory.get(downloaderProperties.getSource());
        List<Chart> charts = generateCartesianProduct(downloaderProperties);
        chartIterator = charts.iterator();
    }

    @Override
    public DownloadData read() throws Exception {
        return chartIterator.hasNext() ? source.download(chartIterator.next()) : null;
    }

    private List<Chart> generateCartesianProduct(DownloaderProperties props) {
        List<Chart> charts = new ArrayList<>();
        for (String ticker : props.getTickers()) {
            for (String timeframe : props.getTimeframes()) {
                charts.add(new Chart(
                        props.getSource(),
                        ticker,
                        props.getStart(),
                        props.getEnd(),
                        timeframe
                ));
            }
        }
        return charts;
    }

}