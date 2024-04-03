package com.kdn.core.domain.wpi.equipment;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.kdn.core.model.resbody.EquipmentResBody;
import com.kdn.core.model.resbody.ShipResBody;

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
	name = "EquipmentResBodyMapping",
	classes = @ConstructorResult(
		targetClass = EquipmentResBody.class,
		columns = {
			@ColumnResult(name = "swrk", type = String.class),
			@ColumnResult(name = "hoki", type = String.class),
			@ColumnResult(name = "description", type = String.class),
			@ColumnResult(name = "equip_No", type = String.class),
			@ColumnResult(name = "eq_Type", type = String.class),
			@ColumnResult(name = "icnd_imp", type = String.class),
			@ColumnResult(name = "ityp_Fat", type = String.class),
			@ColumnResult(name = "docc_fat", type = String.class),
			@ColumnResult(name = "etxt_req", type = String.class),
			@ColumnResult(name = "dday_Wrk", type = String.class),
		}
	)
)

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="te_equipmainlist")
@IdClass(EquipMainHistId.class)
public class EquipMainHist {

	@Id
	private String hoki;

	@Id
	private String equipNo;

	@Id
	private String ddayWrk;

	@Id
	private String swrk;

	private String dfinWrk;

	private String ikndImp;

	private Integer qtmeNed;

	private String itypFat;

	private String npsnWrk;

	private String npsnVrf;

	private String npsnWrt;

	private String etxtWrk;

	private String saver;

	private String saveDt;

	@CreationTimestamp
	private LocalDateTime wdate;

	public void modifyEquipMainHist(String swrk, String itypFat, String ddayWrk){
		this.swrk = swrk;
		this.itypFat = itypFat;
		this.ddayWrk = ddayWrk;
	}
}
