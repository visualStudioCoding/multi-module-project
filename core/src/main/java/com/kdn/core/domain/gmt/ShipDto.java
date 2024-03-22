package com.kdn.core.domain.gmt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ShipDto {

	private String shipId;
	private String recptnDt;
	private String sensor;
	private String lon;
	private String lat;
	private String sog;
	private String cog;
	private String hdg;
	private String danger;
	private String shipType;
	private String timeSect;
}
