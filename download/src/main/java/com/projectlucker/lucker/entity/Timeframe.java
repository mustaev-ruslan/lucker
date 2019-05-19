package com.projectlucker.lucker.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Timeframe {

    @Id
    @Column
    @GeneratedValue
    private UUID id;

    @Column
    private int value;

    @Column
    private String unit;
}
