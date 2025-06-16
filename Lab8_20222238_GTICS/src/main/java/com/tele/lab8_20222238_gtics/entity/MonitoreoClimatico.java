package com.tele.lab8_20222238_gtics.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Table(name = "monitoreo_climatico")
@Data
public class MonitoreoClimatico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String ciudad;

    private LocalDate fecha;

    @Column(name = "temp_promedio")
    private Double tempPromedio;

    @Column(name = "condicion_frecuente")
    private String condicionFrecuente;

    @Column(name = "temp_max")
    private Double tempMax;

    @Column(name = "temp_min")
    private Double tempMin;
}