package com.kdn.scheduler.wpi.weather;

import com.kdn.core.domain.wpi.Weather;
import com.kdn.core.repository.WeatherRepository;
import com.kdn.scheduler.wpi.weather.service.WeatherService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherScheduler {

	private final WeatherRepository weatherRepository;

	@Autowired
	private WeatherService weatherService;

	// @Scheduled(cron = "10 * * * * *")
	@Scheduled(fixedRate = 25000) // 25초마다 실행
	public void fetchWeatherData() {
		log.info("[weather scheduler] START");
		Weather weather = weatherService.weatherApiData();

		weatherRepository.save(weather);
	}
}

