package com.kdn.wpi.service.turbine;

import java.util.List;

import com.kdn.core.model.resbody.TurbineResBody;

public interface TurbineService {

	List<TurbineResBody> getTurbinesByMaxSerial();
}
