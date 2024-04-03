package com.kdn.wpi.service.equipment;

import com.kdn.core.model.reqbody.EquipmentReqBody;
import com.kdn.core.model.resbody.EquipmentResBody;

public interface EquipmentService {
	EquipmentResBody getEquipment(String hoki);
	boolean updateEquipment(String hoki, EquipmentReqBody equipmentReqBody);
}
