package com.projectlucker.downloader.sourcedownloader;

public class SourceDownloaderException extends Exception {

    public SourceDownloaderException() {
    }

    public SourceDownloaderException(String message) {
        super(message);
    }

    public SourceDownloaderException(String message, Throwable cause) {
        super(message, cause);
    }

    public SourceDownloaderException(Throwable cause) {
        super(cause);
    }
}
