package com.tele.lab8_20222238_gtics.dto;

import lombok.Data;
import java.util.List;

@Data
public class PronosticoHoraResponseDto {
    private String city;
    private List<PronosticoHoraDto> forecast;

    @Data
    public static class PronosticoHoraDto {
        private String hour;
        private double tempC;
        private String condition;
    }
}
