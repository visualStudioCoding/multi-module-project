package com.kdn.wpi.util;

public class WeatherUtil {
	public static String getWeatherStatusByPty(String pty) {
		if (pty == null) {
			return "Unknown";
		}
		return switch (pty) {
			case "1", "2", "4", "5", "6" -> "비";
			case "3", "7" -> "눈";
			default -> "";
		};
	}

	public static String getWeatherStatusBySky(String sky) {
		if (sky == null) {
			return "Unknown";
		}
		return switch (sky) {
			case "1" -> "맑음";
			case "3", "4" -> "흐림";
			default -> "";
		};
	}

	public static String getWindDirection(String angle) {
		/*풍향로직 START*/
		// 풍향을 360도로 표기하고, 22.5도를 더하여 0도부터 45도까지를 북, 45도부터 90도까지를 북동으로 처리
		String[] directions = {"북", "북동", "동", "남동", "남", "남서", "서", "북서", "북"};
		int wd = (int)((Double.parseDouble(angle) + 22.5) / 45) % 8;
		return directions[wd];
	}
}
