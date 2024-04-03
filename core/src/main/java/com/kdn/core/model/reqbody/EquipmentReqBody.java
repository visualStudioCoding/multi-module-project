package com.kdn.core.model.reqbody;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EquipmentReqBody {
	private String swrk;
	private String hoki;
	private String description;
	private String equipNo;
	private String eqType;
	private String icndImp;
	private String itypFat;
	private String doccFat;
	private String etxtReq;
	private String ddayWrk;

}
