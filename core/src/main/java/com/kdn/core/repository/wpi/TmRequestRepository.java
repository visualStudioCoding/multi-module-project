package com.kdn.core.repository.wpi;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.core.domain.wpi.equipment.TmRequest;

public interface TmRequestRepository extends JpaRepository<TmRequest, String> {

	Optional<TmRequest> findBySmhnAndEquipNo(String smhn, String equipNo);

}
