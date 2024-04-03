package com.kdn.wpi.service.turbine;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kdn.core.domain.wpi.Turbine;
import com.kdn.core.model.resbody.TurbineResBody;
import com.kdn.core.repository.wpi.TurbineRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@RequiredArgsConstructor
public class TurbineServiceImpl implements TurbineService {

	private final TurbineRepository turbineRepository;

	@Override
	public List<TurbineResBody> getTurbinesByMaxSerial() {
		long maxSerial = turbineRepository.findMaxSerial().orElse(0L);

		List<Turbine> turbines = turbineRepository.findBySerial(maxSerial);

		return turbines.stream().map(turbine -> {
			TurbineResBody turbineResBody = new TurbineResBody();
			turbineResBody.setSido(turbine.getSido());
			turbineResBody.setSite(turbine.getSite());
			turbineResBody.setSerial(turbine.getSerial());
			turbineResBody.setTurbineId(turbine.getTurbineId());
			turbineResBody.setRotorRpm(turbine.getRotorRpm());
			turbineResBody.setWindSpeed(turbine.getWindSpeed());
			turbineResBody.setAmbientTemp(turbine.getAmbientTemp());
			turbineResBody.setActivePower(turbine.getActivePower());
			turbineResBody.setAccumulatedPower(turbine.getAccumulatedPower());
			turbineResBody.setWtStatus(turbine.getWtStatus());
			turbineResBody.setPitchAngle1(turbine.getPitchAngle1());
			turbineResBody.setPitchAngle2(turbine.getPitchAngle2());
			turbineResBody.setPitchAngle3(turbine.getPitchAngle3());
			turbineResBody.setWrotPtctlst(turbine.getWrotPtctlst());
			turbineResBody.setNacellePosition(turbine.getNacellePosition());
			turbineResBody.setAmbientTemp(turbine.getAmbientTemp());
			turbineResBody.setWnacBecbulbst(turbine.getWnacBecbulbst());
			turbineResBody.setWnacAnest(turbine.getWnacAnest());
			turbineResBody.setWnacIntltmp(turbine.getWnacIntltmp());
			turbineResBody.setGeaboxBearingTemp1(turbine.getGeaboxBearingTemp1());
			turbineResBody.setGeaboxBearingTemp2(turbine.getGeaboxBearingTemp2());
			turbineResBody.setGeaboxBearingTemp3(turbine.getGeaboxBearingTemp3());
			turbineResBody.setGearboxOilTemp(turbine.getGearboxOilTemp());
			turbineResBody.setGeaboxOilPressure(turbine.getGeaboxOilPressure());
			turbineResBody.setGeneratorBearingTempLoad(turbine.getGeneratorBearingTempLoad());
			turbineResBody.setGeneratorBearingTempUnload(turbine.getGeneratorBearingTempUnload());
			turbineResBody.setGeneratorWindingTempU(turbine.getGeneratorWindingTempU());
			turbineResBody.setGeneratorWindingTempV(turbine.getGeneratorWindingTempV());
			turbineResBody.setGeneratorWindingTempW(turbine.getGeneratorWindingTempW());
			turbineResBody.setWgenGnopmod(turbine.getWgenGnopmod());
			turbineResBody.setWgenClst(turbine.getWgenClst());
			turbineResBody.setWgenGntmpinlet(turbine.getWgenGntmpinlet());
			turbineResBody.setWcnvCnvopmod(turbine.getWcnvCnvopmod());
			turbineResBody.setWcnvHz(turbine.getWcnvHz());
			turbineResBody.setWcnvGriphvA(turbine.getWcnvGriphvA());
			turbineResBody.setWcnvGriphvB(turbine.getWcnvGriphvB());
			turbineResBody.setWcnvGriphvC(turbine.getWcnvGriphvC());
			turbineResBody.setWcnvGriaA(turbine.getWcnvGriaA());
			turbineResBody.setWcnvGriaB(turbine.getWcnvGriaB());
			turbineResBody.setWcnvGriaC(turbine.getWcnvGriaC());
			turbineResBody.setWcnvGripfA(turbine.getWcnvGripfA());
			turbineResBody.setWcnvGripfB(turbine.getWcnvGripfB());
			turbineResBody.setWcnvGripfC(turbine.getWcnvGripfC());
			turbineResBody.setWcnvDclvol(turbine.getWcnvDclvol());
			turbineResBody.setWyawYawang(turbine.getWyawYawang());
			turbineResBody.setWyawCabwup(turbine.getWyawCabwup());
			turbineResBody.setWtrmBrkopmod(turbine.getWtrmBrkopmod());
			turbineResBody.setWtrmLust(turbine.getWtrmLust());
			turbineResBody.setWtrmFtrst(turbine.getWtrmFtrst());
			turbineResBody.setWtrmClst(turbine.getWtrmClst());
			return turbineResBody;
		}).collect(toList());

	}
}
