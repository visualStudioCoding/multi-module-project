package com.kdn.core.domain.wpi.equipment;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="te_equipmst")
public class EquipMstr {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String hoki;
	private String placeType;
	private String turbineType;
	private String turbineProduction;
	private Character turbineNumber;
	private String geomLoc;
	private String virtualGeomLoc;
	private String eqType;
	private String sequenceLogic;
	private String pnidNo;
	private String deptNo;
	private String tagNo;
	private Character isSafety;
	private Character isEnv;
	private Character isOh;
	private Character isCheck;
	private Character isPmCheck;
	private Character isRcmUnitTrip;
	private Character isRcmAffect;
	private Character isRbm;
	private String eqLocation;
	private String makeVendor;
	private String modelNo;
	private String serialNo;
	private String constructDate;
	private Integer specDesc;
	private Integer sortNo;
	private String saver;
	private String saveDt;
	private Character isMainMachine;
	private Integer operTimeSt;
	private String pnidNm;
	private String supplier;
	private String dataSource;
	private String mainMachineCheckDate;
	private String psmEquipNo;
	private String block;
	private String makeDt;
	private String description;
	private String equipNo;
	private String parentNo;
	private Character eqFamily;
	private String eqCategory;
	@CreationTimestamp
	private LocalDateTime wdate;

	public void modifyEquipMstr(String description, String eqType){
		this.description = description;
		this.eqType = eqType;
	}
}
