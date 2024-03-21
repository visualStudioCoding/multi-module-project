package com.kdn.wpi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdn.core.model.resbody.TurbineResBody;
import com.kdn.wpi.service.turbine.TurbineService;

@RestController
@RequestMapping("/api/turbines")
public class APITurbineController {

    @Autowired
    private TurbineService turbineService;

    @GetMapping
    public ResponseEntity<List<TurbineResBody>> getTurbinesByMaxSerial() {
    	List<TurbineResBody> turbines = turbineService.getTurbinesByMaxSerial();
        return ResponseEntity.ok(turbines);
    }
}
