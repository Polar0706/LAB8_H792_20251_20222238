package com.tele.lab8_20222238_gtics.repository;

import com.tele.lab8_20222238_gtics.entity.MonitoreoClimatico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitoreoClimaticoRepository extends JpaRepository<MonitoreoClimatico, Integer> {
}