package com.kdn.scheduler.wpi.weather.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.kdn.core.domain.wpi.Weather;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

	private static final int MAX_RETRY = 3; // 최대 재시도 횟수
	private static final int RETRY_DELAY_MS = 2000; // 재시도 사이의 대기 시간 (밀리초)

	private static final Random random = new Random();

	private final ApplicationContext applicationContext;


	@Override
	public Weather weatherApiData() {

		// DecimalFormat 객체 생성하여 다섯 번째 자리에서 반올림
		DecimalFormat df = new DecimalFormat("#.##");

		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);

		Weather weather = new Weather();
		Optional<JSONObject> weatherDataOptional = Optional.ofNullable(getWeatherApiData());
		weatherDataOptional.ifPresent(weatherData -> {
			// 날씨 데이터가 존재할 경우 실행되는 코드 작성
			weather.setPty(weatherData.getString("pty"));
			weather.setSky(weatherData.getString("sky"));
			weather.setTemperature(weatherData.getString("tmp"));
			weather.setWindSpeed(weatherData.getString("windSpeed"));
			weather.setHumidity(weatherData.getString("hum"));
			weather.setWindDirection(weatherData.getString("windDir"));
		});

		// 날씨 데이터가 존재하지 않는 경우에 대한 처리
		if (weatherDataOptional.isEmpty()) {
			// 랜덤한 풍향각도 생성 (0에서 360 사이의 값)
			weather.setWindDirection(String.valueOf(random.nextInt(361)));
			// 랜덤한 풍속 생성 (0에서 10 사이의 값)
			weather.setWindSpeed(String.valueOf(random.nextInt(11)));
			// 랜덤한 외부 온도 생성 (5에서 25 사이의 값)
			weather.setTemperature(String.valueOf(df.format(random.nextDouble() * 21 + 5)));
			// 랜덤한 외부 습도 생성 (40에서 80 사이의 값)
			weather.setHumidity(String.valueOf(df.format(random.nextDouble() * 41 + 40)));
			// 랜덤한 파도 높이 생성 (0에서 2 사이의 값)
		}
		weather.setDbTimeStamp(formattedTime);
		weather.setWheightTop(String.valueOf(df.format(random.nextDouble() * 3)));
		weather.setWheightMean(String.valueOf(df.format(random.nextDouble() * 3)));
		weather.setWheightBottom(String.valueOf(df.format(random.nextDouble() * 3)));

		return weather;
	}

	private JSONObject getWeatherApiData() {

		final String BASE_NX = "52";
		final String BASE_NY = "80";
		final String DATA_TYPE = "numOfRows=1000&dataType=json";

		// 현재 날짜 및 시간 구하기
		LocalDateTime nowDateTime = LocalDateTime.now();
		DateTimeFormatter hh = DateTimeFormatter.ofPattern("HH");
		DateTimeFormatter mm = DateTimeFormatter.ofPattern("mm");
		DateTimeFormatter ymd = DateTimeFormatter.ofPattern("yyyyMMdd");

		//fcstTime 계산
		String fcstTime = nowDateTime.plusHours(1).format(hh)+"00";

		//base_time 계산
		String mmStr = nowDateTime.format(mm);
		if(Integer.parseInt(mmStr)<30) {
			nowDateTime = nowDateTime.minusHours(1);
		}
		String baseTime = nowDateTime.format(hh)+"30";

		String baseDate = ymd.format(nowDateTime);

		int attempt = 0;
		while (attempt < MAX_RETRY) {
			try {
				Map<String, String> weatherApiProperties = applicationContext.getBean("weatherApiProperties",
					Map.class);
				// URL 조립
				String weatherUrl = weatherApiProperties.get("weatherUrl") + DATA_TYPE
					+ "&serviceKey=" + weatherApiProperties.get("weatherKey")
					+ "&base_date=" + baseDate
					+ "&base_time=" + baseTime
					+ "&fcstTime=" + fcstTime
					+ "&nx=" + BASE_NX
					+ "&ny=" + BASE_NY;

				log.info("[WEATHER API URL]::::::::" + weatherUrl);

				URL url = new URL(weatherUrl);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("GET");

				int responseCode = conn.getResponseCode();

				if (responseCode == HttpURLConnection.HTTP_OK) {
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
					String inputLine;
					StringBuilder response = new StringBuilder();
					while ((inputLine = in.readLine()) != null) {
						response.append(inputLine);
					}
					in.close();

					// JSON 파싱
					JSONObject jsonResponse = new JSONObject(response.toString());
					JSONObject responseObj = (JSONObject)jsonResponse.get("response");
					JSONObject bodyObj = (JSONObject)responseObj.get("body");
					JSONObject itemsObj = (JSONObject)bodyObj.get("items");
					JSONArray weatherItems = itemsObj.getJSONArray("item");

					if(!ObjectUtils.isEmpty(weatherItems)) {
						return getJsonParseObject(weatherItems, fcstTime);
					} else {
						//연계 실패시 (resultCode가 00이 아닌경우) base_time-1시간으로 다시 연계
						fcstTime = nowDateTime.minusHours(1).format(hh)+"30";
						attempt++;
						Thread.sleep(RETRY_DELAY_MS);
					}
				} else {
					log.info("Failed to fetch data. Response code: " + responseCode);
					attempt++;
					Thread.sleep(RETRY_DELAY_MS);
				}

			} catch (JSONException e) {
				log.error("JSONException ", e);
				attempt++;
				try {
					Thread.sleep(RETRY_DELAY_MS);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt(); // 현재 스레드의 인터럽트 상태를 설정
					log.error("Thread interrupted during retry delay", ie);
				}
			} catch (IOException e) {
				log.error("IOException occurred", e);
				attempt++;
				try {
					Thread.sleep(RETRY_DELAY_MS);
				} catch (InterruptedException ie) {
					Thread.currentThread().interrupt(); // 현재 스레드의 인터럽트 상태를 설정
					log.error("Thread interrupted during retry delay", ie);
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); // 현재 스레드의 인터럽트 상태를 설정
				log.error("Thread interrupted", e);
				break; // 인터럽트 발생 시 반복 중단
			}
		}
		return null;
	}

	private JSONObject getJsonParseObject(JSONArray weatherItems, String fcstTime) {
		JSONObject returnItem = new JSONObject();
		// 예측 데이터 처리
		weatherItems.forEach(weatherObj -> {
			JSONObject weather = (JSONObject)weatherObj;

			if (!weather.get("fcstTime").toString().equals(fcstTime)) {
				return;
			}

			String category = weather.get("category").toString();
			String fcstValue = weather.get("fcstValue").toString();

			// 카테고리와 대응하는 키 매핑
			Map<String, String> categoryToKey = new HashMap<>();
			categoryToKey.put("PTY", "pty");
			categoryToKey.put("SKY", "sky");
			categoryToKey.put("T1H", "tmp");
			categoryToKey.put("VEC", "windDir");
			categoryToKey.put("WSD", "windSpeed");
			categoryToKey.put("REH", "hum");

			// 주어진 카테고리에 따라 값을 매핑
			String key = categoryToKey.getOrDefault(category, null);
			if (key != null) {
				returnItem.put(key, fcstValue);
			}

			/*switch (category) {
				case "PTY": // 강수형태 없음(0), 비(1), 비/눈(2), 눈(3), 소나기(4)
					returnItem.put("pty", fcstValue);
					break;
				case "SKY": //맑음(1), 구름많음(3), 흐림(4)
					returnItem.put("sky", fcstValue);
					break;
				case "T1H": // 기온
					returnItem.put("tmp", fcstValue);
					break;
				case "VEC": // 풍향
					returnItem.put("windDir", fcstValue);
					break;
				case "WSD": // 풍속
					returnItem.put("windSpeed", fcstValue);
					break;
				case "REH": // 습도
					returnItem.put("hum", fcstValue);
					break;
				default:
					break;
			}*/
		});
		return returnItem;
	}
}
