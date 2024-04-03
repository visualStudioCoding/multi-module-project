package com.kdn.core.repository.wpi;

import com.kdn.core.domain.wpi.Turbine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TurbineRepository  extends JpaRepository<Turbine, Long> {
    @Query("select max(t.serial) from Turbine t")
    Optional<Long> findMaxSerial();

	List<Turbine> findBySerial(Long serial);
}
