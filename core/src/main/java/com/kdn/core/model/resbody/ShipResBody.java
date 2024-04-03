package com.kdn.core.model.resbody;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ShipResBody {

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
