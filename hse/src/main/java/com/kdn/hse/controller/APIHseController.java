package com.kdn.hse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdn.hse.service.HseService;

@RestController
@RequestMapping("/api/hses")
public class APIHseController {


	@Autowired
	private HseService hseService;

	@GetMapping
	public ResponseEntity<Integer> getTest() {
		int count = hseService.count();
		return ResponseEntity.ok(count);
	}
}

