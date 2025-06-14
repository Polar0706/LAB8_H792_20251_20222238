package com.tele.lab8_20222238_gtics.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class MonitoreoClimaticoRequestDto {
    private String ciudad;
    private LocalDate fecha;
    private Double tempPromedio;
    private String condicionFrecuente;
    private Double tempMax;
    private Double tempMin;
}
