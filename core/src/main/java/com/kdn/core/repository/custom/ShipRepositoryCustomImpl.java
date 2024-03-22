package com.kdn.core.repository.custom;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kdn.core.domain.gmt.Ship;
import com.kdn.core.domain.gmt.ShipDto;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class ShipRepositoryCustomImpl implements ShipRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public void insertShipWithCondition(List<Ship> ships) {
		ships.forEach(ship -> entityManager.createNativeQuery("INSERT INTO Ship (ship_id, recptn_dt, sensor, lon, lat, sog, cog, hdg, danger, ship_type, result, msg, wdate) " +
			"SELECT :ship_id, :recptn_dt, :sensor, :lon, :lat, :sog, :cog, :hdg, :danger, :ship_type, :result, :msg, now() " +
			"WHERE NOT EXISTS (SELECT 1 FROM Ship WHERE ship_id = :ship_id AND recptn_dt = :recptn_dt)")
			.setParameter("ship_id", ship.getShipId())
			.setParameter("recptn_dt", ship.getRecptnDt())
			.setParameter("sensor", ship.getSensor())
			.setParameter("lon", ship.getLon())
			.setParameter("lat", ship.getLat())
			.setParameter("sog", ship.getSog())
			.setParameter("cog", ship.getCog())
			.setParameter("hdg", ship.getHdg())
			.setParameter("danger", ship.getDanger())
			.setParameter("ship_type", ship.getShipType())
			.setParameter("result", ship.getResult())
			.setParameter("msg", ship.getMsg())
			.executeUpdate());
	}

	public List<ShipDto> findRecentShipInfo() {
		List<ShipDto> ships = entityManager.createNativeQuery("WITH RankedShips AS ( "
			+ "    SELECT "
			+ "                DENSE_RANK() OVER (PARTITION BY ship_id ORDER BY recptn_dt DESC) AS rk, "
			+ "                ship_id, "
			+ "                recptn_dt, "
			+ "                cog, danger, hdg, lat, lon, sensor, ship_type, sog "
			+ "    FROM ship "
			+ "    WHERE "
			+ "            recptn_dt > to_char(NOW() - interval '30 min', 'yyyy-mm-dd hh24:mi:ss') "
			+ "), TimeSectShips AS ( "
			+ "    SELECT "
			+ "        CASE "
			+ "            WHEN rk = 1 THEN '1' "
			+ "            WHEN recptn_dt > to_char(NOW() - interval '6 min', 'yyyy-mm-dd hh24:mi:ss') THEN '2' "
			+ "            WHEN recptn_dt > to_char(NOW() - interval '12 min', 'yyyy-mm-dd hh24:mi:ss') THEN '3' "
			+ "            WHEN recptn_dt > to_char(NOW() - interval '18 min', 'yyyy-mm-dd hh24:mi:ss') THEN '4' "
			+ "            WHEN recptn_dt > to_char(NOW() - interval '24 min', 'yyyy-mm-dd hh24:mi:ss') THEN '5' "
			+ "            ELSE '6' "
			+ "            END AS time_sect, "
			+ "        rk, "
			+ "        ship_id, "
			+ "        recptn_dt, "
			+ "        cog, danger, hdg, lat, lon, sensor, ship_type, sog "
			+ "    FROM "
			+ "        RankedShips "
			+ ") "
			+ "SELECT * "
			+ "FROM ( "
			+ "         SELECT "
			+ "                     DENSE_RANK() OVER (PARTITION BY time_sect, ship_id ORDER BY time_sect, recptn_dt DESC) AS time_rk, * "
			+ "         FROM "
			+ "             TimeSectShips "
			+ "     ) AS FinalRanked "
			+ "WHERE time_rk = 1;", "ShipDtoMapping").getResultList();
		return ships;
	}

}
