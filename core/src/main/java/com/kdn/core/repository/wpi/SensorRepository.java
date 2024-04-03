package com.kdn.core.repository.wpi;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SensorRepository {

	private final JdbcTemplate jdbcTemplate;

	public int count() {
		Integer result = jdbcTemplate.queryForObject("select count(*) from HSE_RISK_ASSESSMENT_MEMBER", Integer.class);
		if (result != null) {
			return result;
		} else {
			return 0;
		}
	}
}
