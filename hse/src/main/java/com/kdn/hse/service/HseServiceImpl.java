package com.kdn.hse.service;

import org.springframework.stereotype.Service;

import com.kdn.core.repository.hse.HseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class HseServiceImpl implements HseService {

	private final HseRepository hseJdbcRepository;

	@Override
	public int count() {
		return hseJdbcRepository.count();
	}
}
