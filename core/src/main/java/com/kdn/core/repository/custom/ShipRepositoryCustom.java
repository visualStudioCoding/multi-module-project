package com.kdn.core.repository.custom;

import java.util.List;

import com.kdn.core.domain.gmt.Ship;
import com.kdn.core.model.resbody.ShipResBody;

public interface ShipRepositoryCustom {

	void insertShipWithCondition(List<Ship> ships);

	List<ShipResBody> findRecentShipInfo();

}
