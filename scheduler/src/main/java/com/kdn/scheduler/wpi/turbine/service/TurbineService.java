package com.kdn.scheduler.wpi.turbine.service;

import com.kdn.core.domain.wpi.Turbine;

import java.util.List;

public interface TurbineService {

	List<Turbine> turbineApiList();
	List<Turbine> turbineDataGenerator();

}
