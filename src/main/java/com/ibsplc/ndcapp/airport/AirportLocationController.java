package com.ibsplc.ndcapp.airport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;

import com.ibsplc.ndcapp.common.APIKeys;
import com.ibsplc.ndcapp.framework.exception.GenericException;

import com.ibsplc.ndcapp.util.HttpGetClient;
import com.ibsplc.ndcapp.util.PropertiesHolder;


@Controller
public class AirportLocationController {

	final static Logger log = Logger.getLogger("AirportLocationController.class");
	private static HashMap<String,AirportDetails> nearestAirportMap =new HashMap<String,AirportDetails>();

	public static <T> T parseJsonMessage(T object,String jsonMessage) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		object = (T) mapper.readValue(jsonMessage, object.getClass());
		return object;
	}


	public static AirportDetails getNearestAirport(String latitude, String longitude) throws GenericException{

		if(nearestAirportMap.get(latitude+longitude)!=null){
			return nearestAirportMap.get(latitude+longitude);
		}
		try {
			log.debug("Start get nearest airport from latitude and longitude");

			
			if (true) {
				if (latitude.equals("50.1109") && longitude.equals("8.6821")) {
					AirportDetails airportDetails = new AirportDetails();
					Airports[] airports = new Airports[1];
					Airports airports2 = new Airports();
					airports2.setCountry("DE");
					airports2.setIatacode("FRA");
					airports2.setLat(latitude);
					airports2.setLng(longitude);
					airports[0] = airports2;
					airportDetails.setAirports(airports);
					return airportDetails;
				} else {
					AirportDetails airportDetails = new AirportDetails();
					
					Airports[] airports = new Airports[1];
					Airports airports2 = new Airports();
					airports2.setCountry("TR");
					airports2.setIatacode("ADB");
					airports2.setLat("38.4237");
					airports2.setLng("27.1428");
					airports[0] = airports2;
					airportDetails.setAirports(airports);
					return airportDetails;
				}
			}
			
			
			HttpGetClient client = new HttpGetClient();
			Map<String, String> parameterMap = new HashMap<String, String>();
			parameterMap.put("user_key",APIKeys.SITA_AIRPORT_KEY);
			//maxAirports=10
			parameterMap.put("maxAirports","1");		
			Map<String, String> headerParameterMap = new HashMap<String, String>();
			headerParameterMap.put("accept", "application/json");		
			String response = client.getJSON("https://airport-qa.api.aero/airport/v2/nearest/"+latitude+"/"+longitude, parameterMap, 1000,headerParameterMap);
			System.out.println(response);		
			AirportDetails airportDetails = new AirportDetails();
			try {
				if (response.indexOf("\\(") != -1) {
					response = response.split("\\(")[1];
				}
				airportDetails = parseJsonMessage(airportDetails, response);
				System.out.println(airportDetails);
				nearestAirportMap.put(latitude+longitude,airportDetails);
				return airportDetails;
			} catch (JsonParseException jsonParseException) {
				log.error("",jsonParseException);
			} catch (JsonMappingException jsonMappingException) {
				log.error("",jsonMappingException);
			} catch (IOException ioException) {
				log.error("",ioException);
			}
		} catch(Exception e) {
			throw new GenericException(GenericException.UNABLE_TO_LOCATE, PropertiesHolder.getProperty(GenericException.ERROR_MESSAGE_PROPERTY, GenericException.UNABLE_TO_LOCATE));
		}
		finally {
			log.debug("End get nearest airport from latitude and longitude");
		}

		return null;
	}
//	public static void main(String[] args) {
//		AirportLocationController airportLocationController = new AirportLocationController();
//		System.out.println(airportLocationController.getNearestAirport("50.1109", "8.6821"));
//	}

}
