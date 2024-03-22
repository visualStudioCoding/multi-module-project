package com.kdn.gmt.ship.service;

import java.util.List;
import java.util.Map;

import com.kdn.core.domain.gmt.Ship;
import com.kdn.core.model.resbody.ShipResBody;

public interface ShipService {

	Map<String, Object> getRecentShipList();
}
