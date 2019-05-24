package com.projectlucker.downloader.batch;

import com.projectlucker.downloader.entity.Chart;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.*;

@Component
@RequiredArgsConstructor
public class FinamSourceDownloader implements SourceDownloader {

    private final RestTemplate restTemplate;

    @Override
    public String sourceName() {
        return "finam";
    }

    @Retryable(value = RestClientException.class, backoff = @Backoff(delay = 5000))
    @Override
    public DownloadData download(Chart chart) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0");
        headers.setAccept(singletonList(MediaType.APPLICATION_OCTET_STREAM));

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl("http://export.finam.ru")
                .path("SPFB.SBRF-6.19_190416_190417.csv")
                .queryParam("market", 14)
                .queryParam("em", 495895)
                .queryParam("code", "SPFB.SBRF - 6.19")
                .queryParam("apply", 0)
                .queryParam("df", 16)
                .queryParam("mf", 3)
                .queryParam("yf", 2019)
                .queryParam("from", "16.04.2019")
                .queryParam("dt", 17)
                .queryParam("mt", 3)
                .queryParam("yt", 2019)
                .queryParam("to", "17.04.2019")
                .queryParam("p", 2)
                .queryParam("f", "SPFB.SBRF - 6.19_190416_190417")
                .queryParam("e", ".csv")
                .queryParam("cn", "SPFB.SBRF - 6.19")
                .queryParam("dtf", 1)
                .queryParam("tmf", 1)
                .queryParam("MSOR", 0)
                .queryParam("mstime", "on")
                .queryParam("mstimever", 1)
                .queryParam("sep", 3)
                .queryParam("sep2", 1)
                .queryParam("datf", 1)
                .queryParam("at", 1);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<byte[]> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                entity,
                byte[].class);
        System.out.println(entity);
        return new DownloadData(chart, response.getBody());
    }
}
