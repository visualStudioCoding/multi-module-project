package com.kdn.core.model.resbody;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherResBody {

	private String sky;
	private String pty;
	private String weather;
	private String windPower; // windSpeed
	private String wind;
	private String tmp; // temperature

	private String wheightTop;
	private String wheightMean;
	private String wheightBottom;
	private String humidity;
}
