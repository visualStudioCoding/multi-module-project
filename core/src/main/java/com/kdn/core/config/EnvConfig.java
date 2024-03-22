package com.kdn.core.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:properties/env.properties")
public class EnvConfig {
	@Value("${weather.api.url}")
	private String weatherApiUrl;
	@Value("${weather.api.key}")
	private String weatherApiKey;

	@Value("${turbine.api.url}")
	private String turbineApiUrl;

	@Value("${gmt.ship.api.url}")
	private String shipApiUrl;

	@Bean
	public Map<String, String> weatherApiProperties() {
		Map<String, String> properties = new HashMap<>();
		properties.put("weatherUrl", weatherApiUrl);
		properties.put("weatherKey", weatherApiKey);
		return properties;
	}

	@Bean
	public Map<String, String> turbineApiProperties() {
		Map<String, String> properties = new HashMap<>();
		properties.put("turbineUrl", turbineApiUrl);
		return properties;
	}

	@Bean
	public Map<String, String> shipApiProperties() {
		Map<String, String> properties = new HashMap<>();
		properties.put("shipUrl", shipApiUrl);
		return properties;
	}
}
