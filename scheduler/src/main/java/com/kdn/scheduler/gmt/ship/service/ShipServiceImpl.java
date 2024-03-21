package com.kdn.scheduler.gmt.ship.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kdn.core.domain.gmt.Ship;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShipServiceImpl implements ShipService {

	@Override
	public List<Ship> getShips() {
		return null;
	}
}
