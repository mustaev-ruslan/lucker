package com.projectlucker.downloader.entity;

import lombok.Data;

import javax.persistence.*;
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

    @OneToOne
    @JoinColumn(name = "timeframe_id")
    private Timeframe timeframe;
}
