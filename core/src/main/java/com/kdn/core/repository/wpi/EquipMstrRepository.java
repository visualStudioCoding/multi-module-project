package com.kdn.core.repository.wpi;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.core.domain.wpi.equipment.EquipMstr;
import com.kdn.core.domain.wpi.equipment.TmRequest;

public interface EquipMstrRepository extends JpaRepository<EquipMstr, String> {

	Optional<EquipMstr> findByHokiAndEquipNo(String hoki, String equipNo);
}
