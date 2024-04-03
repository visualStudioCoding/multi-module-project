package com.kdn.wpi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kdn.core.model.reqbody.EquipmentReqBody;
import com.kdn.core.model.resbody.EquipmentResBody;
import com.kdn.wpi.service.equipment.EquipmentService;

@RestController
@RequestMapping("/api/equipments")
public class APIEquipmentController {

	@Autowired
	private EquipmentService equipmentService;

	@GetMapping("/{hoki}")
	public ResponseEntity<EquipmentResBody> getEquipment(@PathVariable String hoki) {
		return ResponseEntity.ok(equipmentService.getEquipment(hoki));
	}

	@PutMapping("/{hoki}")
	public ResponseEntity<Boolean> updateEquipment(@PathVariable String hoki, @RequestBody EquipmentReqBody equipmentReqBody) {

		/*
		equipmentReqBody.setEqType("MHY344");
		equipmentReqBody.setIcndImp("102");
		equipmentReqBody.setDoccFat("2024-03-27 BC");
		equipmentReqBody.setEtxtReq("주요알람 : 2020.01.28 07:40 Yaw motor fault\r\n결함부위 : Yaw motor 동작불량\r\n점검항목 : Yaw converter");
		equipmentReqBody.setDescription("#9 Yaw Drive-1(YAD300)");
		equipmentReqBody.setItypFat("FRSPPPP");
		*/
		try{
			equipmentService.updateEquipment(hoki, equipmentReqBody);
		}catch (Exception e){
			return ResponseEntity.ok(false);
		}

		return ResponseEntity.ok(true);
	}

}
