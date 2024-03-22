package com.kdn.gmt.ship.service;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.kdn.core.domain.gmt.Ship;
import com.kdn.core.domain.gmt.ShipDto;
import com.kdn.core.model.resbody.ShipResBody;
import com.kdn.core.repository.ShipRepository;

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

		List<ShipDto> ships = shipRepository.findRecentShipInfo();

		for (ShipDto shipItem : ships) {
			ShipResBody shipResBody = new ShipResBody();

			String shipId = shipItem.getShipId();
			if ("VPASS".equals(shipItem.getSensor())) {
				byte[] decodedBytes = Base64.decodeBase64(shipId);
				shipId = new String(decodedBytes);
			}
			shipResBody.setShipId(shipId);
			shipResBody.setTimeSect(shipItem.getTimeSect());
			shipResBody.setRecptnDt(shipItem.getRecptnDt());
			shipResBody.setLon(shipItem.getLon());
			shipResBody.setLat(shipItem.getLat());
			shipResBody.setSog(shipItem.getSog());
			shipResBody.setCog(shipItem.getCog());
			shipResBody.setHdg(shipItem.getHdg());
			shipResBody.setDanger(shipItem.getDanger());
			shipResBody.setSensor(shipItem.getSensor());
			shipResBody.setShipType(shipItem.getShipType());
			dataList.add(shipResBody);
		}

		result.put("data", dataList);
		result.put("result", true);
		return result;
	}
}
