package com.projectlucker.lucker.repository;

import com.projectlucker.lucker.entity.Timeframe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TimeframeRepository extends JpaRepository<Timeframe, UUID> {
}
