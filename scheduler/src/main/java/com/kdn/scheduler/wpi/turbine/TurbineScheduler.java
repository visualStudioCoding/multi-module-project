package com.kdn.scheduler.wpi.turbine;

import com.kdn.core.domain.wpi.Turbine;
import com.kdn.core.repository.TurbineRepository;
import com.kdn.scheduler.wpi.turbine.service.TurbineService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class TurbineScheduler {

	private final TurbineRepository turbineRepository;

	@Autowired
	private TurbineService turbineService;

	// @Scheduled(cron = "10 * * * * *")
	@Scheduled(fixedRate = 60000) // 60초마다 실행
	public void fetchTurbineData() throws IOException {
		log.info("[turbine scheduler] START");
		// List<Turbine> turbines = turbineService.turbineDataGenerator();
		List<Turbine> turbines = turbineService.turbineApiList();
		turbineRepository.saveAll(turbines);
	}
}
