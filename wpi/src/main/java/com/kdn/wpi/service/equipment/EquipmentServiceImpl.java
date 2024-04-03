package com.kdn.wpi.service.equipment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kdn.core.domain.wpi.equipment.EquipMainHist;
import com.kdn.core.domain.wpi.equipment.EquipMstr;
import com.kdn.core.domain.wpi.equipment.TmRequest;
import com.kdn.core.model.reqbody.EquipmentReqBody;
import com.kdn.core.model.resbody.EquipmentResBody;
import com.kdn.core.repository.wpi.EquipMainHistRepository;
import com.kdn.core.repository.wpi.EquipMstrRepository;
import com.kdn.core.repository.wpi.TmRequestRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class EquipmentServiceImpl implements EquipmentService {

	private final EquipMainHistRepository equipMainHistRepository;
	private final EquipMstrRepository equipMstrRepository;
	private final TmRequestRepository tmRequestRepository;

	private final String ERR_MSG = "Entity not found";

	@Override
	public EquipmentResBody getEquipment(String hoki) {
		return equipMainHistRepository.findEquipMainHistByHoki(hoki);
	}

	@Transactional
	@Override
	public boolean updateEquipment(String hoki, EquipmentReqBody equipmentReqBody) {

		TmRequest tmRequest = tmRequestRepository.findBySmhnAndEquipNo(hoki, equipmentReqBody.getEquipNo())
			.orElseThrow(() -> new RuntimeException(ERR_MSG));
		tmRequest.modifyTmRequest(
			equipmentReqBody.getIcndImp(),
			equipmentReqBody.getDoccFat(),
			equipmentReqBody.getEtxtReq()
		);

		EquipMstr equipMstr = equipMstrRepository.findByHokiAndEquipNo(hoki, equipmentReqBody.getEquipNo())
			.orElseThrow(() -> new RuntimeException(ERR_MSG));
		equipMstr.modifyEquipMstr(
			equipmentReqBody.getDescription(),
			equipmentReqBody.getEqType()
		);

		EquipMainHist equipMainHist = equipMainHistRepository.findByHokiAndEquipNo(hoki, equipmentReqBody.getEquipNo())
			.orElseThrow(() -> new RuntimeException(ERR_MSG));
		equipMainHist.modifyEquipMainHist(
			equipmentReqBody.getSwrk(),
			equipmentReqBody.getItypFat(),
			equipmentReqBody.getDdayWrk()
		);

		/*try{
			equipMainHistRepository.save(equipMainHist);
			equipMstrRepository.save(equipMstr);
			tmRequestRepository.save(tmRequest);
		}catch (Exception e){
			return false;
		}*/
		return true;
	}
}
