package com.kdn.core.repository.wpi;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.core.domain.wpi.equipment.EquipMainHist;
import com.kdn.core.model.resbody.EquipmentResBody;
import com.kdn.core.repository.custom.EquipRepositoryCustom;

public interface EquipMainHistRepository extends JpaRepository<EquipMainHist, String>, EquipRepositoryCustom {

	EquipmentResBody findEquipMainHistByHoki(String hoki);

	Optional<EquipMainHist> findByHokiAndEquipNo(String hoki, String equipNo);
}
