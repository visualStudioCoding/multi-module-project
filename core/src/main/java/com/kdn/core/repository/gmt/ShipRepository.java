package com.kdn.core.repository.gmt;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.core.domain.gmt.Ship;
import com.kdn.core.model.resbody.ShipResBody;
import com.kdn.core.repository.custom.ShipRepositoryCustom;

public interface ShipRepository extends JpaRepository<Ship, String>, ShipRepositoryCustom {

	List<ShipResBody> findRecentShipInfo();

	void insertShipWithCondition(List<Ship> ships);


}
