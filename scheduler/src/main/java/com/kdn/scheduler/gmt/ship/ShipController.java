package com.kdn.scheduler.gmt.ship;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kdn.core.domain.gmt.Ship;
import com.kdn.core.repository.gmt.ShipRepository;
import com.kdn.scheduler.gmt.ship.service.ShipService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ShipController {

	private final ShipRepository shipRepository;

	@Autowired
	private ShipService shipService;

	@Scheduled(fixedRate = 10000) // 10초마다 실행
	public void fetchShipData() {
		log.info("[ship scheduler] START");
		List<Ship> ships = shipService.shipApiList();
		shipRepository.insertShipWithCondition(ships);
	}
}
