package com.kdn.wpi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdn.core.model.resbody.WeatherResBody;
import com.kdn.wpi.service.weather.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class APIWeatherController {

	@Autowired
	private WeatherService weatherService;

	@GetMapping
	public ResponseEntity<WeatherResBody> getRecentWeather() {
		return ResponseEntity.ok(weatherService.getWeatherByMaxId());
	}
}
