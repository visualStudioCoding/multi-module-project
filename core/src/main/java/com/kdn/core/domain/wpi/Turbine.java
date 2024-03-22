package com.kdn.core.domain.wpi;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table
@IdClass(TurbineId.class)
public class Turbine {

	@CreationTimestamp
	private LocalDateTime wdate;

	@Id
	private Long serial;
	@Id
	private String sido;
	@Id
	private String site;
	@Id
	@JsonProperty("id")
	private String turbineId;

	private String wtStatus;
	private String windSpeed;
	private String windDir;
	private String activePower;
	private String reactivePower;
	private String accumulatedPower;
	private String uvVoltage;
	private String vwVoltage;
	private String wuVoltage;
	@JsonProperty("u_current")
	@Column(name="u_current")
	private String uCurrent;
	@JsonProperty("v_current")
	@Column(name="v_current")
	private String vCurrent;
	@JsonProperty("w_currnet")
	@Column(name="w_current")
	private String wCurrent;
	private String yawAction;
	private String pitchAction;
	private String idlingAction;
	private String ambientTemp;
	private String geaboxBearingTemp1;
	private String geaboxBearingTemp2;
	private String geaboxBearingTemp3;
	private String generatorBearingTempLoad;
	private String generatorBearingTempUnload;
	private String gearboxOilTemp;
	private String geaboxOilPressure;
	private String wrotPtctlst;
	private String wtrmBrkopmod;
	private String wtrmLust;
	private String wtrmFtrst;
	private String wtrmClst;
	private String wtrmOillevst;
	private String wgenGnopmod;
	private String wgenClst;
	private String wgenGntmpinlet;
	private String wcnvCnvopmod;
	private String wcnvClst;
	private String wcnvHz;
	private String wtrfGriconst;
	private String wnacBecbulbst;
	private String wnacAnest;
	private String alarm1;
	private String alarm2;
	private String alarm3;
	private String alarm4;
	private String alarm5;
	private String alarm6;
	private String alarm7;
	private String alarm8;
	private String err1;
	private String err2;
	private String err3;
	private String err4;
	private String err5;
	private String err6;
	private String err7;
	private String err8;
	private String turbineTime;
	private String turbineDt;
	private String wfcTurbinenumber;
	private String wfcStpowerlimit;
	private String wfcStfault;
	private String wfcStgrid;
	private String wfcStgriccon;
	private String a001;
	private String a002;
	private String a003;
	private String a004;
	private String a005;
	private String a006;
	private String a007;
	private String a008;
	private String a009;
	private String a010;
	private String a011;
	private String a012;
	private String a013;
	private String a014;
	private String a015;
	private String a016;
	private String a017;
	private String a018;
	private String a019;
	private String a020;
	private String a021;
	private String a022;
	private String a023;
	private String a024;
	private String a025;
	private String a026;
	private String a027;
	private String a028;
	private String a029;
	private String a030;
	private String a031;
	private String a032;
	private String a033;
	private String a034;
	private String a035;
	private String a036;
	private String a037;
	private String a038;
	private String a039;
	private String a040;
	private String auxtrawndtem001;
	private String auxtrawndtem002;
	private String blacabtem001;
	private String blacabtem002;
	private String blacabtem003;
	private String blamottem001;
	private String blamottem002;
	private String blamottem003;
	private String envtem;
	private String geaoilinppre;
	private String geaoilinptem;
	private String geaoiltnktem;
	private String generatorRpm;
	@JsonProperty("generator_winding_temp_w")
	@Column(name="generator_winding_temp_w")
	private String generatorWindingTempW;
	@JsonProperty("generator_winding_temp_u")
	@Column(name="generator_winding_temp_u")
	private String generatorWindingTempU;
	@JsonProperty("generator_winding_temp_v")
	@Column(name="generator_winding_temp_v")
	private String generatorWindingTempV;
	private String igbtTemp1;
	private String igbtTemp2;
	private String naca2afanteminp;
	private String naca2afantemout;
	private String naccabtem300;
	private String naccabtem310;
	private String nacellePosition;
	private String nacpsnsperdd;
	private String pitchAngle1;
	private String pitchAngle2;
	private String pitchAngle3;
	private String rotorRpm;
	private String towcabtem;
	private String towtem;
	private String upscabtem;
	private String wcnvDclvol;

	@JsonProperty("wcnv_gria_a")
	@Column(name="wcnv_gria_a")
	private String wcnvGriaA;

	@JsonProperty("wcnv_gria_b")
	@Column(name="wcnv_gria_b")
	private String wcnvGriaB;
	@JsonProperty("wcnv_gria_c")
	@Column(name="wcnv_gria_c")
	private String wcnvGriaC;
	@JsonProperty("wcnv_gripf_a")
	@Column(name="wcnv_gripf_a")
	private String wcnvGripfA;
	@JsonProperty("wcnv_gripf_b")
	@Column(name="wcnv_gripf_b")
	private String wcnvGripfB;
	@JsonProperty("wcnv_gripf_c")
	@Column(name="wcnv_gripf_c")
	private String wcnvGripfC;
	private String wfcCmdfbmodeactpow;
	private String wfcCmdfbmoderctpow;
	private String wfcCmdfbmodewpc;
	private String wfcCmdfbwfc;
	private String wfcDmdfbactpwrlim;
	private String wfcDmdfbactpwrramlim;
	private String wfcDmdfbpf;
	private String wfcDmdfbrctpwr;
	private String wfcStaclactpwr;
	private String wfcStaclactpwrlim;
	private String wfcStaclrctpwr;
	private String wfcStactpwrrmplim;
	private String wfcStmodeactpow;
	private String wfcStmoderctpow;
	private String wfcStmodewpc;
	private String wfcTurbineactpwrrate;
	private String wfcTurbinenegrctpwrrate;
	private String wfcTurbineposrctpwrrate;
	private String wfcTurbinetype;
	private String wnacIntltmp;
	private String wtrfTrftmptrfgri;
	private String wtrfTrftmptrftur;
	private String wyawCabwup;
	private String wyawYawang;
	private String time;
	@JsonProperty("wcnv_griphv_a")
	@Column(name="wcnv_griphv_a")
	private String wcnvGriphvA;
	@JsonProperty("wcnv_griphv_b")
	@Column(name="wcnv_griphv_b")
	private String wcnvGriphvB;
	@JsonProperty("wcnv_griphv_c")
	@Column(name="wcnv_griphv_c")
	private String wcnvGriphvC;
}
