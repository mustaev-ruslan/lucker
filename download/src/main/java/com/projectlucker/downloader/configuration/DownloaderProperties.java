package com.projectlucker.downloader.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Data
@Configuration
@ConfigurationProperties
public class DownloaderProperties {

    private String source;
    private List<String> tickers;
    private LocalDate start;
    private LocalDate end;
    private List<String> timeframes;
}
