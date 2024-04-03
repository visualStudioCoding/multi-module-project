package com.kdn.core.repository.custom;

import java.util.Optional;

import com.kdn.core.model.resbody.EquipmentResBody;


public interface EquipRepositoryCustom {
	EquipmentResBody findEquipMainHistByHoki(String hoki);

}
