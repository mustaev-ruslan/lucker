package com.projectlucker.downloader.batch;

import com.projectlucker.downloader.entity.Chart;

public interface SourceDownloader {

    String sourceName();
    DownloadData download(Chart chart);
}
