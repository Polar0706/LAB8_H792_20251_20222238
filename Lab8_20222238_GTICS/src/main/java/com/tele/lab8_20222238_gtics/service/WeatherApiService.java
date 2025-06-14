package com.tele.lab8_20222238_gtics.service;

import com.tele.lab8_20222238_gtics.dto.ClimaActualResponseDto;
import com.tele.lab8_20222238_gtics.dto.PronosticoHoraResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.json.JSONObject;
import org.json.JSONArray;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherApiService {
    private static final String API_KEY = "88e12060abad41ab97212738250906";
    private static final String BASE_URL_CURRENT = "https://api.weatherapi.com/v1/current.json";
    private static final String BASE_URL_FORECAST = "https://api.weatherapi.com/v1/forecast.json";

    public ClimaActualResponseDto obtenerClimaActual(String ciudad) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL_CURRENT)
                .queryParam("key", API_KEY)
                .queryParam("q", ciudad)
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);
        JSONObject current = json.getJSONObject("current");
        JSONObject condition = current.getJSONObject("condition");

        ClimaActualResponseDto dto = new ClimaActualResponseDto();
        dto.setTempC(current.getDouble("temp_c"));
        dto.setConditionText(condition.getString("text"));
        dto.setFeelslikeC(current.getDouble("feelslike_c"));
        dto.setHumidity(current.getInt("humidity"));
        return dto;
    }

    public PronosticoHoraResponseDto obtenerPronosticoPorHora(String ciudad) {
        RestTemplate restTemplate = new RestTemplate();
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL_FORECAST)
                .queryParam("key", API_KEY)
                .queryParam("q", ciudad)
                .queryParam("days", 1)
                .toUriString();

        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);
        JSONObject location = json.getJSONObject("location");
        String cityName = location.getString("name");
        JSONArray forecastDayArr = json.getJSONObject("forecast").getJSONArray("forecastday");
        LocalDate today = LocalDate.now();
        PronosticoHoraResponseDto result = new PronosticoHoraResponseDto();
        result.setCity(cityName);
        List<PronosticoHoraResponseDto.PronosticoHoraDto> forecastList = new ArrayList<>();
        for (int i = 0; i < forecastDayArr.length(); i++) {
            JSONObject dayObj = forecastDayArr.getJSONObject(i);
            String date = dayObj.getString("date");
            if (today.toString().equals(date)) {
                JSONArray hourArr = dayObj.getJSONArray("hour");
                for (int j = 0; j < hourArr.length(); j++) {
                    JSONObject hourObj = hourArr.getJSONObject(j);
                    PronosticoHoraResponseDto.PronosticoHoraDto dto = new PronosticoHoraResponseDto.PronosticoHoraDto();
                    dto.setHour(hourObj.getString("time").substring(11, 16)); // formato HH:mm
                    dto.setTempC(hourObj.getDouble("temp_c"));
                    dto.setCondition(hourObj.getJSONObject("condition").getString("text"));
                    forecastList.add(dto);
                }
                break;
            }
        }
        result.setForecast(forecastList);
        return result;
    }
}
