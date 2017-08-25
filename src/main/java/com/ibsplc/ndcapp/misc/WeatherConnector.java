package com.ibsplc.ndcapp.misc;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.ibsplc.ndcapp.common.APIKeys;
import com.ibsplc.ndcapp.util.HttpGetClient;

public class WeatherConnector {

	final static Logger log = Logger.getLogger("WeatherConnector.class");
	static Map<String,String> headerParameterMap = new HashMap();
	static{
	
	headerParameterMap.put("x-apiKey", APIKeys.SITA_WEATHER_KEY);
	headerParameterMap.put("apiKey", APIKeys.SITA_WEATHER_KEY);
	}
	
	public static String getWeather(String airPortCode){
		
 		HttpGetClient client = new HttpGetClient();
 		
 		String url = "https://weather-qa.api.aero/weather/v1/combined/"+ airPortCode ;
 		System.out.println("Sita weather URL is " + url);
 		String weather_details = client.getJSON(url, null, 1000,headerParameterMap);
		log.debug(weather_details);		
		
		return weather_details;
		
	}
	
	public static void main(String argv[]){
		getWeather("HEL");
		
	}
}
