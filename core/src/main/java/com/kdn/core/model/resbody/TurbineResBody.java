package com.kdn.core.model.resbody;


import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TurbineResBody {


	private Long serial;
	private String sido;
	private String site;
	private String turbineId;
	private String wtStatus;
	private String windSpeed;
	private String activePower;
	private String accumulatedPower;
	private String ambientTemp;
	private String geaboxBearingTemp1;
	private String geaboxBearingTemp2;
	private String geaboxBearingTemp3;
	private String generatorBearingTempLoad;
	private String generatorBearingTempUnload;
	private String gearboxOilTemp;
	private String geaboxOilPressure;
	private String wrotPtctlst;
	private String wtrmOillevst;
	private String wtrmBrkopmod;
	private String wtrmLust;
	private String wtrmFtrst;
	private String wtrmClst;
	private String wgenGnopmod;
	private String wgenClst;
	private String wgenGntmpinlet;
	private String wcnvCnvopmod;
	private String wcnvHz;
	private String wnacBecbulbst;
	private String wnacAnest;
	private String generatorWindingTempW;
	private String generatorWindingTempU;
	private String generatorWindingTempV;
	private String nacellePosition;
	private String pitchAngle1;
	private String pitchAngle2;
	private String pitchAngle3;
	private String rotorRpm;
	private String wcnvDclvol;

	private String wcnvGriaA;
	private String wcnvGriaB;
	private String wcnvGriaC;
	private String wcnvGripfA;
	private String wcnvGripfB;
	private String wcnvGripfC;
	private String wnacIntltmp;
	private String wyawCabwup;
	private String wyawYawang;

	private String wcnvGriphvA;
	private String wcnvGriphvB;
	private String wcnvGriphvC;
}
