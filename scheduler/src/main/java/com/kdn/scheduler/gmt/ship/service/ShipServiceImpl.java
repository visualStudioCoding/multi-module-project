package com.kdn.scheduler.gmt.ship.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.kdn.core.domain.gmt.Ship;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {

	private final ApplicationContext applicationContext;

	@Override
	public List<Ship> shipApiList() {

		List<Ship> ships =  new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

		try {
			Map<String, String> shipApiProperties = applicationContext.getBean("shipApiProperties",
				Map.class);

			URL url = new URL(shipApiProperties.get("shipUrl"));
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");

			int responseCode = conn.getResponseCode();

			if (responseCode != HttpURLConnection.HTTP_OK) {
				log.error("Failed to fetch data. Response code: " + responseCode);
				return Collections.emptyList();
			}

			StringBuilder response = new StringBuilder();
			try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			}

			JSONObject jObject = new JSONObject(response.toString());
			boolean result = jObject.getBoolean("result");
			if(result) {
				String message = jObject.getString("msg");
				JSONArray dataArray = jObject.getJSONArray("data");
				int dataCount = dataArray.length();

				for (int i = 0; i < dataCount; i++) {
					JSONObject dataObject = dataArray.getJSONObject(i);
					Ship ship = mapper.convertValue(dataObject.toMap(), Ship.class);
					ship.setMsg(message);
					ship.setResult(String.valueOf(true));
					ship.setWdate(LocalDateTime.now());
					ships.add(ship);
				}
			}
		} catch (IOException e) {
			log.error("IOException occurred", e);
		} catch (Exception e) {
			log.error("Unexpected error occurred", e);
		}

		return ships;
	}
}
