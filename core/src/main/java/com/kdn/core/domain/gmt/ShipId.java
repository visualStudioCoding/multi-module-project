package com.kdn.core.domain.gmt;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ShipId implements Serializable {
	private String shipId;
	private String recptnDt;
}
