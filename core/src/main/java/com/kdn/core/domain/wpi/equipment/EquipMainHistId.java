package com.kdn.core.domain.wpi.equipment;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EquipMainHistId implements Serializable {
	private String hoki;
	private String equipNo;
	private String ddayWrk;
	private String swrk;
}
