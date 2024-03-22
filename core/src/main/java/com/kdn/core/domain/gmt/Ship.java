package com.kdn.core.domain.gmt;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.ColumnResult;
import jakarta.persistence.ConstructorResult;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SqlResultSetMapping;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SqlResultSetMapping(
	name = "ShipDtoMapping",
	classes = @ConstructorResult(
		targetClass = ShipDto.class,
		columns = {
			@ColumnResult(name = "ship_id", type = String.class),
			@ColumnResult(name = "recptn_dt", type = String.class),
			@ColumnResult(name = "sensor", type = String.class),
			@ColumnResult(name = "lon", type = String.class),
			@ColumnResult(name = "lat", type = String.class),
			@ColumnResult(name = "sog", type = String.class),
			@ColumnResult(name = "cog", type = String.class),
			@ColumnResult(name = "hdg", type = String.class),
			@ColumnResult(name = "danger", type = String.class),
			@ColumnResult(name = "ship_type", type = String.class),
			@ColumnResult(name = "time_sect", type = String.class)
		}
	)
)
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
	private String recptnDt;

	@CreationTimestamp
	private LocalDateTime wdate;

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
