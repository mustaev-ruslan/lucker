package com.projectlucker.lucker.repository;

import com.projectlucker.lucker.entity.Chart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChartRepository extends JpaRepository<Chart, UUID> {
}
