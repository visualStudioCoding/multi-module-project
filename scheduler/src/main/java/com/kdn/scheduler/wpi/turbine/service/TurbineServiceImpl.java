package com.kdn.scheduler.wpi.turbine.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.kdn.core.domain.wpi.Turbine;
import com.kdn.core.repository.TurbineRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TurbineServiceImpl implements TurbineService {

	private final TurbineRepository turbineRepository;

	private static final Random random = new Random();

	private final ApplicationContext applicationContext;

	public List<Turbine> getTurbines() {

		List<Turbine> turbines =  new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

		try {
			Map<String, String> turbineApiProperties = applicationContext.getBean("turbineApiProperties",
				Map.class);

			URL url = new URL(turbineApiProperties.get("turbineUrl"));
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.setRequestMethod("GET");

			int responseCode = conn.getResponseCode();

			if (responseCode != HttpURLConnection.HTTP_OK) {
				log.error("Failed to fetch data. Response code: " + responseCode);
				return Collections.emptyList();
			}

			StringBuilder response = new StringBuilder();
			try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
				String inputLine;
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
			}

			long maxSerial = turbineRepository.findMaxSerial().orElse(0L);
			log.info("[maxSerial]" + maxSerial);

			JSONObject jObject = new JSONObject(response.toString());
			Iterator<String> objKeys = jObject.keys(); // key값들을 모두 얻어옴.

			int firstKeyIndex = 1;
			while(objKeys.hasNext()) {
				String firstKey = objKeys.next();
				JSONObject tObject = jObject.getJSONObject(firstKey);
				Turbine turbine = mapper.convertValue(tObject.toMap(), Turbine.class);

				turbine.setSido("고창");
				turbine.setSite("01");
				turbine.setSerial(maxSerial + 1);
				turbines.add(turbine);

				log.info("(" + firstKeyIndex + ")번째 Key ->" + firstKey + " : " + jObject.get(firstKey));
				firstKeyIndex++;
			}
		} catch (IOException e) {
			log.error("IOException occurred", e);
		} catch (Exception e) {
			log.error("Unexpected error occurred", e);
		}
		return turbines;
	}


	// 터빈데이터 랜덤 생성, 20240320 고창해상풍 데이터 사용으로 미사용 예정
	public List<Turbine> turbineDataGenerator() {

		List<Turbine> turbines = new ArrayList<>();

		long maxSerial = turbineRepository.findMaxSerial().orElse(0L);

		log.info("[maxSerial]" + maxSerial);

		LocalDateTime currentTime = LocalDateTime.now();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedTime = currentTime.format(formatter);

		// DecimalFormat 객체 생성하여 다섯 번째 자리에서 반올림
		DecimalFormat df = new DecimalFormat("#.#####");

		for (int i = 1; i <= 20; i++) {
			Turbine turbine = new Turbine();
			turbine.setSerial(maxSerial + 1);
			turbine.setTurbineId("tur" + i);
			turbine.setSido("고창");
			turbine.setSite("1");

			turbine.setTime(formattedTime);

			turbine.setWtStatus(String.valueOf(random.nextInt(0, 20)));
			turbine.setYawAction(String.valueOf(random.nextInt(0, 25)));
			turbine.setWindSpeed(String.valueOf(df.format(random.nextDouble(0, 10))));
			turbine.setWindDir(String.valueOf(df.format(random.nextDouble(-10, 10))));
			turbine.setActivePower(String.valueOf(df.format(random.nextDouble(1000, 2000))));
			turbine.setReactivePower(String.valueOf(df.format(random.nextDouble(-5, 35))));
			turbine.setAccumulatedPower(String.valueOf(df.format(random.nextDouble(11776, 11777))));
			turbine.setUvVoltage(String.valueOf(df.format(random.nextDouble(45, 550))));
			turbine.setVwVoltage(String.valueOf(df.format(random.nextDouble(50, 600))));
			turbine.setWuVoltage(String.valueOf(df.format(random.nextDouble(90, 800))));
			turbine.setUCurrent(String.valueOf(df.format(random.nextDouble(30, 500))));
			turbine.setVCurrent(String.valueOf(df.format(random.nextDouble(70, 800))));
			turbine.setWCurrent(String.valueOf(df.format(random.nextDouble(70, 1000))));
			turbine.setPitchAction(String.valueOf(df.format(random.nextDouble(50, 54))));
			turbine.setIdlingAction(String.valueOf(df.format(random.nextDouble(50, 54))));
			turbine.setAmbientTemp(String.valueOf(df.format(random.nextDouble(50, 54))));

			String[] gearboxBearingTemps = new String[3];
			String[] blacabtems = new String[3];
			String[] blamottems = new String[3];
			String[] windingTemps = new String[3];
			String[] wcvnGrias = new String[3];
			String[] wcvnGripfs = new String[3];
			String[] wcnvGrpihvs = new String[3];
			for (int j = 0; j < 3; j++) {
				if (i % 9 == 0) {
					gearboxBearingTemps[j] = String.valueOf(df.format(random.nextDouble(98, 99)));
					blacabtems[j] = String.valueOf(df.format(random.nextDouble(108, 111)));
					blamottems[j] = String.valueOf(df.format(random.nextDouble(115, 118)));
					windingTemps[j] = String.valueOf(df.format(random.nextDouble(54, 57)));
					wcvnGrias[j] = String.valueOf(df.format(random.nextDouble(73, 75)));
					wcvnGripfs[j] = String.valueOf(df.format(random.nextDouble(57, 59)));
					wcnvGrpihvs[j] = String.valueOf(df.format(random.nextDouble(56, 57)));
				} else if (i % 3 == 0) {
					gearboxBearingTemps[j] = String.valueOf(df.format(random.nextDouble(129, 131)));
					blacabtems[j] = String.valueOf(df.format(random.nextDouble(70, 72)));
					blamottems[j] = String.valueOf(df.format(random.nextDouble(73, 74)));
					windingTemps[j] = String.valueOf(df.format(random.nextDouble(50, 51)));
					wcvnGrias[j] = String.valueOf(df.format(random.nextDouble(57, 58)));
					wcvnGripfs[j] = String.valueOf(df.format(random.nextDouble(60, 62)));
					wcnvGrpihvs[j] = String.valueOf(df.format(random.nextDouble(58, 60)));
				} else if (i % 5 == 0) {
					gearboxBearingTemps[j] = String.valueOf(df.format(random.nextDouble(54, 55)));
					blacabtems[j] = String.valueOf(df.format(random.nextDouble(50, 51)));
					blamottems[j] = String.valueOf(df.format(random.nextDouble(50, 51)));
					windingTemps[j] = String.valueOf(df.format(random.nextDouble(51, 52)));
					wcvnGrias[j] = String.valueOf(df.format(random.nextDouble(59, 60)));
					wcvnGripfs[j] = String.valueOf(df.format(random.nextDouble(75, 78)));
					wcnvGrpihvs[j] = String.valueOf(df.format(random.nextDouble(70, 73)));
				} else if (i % 7 == 0) {
					gearboxBearingTemps[j] = String.valueOf(df.format(random.nextDouble(98, 99)));
					blacabtems[j] = String.valueOf(df.format(random.nextDouble(97, 99)));
					blamottems[j] = String.valueOf(df.format(random.nextDouble(102, 104)));
					windingTemps[j] = String.valueOf(df.format(random.nextDouble(54, 55)));
					wcvnGrias[j] = String.valueOf(df.format(random.nextDouble(76, 79)));
					wcvnGripfs[j] = String.valueOf(df.format(random.nextDouble(79, 80)));
					wcnvGrpihvs[j] = String.valueOf(df.format(random.nextDouble(73, 76)));
				} else {
					gearboxBearingTemps[j] = String.valueOf(df.format(random.nextDouble(68, 69)));
					blacabtems[j] = String.valueOf(df.format(random.nextDouble(65, 67)));
					blamottems[j] = String.valueOf(df.format(random.nextDouble(65, 67)));
					windingTemps[j] = String.valueOf(df.format(random.nextDouble(51, 53)));
					wcvnGrias[j] = String.valueOf(df.format(random.nextDouble(50, 51)));
					wcvnGripfs[j] = String.valueOf(df.format(random.nextDouble(50, 52)));
					wcnvGrpihvs[j] = String.valueOf(df.format(random.nextDouble(50, 52)));
				}
			}

			turbine.setGeaboxBearingTemp1(gearboxBearingTemps[0]);
			turbine.setGeaboxBearingTemp2(gearboxBearingTemps[1]);
			turbine.setGeaboxBearingTemp3(gearboxBearingTemps[2]);
			turbine.setWtrmOillevst(String.valueOf(random.isDeprecated()));
			turbine.setWtrfGriconst(String.valueOf(random.isDeprecated()));
			turbine.setGeneratorBearingTempLoad(String.valueOf(df.format(random.nextDouble(200, 201))));
			turbine.setGeneratorBearingTempUnload(String.valueOf(df.format(random.nextDouble(55, 134))));
			turbine.setGearboxOilTemp(String.valueOf(df.format(random.nextDouble(50, 60))));
			turbine.setGeaboxOilPressure(String.valueOf(df.format(random.nextDouble(50, 60))));
			turbine.setWrotPtctlst(df.format(random.nextInt(0, 10)));
			turbine.setWtrmBrkopmod(String.valueOf(df.format(random.nextDouble(50, 61))));
			turbine.setWtrmLust(String.valueOf(df.format(random.nextDouble(50, 61))));
			turbine.setWtrmFtrst(String.valueOf(df.format(random.nextDouble(50, 62))));
			turbine.setWtrmClst(String.valueOf(df.format(random.nextDouble(50, 62))));
			turbine.setWgenGnopmod(String.valueOf(df.format(random.nextDouble(50, 65))));
			turbine.setWgenClst(String.valueOf(df.format(random.nextDouble(50, 65))));
			turbine.setWgenGntmpinlet(String.valueOf(df.format(random.nextDouble(50, 66))));
			turbine.setWcnvCnvopmod(df.format(random.nextInt(0, 25)));
			turbine.setWcnvClst(String.valueOf(df.format(random.nextDouble(50, 65))));
			turbine.setWcnvHz(String.valueOf(df.format(random.nextDouble(50, 67))));
			turbine.setWnacBecbulbst(String.valueOf(df.format(random.nextDouble(50, 80))));
			turbine.setWnacAnest(String.valueOf(df.format(random.nextDouble(50, 80))));

			if (i == 12) {
				turbine.setAlarm1("6661");
				turbine.setAlarm2("6662");
				turbine.setAlarm3("6663");
				turbine.setAlarm4("6664");
				turbine.setAlarm5("6665");
				turbine.setAlarm6("6666");
				turbine.setAlarm7("6667");
				turbine.setAlarm8("6668");
			} else {
				turbine.setAlarm1("");
				turbine.setAlarm2("");
				turbine.setAlarm3("");
				turbine.setAlarm4("");
				turbine.setAlarm5("");
				turbine.setAlarm6("");
				turbine.setAlarm7("");
				turbine.setAlarm8("");
			}
			if (i == 11) {
				turbine.setErr1("6661");
				turbine.setErr2("6662");
				turbine.setErr3("6663");
				turbine.setErr4("6664");
				turbine.setErr5("6665");
				turbine.setErr6("6666");
				turbine.setErr7("6667");
				turbine.setErr8("6668");
			} else {
				turbine.setErr1("");
				turbine.setErr2("");
				turbine.setErr3("");
				turbine.setErr4("");
				turbine.setErr5("");
				turbine.setErr6("");
				turbine.setErr7("");
				turbine.setErr8("");
			}
			turbine.setTurbineDt("609700");
			turbine.setTurbineTime(String.valueOf(df.format(random.nextDouble(50, 127))));
			turbine.setWfcTurbinenumber(String.valueOf(df.format(random.nextDouble(50, 129))));
			turbine.setWfcStpowerlimit(String.valueOf(df.format(random.nextDouble(50, 134))));
			turbine.setWfcStfault(String.valueOf(df.format(random.nextDouble(50, 135))));
			turbine.setWfcStgrid(String.valueOf(df.format(random.nextDouble(50, 136))));
			turbine.setWfcStgriccon(String.valueOf(df.format(random.nextDouble(50, 139))));
			turbine.setA001(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA002(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA003(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA004(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA005(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA006(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA007(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA008(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA009(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA010(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA011(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA012(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA013(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA014(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA015(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA016(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA017(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA018(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA019(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA020(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA021(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA022(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA023(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA024(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA025(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA026(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA027(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA028(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA029(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA030(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA031(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA032(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA033(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA034(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA035(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA036(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA037(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA038(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA039(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setA040(String.valueOf(df.format(random.nextDouble(-40, 40))));
			turbine.setAuxtrawndtem001(String.valueOf(df.format(random.nextDouble(50, 108))));
			turbine.setAuxtrawndtem002(String.valueOf(df.format(random.nextDouble(50, 108))));
			turbine.setEnvtem(String.valueOf(df.format(random.nextDouble(50, 119))));
			turbine.setGeaoilinppre(String.valueOf(df.format(random.nextDouble(50, 125))));
			turbine.setGeaoilinptem(String.valueOf(df.format(random.nextDouble(50, 125))));
			turbine.setGeaoiltnktem(String.valueOf(df.format(random.nextDouble(50, 123))));
			turbine.setGeneratorRpm(String.valueOf(df.format(random.nextDouble(163, 164))));
			turbine.setBlacabtem001(blacabtems[0]);
			turbine.setBlacabtem002(blacabtems[1]);
			turbine.setBlacabtem003(blacabtems[2]);
			turbine.setBlamottem001(blamottems[0]);
			turbine.setBlamottem002(blamottems[1]);
			turbine.setBlamottem003(blamottems[2]);
			turbine.setGeneratorWindingTempW(windingTemps[0]);
			turbine.setGeneratorWindingTempU(windingTemps[1]);
			turbine.setGeneratorWindingTempV(windingTemps[2]);

			String[] igbtTemps = new String[2];
			String[] naccabtems = new String[2];
			for (int j = 0; j < 2; j++) {
				if (i % 9 == 0) {
					igbtTemps[j] = String.valueOf(df.format(random.nextDouble(52, 54)));
					naccabtems[j] = String.valueOf(df.format(random.nextDouble(71, 73)));
				}
				if (i % 3 == 0) {
					igbtTemps[j] = String.valueOf(df.format(random.nextDouble(50, 51)));
					naccabtems[j] = String.valueOf(df.format(random.nextDouble(104, 105)));
				} else if (i % 7 == 0) {
					igbtTemps[j] = String.valueOf(df.format(random.nextDouble(57, 59)));
					naccabtems[j] = String.valueOf(df.format(random.nextDouble(111, 113)));
				} else {
					igbtTemps[j] = String.valueOf(df.format(random.nextDouble(52, 53)));
					naccabtems[j] = String.valueOf(df.format(random.nextDouble(66, 68)));
				}
			}

			turbine.setIgbtTemp1(igbtTemps[0]);
			turbine.setIgbtTemp2(igbtTemps[1]);
			turbine.setNaca2afanteminp(String.valueOf(df.format(random.nextDouble(50, 121))));
			turbine.setNaca2afantemout(String.valueOf(df.format(random.nextDouble(50, 121))));
			turbine.setNaccabtem300(naccabtems[0]);
			turbine.setNaccabtem310(naccabtems[1]);
			turbine.setNacellePosition(String.valueOf(df.format(random.nextDouble(0, 670))));
			turbine.setNacpsnsperdd(String.valueOf(df.format(random.nextDouble(50, 126))));

			if (i % 2 == 0) {
				turbine.setPitchAngle1("275");
				turbine.setPitchAngle2("276");
				turbine.setPitchAngle3("0");
			} else {
				turbine.setPitchAngle1("286");
				turbine.setPitchAngle2("288");
				turbine.setPitchAngle3("13");
			}
			turbine.setWcnvGriaA(wcvnGrias[0]);
			turbine.setWcnvGriaB(wcvnGrias[1]);
			turbine.setWcnvGriaC(wcvnGrias[2]);
			turbine.setWcnvGripfA(wcvnGripfs[0]);
			turbine.setWcnvGripfB(wcvnGripfs[1]);
			turbine.setWcnvGripfC(wcvnGripfs[2]);
			turbine.setRotorRpm(String.valueOf(df.format(random.nextDouble(7, 11))));
			turbine.setTowcabtem(String.valueOf(df.format(random.nextDouble(50, 117))));
			turbine.setTowtem(String.valueOf(df.format(random.nextDouble(50, 122))));
			turbine.setUpscabtem(String.valueOf(df.format(random.nextDouble(50, 115))));
			turbine.setWcnvDclvol(String.valueOf(df.format(random.nextDouble(50, 83))));
			turbine.setWfcCmdfbmodeactpow(String.valueOf(df.format(random.nextDouble(50, 146))));
			turbine.setWfcCmdfbmoderctpow(String.valueOf(df.format(random.nextDouble(50, 146))));
			turbine.setWfcCmdfbmodewpc(String.valueOf(df.format(random.nextDouble(50, 146))));
			turbine.setWfcCmdfbwfc(String.valueOf(df.format(random.nextDouble(50, 146))));
			turbine.setWfcDmdfbactpwrlim(String.valueOf(df.format(random.nextDouble(50, 150))));
			turbine.setWfcDmdfbactpwrramlim(String.valueOf(df.format(random.nextDouble(50, 150))));
			turbine.setWfcDmdfbpf(String.valueOf(df.format(random.nextDouble(50, 151))));
			turbine.setWfcDmdfbrctpwr(String.valueOf(df.format(random.nextDouble(50, 151))));
			turbine.setWfcStaclactpwr(String.valueOf(df.format(random.nextDouble(50, 142))));
			turbine.setWfcStaclactpwrlim(String.valueOf(df.format(random.nextDouble(50, 141))));
			turbine.setWfcStaclrctpwr(String.valueOf(df.format(random.nextDouble(50, 142))));
			turbine.setWfcStactpwrrmplim(String.valueOf(df.format(random.nextDouble(50, 143))));
			turbine.setWfcStgriccon(String.valueOf(df.format(random.nextDouble(50, 136))));
			turbine.setWfcStmodeactpow(String.valueOf(df.format(random.nextDouble(50, 138))));
			turbine.setWfcStmoderctpow(String.valueOf(df.format(random.nextDouble(50, 139))));
			turbine.setWfcStmodewpc(String.valueOf(df.format(random.nextDouble(50, 137))));
			turbine.setWfcTurbineactpwrrate(String.valueOf(df.format(random.nextDouble(50, 131))));
			turbine.setWfcTurbinenegrctpwrrate(String.valueOf(df.format(random.nextDouble(50, 136))));
			turbine.setWfcTurbineposrctpwrrate(String.valueOf(df.format(random.nextDouble(50, 132))));
			turbine.setWfcTurbinetype(String.valueOf(df.format(random.nextDouble(50, 130))));
			turbine.setWnacIntltmp(String.valueOf(df.format(random.nextDouble(50, 89))));
			turbine.setWtrfTrftmptrfgri(String.valueOf(df.format(random.nextDouble(50, 89))));
			turbine.setWtrfTrftmptrftur(String.valueOf(df.format(random.nextDouble(50, 85))));
			turbine.setWyawCabwup(String.valueOf(df.format(random.nextDouble(50, 91))));
			turbine.setWyawYawang(String.valueOf(df.format(random.nextDouble(50, 90))));
			turbine.setWcnvGriphvA(wcnvGrpihvs[0]);
			turbine.setWcnvGriphvB(wcnvGrpihvs[1]);
			turbine.setWcnvGriphvC(wcnvGrpihvs[2]);

			turbines.add(turbine);
		}
		return turbines;
	}

}

