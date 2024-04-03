package com.kdn.gmt.ship.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdn.gmt.ship.service.ShipService;

@RestController
@RequestMapping("/api/ships")
public class APIShipContorller {

	@Autowired
	private ShipService shipService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getRecentShipList() {
		Map<String, Object> shipData = shipService.getRecentShipList();
		return ResponseEntity.ok(shipData);
	}

}
