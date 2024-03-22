package com.kdn.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdn.core.domain.gmt.Ship;
import com.kdn.core.domain.gmt.ShipDto;
import com.kdn.core.repository.custom.ShipRepositoryCustom;

public interface ShipRepository extends JpaRepository<Ship, Long>, ShipRepositoryCustom {

	List<ShipDto> findRecentShipInfo();

	void insertShipWithCondition(List<Ship> ships);


}
