package com.tele.lab8_20222238_gtics.dto;

import lombok.Data;

@Data
public class ClimaActualResponseDto {
    private double tempC;
    private String conditionText;
    private double feelslikeC;
    private int humidity;
}
