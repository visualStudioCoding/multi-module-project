package com.kdn.core.domain.wpi.equipment;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name="te_tmreq")
@IdClass(TmRequestId.class)
public class TmRequest {

	@Id
	private int orderNo;
	@Id
	private String tmNo;
	private String equipNo;
	private String ndepPub;
	private String dpub;
	private String spubSbn;
	private String procStt;
	private String smhn;
	private String ndepChr;
	private String tranRsn;
	private String ndepImp;
	private String fprk;
	private String tripYn;
	private String optYn;
	private String icndImp;
	private String itypFat;
	private String iposFat;
	private String iclsQcm;
	private String etxtReq;
	private String redTag;
	private String coprDept1;
	private String coprDept2;
	private String impYn;
	private String safTxt;
	private String impTxt;
	private String rslTxt;
	private String tslWrk;
	private String doccFat;
	private String dfinDt;
	private String dchgDt;
	private double qgorDes;
	private double qgorArs;
	private String histYn;
	private String nsbnImp;
	private String icauUns;
	private String icauFat;
	private String preTxt;
	private String etc;
	private String sttDee;
	private String dendImp;
	private int docSeqIssu;
	private int docSeqDsgn;
	private int docSeqMant;
	private int docSeqOper;
	private int docSeqTerm;
	private String saver;
	private String saveDt;
	private String confirmerOperator;
	private String docSeqDsgnAprv;
	private String workPermitYn;
	private String rslWrk;
	@CreationTimestamp
	private LocalDateTime wdate;

	public void modifyTmRequest(String icndImp, String doccFat, String etxtReq){
		this.icndImp = icndImp;
		this.doccFat = doccFat;
		this.etxtReq = etxtReq;
	}

}
