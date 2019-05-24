package com.projectlucker.downloader.batch;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SourceDownloaderFactory {

    private final List<SourceDownloader> sources;

    SourceDownloader get(String name) {
        for (SourceDownloader source : sources) {
            if (Objects.equals(source.sourceName(), name)) {
                return source;
            }
        }
        throw new IllegalArgumentException("Unknown name of source: " + name);
    }
}
