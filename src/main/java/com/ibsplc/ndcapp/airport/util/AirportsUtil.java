package com.ibsplc.ndcapp.airport.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibsplc.ndcapp.common.APIKeys;
import com.ibsplc.ndcapp.util.HttpGetClient;

public class AirportsUtil {

	static Logger log = Logger.getLogger(AirportsUtil.class.getName());
	
	static String allAirports = null;

	public static String getAllAirports(){
		
		if(allAirports != null) {
			return allAirports;
		}
 		HttpGetClient client = new HttpGetClient();
 		String allAirports = client.getJSON("https://airport-qa.api.aero/airport/v2/airports?user_key=" + APIKeys.SITA_AIRPORT_KEY, null, 1000,null);
		log.debug(allAirports);		
		return allAirports;
	}

}
