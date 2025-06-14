package com.tele.lab8_20222238_gtics.controller;

import com.tele.lab8_20222238_gtics.dto.ClimaActualResponseDto;
import com.tele.lab8_20222238_gtics.dto.PronosticoHoraResponseDto;
import com.tele.lab8_20222238_gtics.dto.MonitoreoClimaticoRequestDto;
import com.tele.lab8_20222238_gtics.entity.MonitoreoClimatico;
import com.tele.lab8_20222238_gtics.repository.MonitoreoClimaticoRepository;
import com.tele.lab8_20222238_gtics.service.WeatherApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClimaController {

    @Autowired
    private WeatherApiService weatherApiService;
    @Autowired
    private MonitoreoClimaticoRepository monitoreoClimaticoRepository;

    @GetMapping("/api/clima/actual")
    public ClimaActualResponseDto obtenerClimaActual(@RequestParam String ciudad) {
        return weatherApiService.obtenerClimaActual(ciudad);
    }

    @GetMapping("/api/clima/pronostico-hora")
    public PronosticoHoraResponseDto obtenerPronosticoPorHora(@RequestParam String ciudad) {
        return weatherApiService.obtenerPronosticoPorHora(ciudad);
    }

    @PostMapping("/api/clima/monitoreo")
    public MonitoreoClimatico registrarMonitoreo(@RequestBody MonitoreoClimaticoRequestDto dto) {
        MonitoreoClimatico monitoreo = new MonitoreoClimatico();
        monitoreo.setCiudad(dto.getCiudad());
        monitoreo.setFecha(dto.getFecha());
        monitoreo.setTempPromedio(dto.getTempPromedio());
        monitoreo.setCondicionFrecuente(dto.getCondicionFrecuente());
        monitoreo.setTempMax(dto.getTempMax());
        monitoreo.setTempMin(dto.getTempMin());
        return monitoreoClimaticoRepository.save(monitoreo);
    }
}
