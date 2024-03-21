package com.kdn.scheduler.wpi.turbine.service;

import com.kdn.core.domain.wpi.Turbine;

import java.util.List;

public interface TurbineService {

	List<Turbine> getTurbines();
	List<Turbine> turbineDataGenerator();

}
