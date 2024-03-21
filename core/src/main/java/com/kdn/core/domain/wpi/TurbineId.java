package com.kdn.core.domain.wpi;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TurbineId implements Serializable {

	private Long serial;
	private String sido;
	private String site;
	private String turbineId;
}
