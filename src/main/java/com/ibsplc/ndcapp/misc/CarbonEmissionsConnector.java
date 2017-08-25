package com.ibsplc.ndcapp.misc;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibsplc.ndcapp.common.APIKeys;
import com.ibsplc.ndcapp.util.HttpGetClient;

public class CarbonEmissionsConnector {

	final static Logger log = Logger.getLogger("CarbonEmissionsConnector.class");
	static Map<String,String> parameterMap = new HashMap();
	static{

		parameterMap.put("way", "oneway");
		parameterMap.put("class","economy_class");
		parameterMap.put("passengers","1");
		parameterMap.put("auth_code","penguin");

	}

	public static String getCarbonEmissions(String origin, String destination){

		parameterMap.put("origin", origin);
		parameterMap.put("destination", destination);
		HttpGetClient client = new HttpGetClient();

		String url = "http://api.southpolegroup.services/api.php/category/flight_emission" ;		
		String weather_details = client.getJSON(url, parameterMap, 1000,null);
		log.debug(weather_details);		

		return weather_details;

	}

	public static void main(String argv[]){
		getCarbonEmissions("HEL","COK");

	}
}
