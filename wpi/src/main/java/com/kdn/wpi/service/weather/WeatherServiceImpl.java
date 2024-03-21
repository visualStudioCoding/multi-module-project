package com.kdn.wpi.service.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kdn.core.domain.wpi.Weather;
import com.kdn.core.model.resbody.WeatherResBody;
import com.kdn.core.repository.WeatherRepository;
import com.kdn.wpi.util.WeatherUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService{

	@Autowired
	private WeatherRepository weatherRepository;

	public WeatherResBody getWeatherByMaxId() {
		Weather weather = weatherRepository.findTopByOrderByIdDesc().orElse(null);
		if (weather == null) {
			return null;
		}

		WeatherResBody weatherResBody = new WeatherResBody();
		weatherResBody.setWind(WeatherUtil.getWindDirection(weather.getWindDirection()));
		weatherResBody.setWeather(getWeather(weather.getPty(), weather.getSky()));
		weatherResBody.setPty(weather.getPty());
		weatherResBody.setSky(weather.getSky());
		weatherResBody.setTmp(weather.getTemperature());
		weatherResBody.setHumidity(weather.getHumidity());
		weatherResBody.setWindPower(weather.getWindSpeed());
		weatherResBody.setWheightTop(weather.getWheightTop());
		weatherResBody.setWheightBottom(weather.getWheightBottom());
		weatherResBody.setWheightMean(weather.getWheightMean());

		return weatherResBody;
	}

	private String getWeather(String pty, String sky) {
		if (!"0".equals(pty)) {
			return WeatherUtil.getWeatherStatusByPty(pty);
		} else {
			return WeatherUtil.getWeatherStatusBySky(sky);
		}
	}




}
