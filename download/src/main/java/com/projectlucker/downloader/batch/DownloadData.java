package com.projectlucker.downloader.batch;

import com.projectlucker.downloader.entity.Chart;
import lombok.Value;

@Value
public class DownloadData {

    private final Chart chart;
    private final byte[] data;

}
