package com.kdn.core.repository.wpi;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdn.core.domain.wpi.Weather;

public interface WeatherRepository extends JpaRepository<Weather, Long> {

	Optional<Weather> findTopByOrderByIdDesc();
}
