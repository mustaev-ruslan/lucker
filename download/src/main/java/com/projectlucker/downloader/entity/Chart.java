package com.projectlucker.downloader.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
public class Chart {

    @Id
    @Column
    @GeneratedValue
    private UUID id;
    @Column
    private String source;
    @Column
    private String code;
    @Column
    private LocalDate startDate;
    @Column
    private LocalDate endDate;
    @Column
    private String timeframe;

    public Chart(String source, String code, LocalDate startDate, LocalDate endDate, String timeframe) {
        this.source = source;
        this.code = code;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeframe = timeframe;
    }
}
