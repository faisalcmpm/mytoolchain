package com.ibsplc.ndcapp.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.DeserializationConfig.Feature;

import com.ibsplc.ndcapp.common.APIKeys;
import com.ibsplc.ndcapp.misc.vo.Airlines;
import com.ibsplc.ndcapp.misc.vo.AppInTheAirVo;
import com.ibsplc.ndcapp.util.HttpGetClient;
import com.ibsplc.ndcapp.util.googleVision.vo.GoogleVisionDetails;

public class AppInTheAirConnector {

	final static Logger log = Logger.getLogger("AppInTheAirConnector.class");
	static Map<String,String> headerParameterMap = new HashMap();
	static{

		headerParameterMap.put("Authorization", "Bearer b)Tn16SjMFJ!xof0DR.qibNCVjujkUOTj)ASVLVI0XcDjo9xIu1sbDEi3f7kV*TgsW$SZ_Gd9HTK2OzFoDlh");

	}

	public static Airlines[] getUserDetails(){
		try{
			HttpClient httpClient = new DefaultHttpClient(); 

			HttpGet request = new HttpGet("https://iappintheair.appspot.com/api/v1/me");
			request.addHeader("Authorization", "Bearer b)Tn16SjMFJ!xof0DR.qibNCVjujkUOTj)ASVLVI0XcDjo9xIu1sbDEi3f7kV*TgsW$SZ_Gd9HTK2OzFoDlh");
			HttpResponse response = httpClient.execute(request);


			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					(response.getEntity().getContent())));

			StringBuilder result = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null) {
				result.append(line);
			}
			log.debug(result.toString());
			String appInTheAir_details = result.toString();
			log.debug(appInTheAir_details );	
			

			AppInTheAirVo appInTheAirVo = new AppInTheAirVo();
			appInTheAirVo = parseJsonMessage(appInTheAirVo, result.toString());
			if(appInTheAirVo.getData().getAirlines()!=null){
			for(Airlines airline : appInTheAirVo.getData().getAirlines()){
				System.out.println(airline.getCode()+" : "+airline.getName()+" : "+airline.getCount());
			}

			return appInTheAirVo.getData().getAirlines();
			}
			return null;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{

		}
		return null;


	}


	public static <T> T parseJsonMessage(T object,String jsonMessage) throws JsonParseException, JsonMappingException, IOException {
		log.debug("Start parsing JSON Message");
		ObjectMapper mapper = new ObjectMapper();
		//mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure( Feature.FAIL_ON_UNKNOWN_PROPERTIES , false);

		object = (T) mapper.readValue(jsonMessage, object.getClass());
		log.debug("End parsing JSON Message");
		return object;
	}
	
	public static void main(String argv[]){
		getUserDetails();

	}
}
