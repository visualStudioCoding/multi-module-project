package com.kdn.core.repository.custom;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kdn.core.model.resbody.EquipmentResBody;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class EquipRepositoryCustomImpl implements EquipRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;

	public EquipmentResBody findEquipMainHistByHoki(String hoki) {
		String sql = "SELECT a.swrk, a.hoki, c.description, c.equip_No, c.eq_Type, b.icnd_imp, a.ityp_Fat, b.docc_fat, b.etxt_req, a.dday_Wrk " +
			"FROM te_equipmainlist a " +
			"JOIN te_tmreq b ON a.hoki = b.smhn AND a.equip_No = b.equip_No " +
			"JOIN te_equipmst c ON b.smhn = c.hoki AND b.equip_No = c.equip_No " +
			"WHERE a.hoki = :hoki " +
			"ORDER BY a.swrk DESC " +
			"LIMIT 1";
		return (EquipmentResBody)entityManager.createNativeQuery(sql, "EquipmentResBodyMapping")
			.setParameter("hoki", hoki).getSingleResult();
	}
}
