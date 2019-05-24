package com.projectlucker.downloader.sourcedownloader;

import com.projectlucker.downloader.entity.Chart;
import com.projectlucker.downloader.model.DownloadData;
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

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singletonList;

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

        FinamQueryParams params = new FinamQueryParams(chart);

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:66.0) Gecko/20100101 Firefox/66.0");
        headers.setAccept(singletonList(MediaType.APPLICATION_OCTET_STREAM));

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromHttpUrl("http://export.finam.ru")
                .path(params.getPath())
                .queryParam("market", 14)
                .queryParam("em", params.getEm())
                .queryParam("code", params.getCode())
                .queryParam("apply", 0)
                .queryParam("df1", params.getDf())
                .queryParam("mf", params.getMf())
                .queryParam("yf", params.getYf())
                .queryParam("from", params.getFrom())
                .queryParam("dt", params.getDt())
                .queryParam("mt", params.getMt())
                .queryParam("yt", params.getYt())
                .queryParam("to", params.getTo())
                .queryParam("p", params.getP())
                .queryParam("f", params.getF())
                .queryParam("e", ".csv")
                .queryParam("cn", params.getCn())
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

    private static class FinamQueryParams {

        private static DateTimeFormatter df1 = DateTimeFormatter.ofPattern("uuMMdd");
        private static DateTimeFormatter df2 = DateTimeFormatter.ofPattern("dd.MM.uuuu");
        private static Map<String, Integer> emMap = new HashMap<>();
        private static Map<String, Integer> timeframeMap = new HashMap<>();

        static {
            timeframeMap.put("1m", 2);
            timeframeMap.put("5m", 3);
            timeframeMap.put("10m", 4);
            timeframeMap.put("15m", 5);
            timeframeMap.put("30m", 6);
            timeframeMap.put("1h", 7);
            timeframeMap.put("1d", 8);
            timeframeMap.put("1w", 9);
            timeframeMap.put("1n", 10); // 1 месяц


            emMap.put("SPFB.RTS-9.19", 478555);
            emMap.put("SPFB.SBRF-6.19", 495895);
        }

        private final Chart chart;

        FinamQueryParams(Chart chart) {
            this.chart = chart;
        }

        int getP() {
            return timeframeMap.get(chart.getTimeframe());
        }

        String getPath() {
            return getF() + ".csv";
        }

        String getCode() {
            return chart.getCode();
        }

        int getDf() {
            return chart.getStartDate().getDayOfMonth();
        }

        int getMf() {
            return chart.getStartDate().getMonthValue() - 1;
        }

        int getYf() {
            return chart.getStartDate().getYear();
        }

        int getDt() {
            return chart.getEndDate().getDayOfMonth();
        }

        int getMt() {
            return chart.getEndDate().getMonthValue() - 1;
        }

        int getYt() {
            return chart.getEndDate().getYear();
        }

        String getFrom() {
            return chart.getStartDate().format(df2);
        }

        String getTo() {
            return chart.getEndDate().format(df2);
        }

        String getF() {
            return chart.getCode() + "_" + chart.getStartDate().format(df1) + chart.getStartDate().format(df1);
        }

        String getCn() {
            return chart.getCode();
        }

        int getEm() {
            return emMap.get(chart.getCode());
        }
    }
}
