package com.projectlucker.downloader.repository;

import com.projectlucker.downloader.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChartRepository extends JpaRepository<Chart, UUID> {
}
