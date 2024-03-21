package com.kdn.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kdn.core.domain.gmt.Ship;

public interface ShipRepository   extends JpaRepository<Ship, Long> {

	@Query(value = "SELECT * FROM ( " +
		"SELECT DENSE_RANK() OVER (PARTITION BY time_sect, ship_id ORDER BY time_sect, recptn_dt DESC) AS time_rk, b.* " +
		"FROM ( " +
		"SELECT CASE " +
		"WHEN rk = 1 THEN '1' " +
		"WHEN recptn_dt > to_char((NOW() - interval '6 min'), 'yyyy-mm-dd hh24:mi.ss') AND rk != 1 THEN '2' " +
		"WHEN recptn_dt > to_char((NOW() - interval '12 min'), 'yyyy-mm-dd hh24:mi.ss') AND recptn_dt < to_char((NOW() - interval '6 min'), 'yyyy-mm-dd hh24:mi.ss') AND rk != 1 THEN '3' " +
		"WHEN recptn_dt > to_char((NOW() - interval '18 min'), 'yyyy-mm-dd hh24:mi.ss') AND recptn_dt < to_char((NOW() - interval '12 min'), 'yyyy-mm-dd hh24:mi:ss') AND rk != 1 THEN '4' " +
		"WHEN recptn_dt > to_char((NOW() - interval '24 min'), 'yyyy-mm-dd hh24:mi.ss') AND recptn_dt < to_char((NOW() - interval '18 min'), 'yyyy-mm-dd hh24:mi:ss') AND rk != 1 THEN '5' " +
		"WHEN recptn_dt > to_char((NOW() - interval '30 min'), 'yyyy-mm-dd hh24:mi.ss') AND recptn_dt < to_char((NOW() - interval '24 min'), 'yyyy-mm-dd hh24:mi:ss') AND rk != 1 THEN '6' " +
		"END AS time_sect, a.* " +
		"FROM ( " +
		"SELECT DENSE_RANK() OVER (PARTITION BY ship_id ORDER BY recptn_dt DESC) AS rk, Ship.* " +
		"FROM Ship " +
		"WHERE recptn_dt > to_char((NOW() - interval '30 min'), 'yyyy-mm-dd hh24:mi:ss') " +
		") a " +
		") b " +
		") c WHERE time_rk = 1", nativeQuery = true)
	List<Ship> findRecentShipInfo();
}
