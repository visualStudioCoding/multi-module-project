package com.kdn.core.domain.gmt;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
@IdClass(ShipId.class)
public class Ship {

	@Id
	private String shipId;

	@Id
	private String wdate;

	private String recptnDt;
	private String sensor;
	private String lon;
	private String lat;
	private String sog;
	private String cog;
	private String hdg;
	private String danger;
	private String shipType;
	private String result;
	private String authDesc;
	private String insertDatetime;
	private String msg;
}
