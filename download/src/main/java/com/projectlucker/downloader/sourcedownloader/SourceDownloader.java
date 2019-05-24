package com.projectlucker.downloader.sourcedownloader;

import com.projectlucker.downloader.entity.Chart;
import com.projectlucker.downloader.model.DownloadData;

public interface SourceDownloader {

    String sourceName();

    DownloadData download(Chart chart);
}
