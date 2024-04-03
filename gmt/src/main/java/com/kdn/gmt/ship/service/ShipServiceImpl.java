package com.kdn.gmt.ship.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.kdn.core.model.resbody.ShipResBody;
import com.kdn.core.repository.gmt.ShipRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService{

	private final ShipRepository shipRepository;

	@Override
	public Map<String, Object> getRecentShipList() {
		Map<String, Object> result = new HashMap<>();
		List<ShipResBody> dataList = new ArrayList<>();

		List<ShipResBody> ships = shipRepository.findRecentShipInfo();

		for (ShipResBody shipItem : ships) {
			/*ShipResBody shipResBody = new ShipResBody();*/

			String shipId = shipItem.getShipId();
			if ("VPASS".equals(shipItem.getSensor())) {
				byte[] decodedBytes = Base64.decodeBase64(shipId);
				shipId = new String(decodedBytes);
			}
			shipItem.setShipId(shipId);
			/*shipResBody.setTimeSect(shipItem.getTimeSect());
			shipResBody.setRecptnDt(shipItem.getRecptnDt());
			shipResBody.setLon(shipItem.getLon());
			shipResBody.setLat(shipItem.getLat());
			shipResBody.setSog(shipItem.getSog());
			shipResBody.setCog(shipItem.getCog());
			shipResBody.setHdg(shipItem.getHdg());
			shipResBody.setDanger(shipItem.getDanger());
			shipResBody.setSensor(shipItem.getSensor());
			shipResBody.setShipType(shipItem.getShipType());*/
			dataList.add(shipItem);
		}

		result.put("data", dataList);
		result.put("result", true);
		return result;
	}
}
