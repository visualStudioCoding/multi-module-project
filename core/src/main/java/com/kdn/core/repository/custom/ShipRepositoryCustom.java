package com.kdn.core.repository.custom;

import java.util.List;

import com.kdn.core.domain.gmt.Ship;
import com.kdn.core.domain.gmt.ShipDto;

public interface ShipRepositoryCustom {

	void insertShipWithCondition(List<Ship> ships);

	List<ShipDto> findRecentShipInfo();

}
