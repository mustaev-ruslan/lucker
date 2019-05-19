package com.projectlucker.downloader.repository;

import com.projectlucker.downloader.entity.Timeframe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TimeframeRepository extends JpaRepository<Timeframe, UUID> {
}
